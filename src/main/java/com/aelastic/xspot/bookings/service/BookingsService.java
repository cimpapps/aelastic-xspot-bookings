package com.aelastic.xspot.bookings.service;

import com.aelastic.xspot.bookings.messagebus.outbox.BookingProducer;
import com.aelastic.xspot.bookings.models.BookingState;
import com.aelastic.xspot.bookings.models.dao.BookingDao;
import com.aelastic.xspot.bookings.models.dao.PlaceDao;
import com.aelastic.xspot.bookings.models.dto.BookingDto;
import com.aelastic.xspot.bookings.models.mappers.BookingMapper;
import com.aelastic.xspot.bookings.models.request.DeleteBookingRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingByIdRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingsRequest;
import com.aelastic.xspot.bookings.models.request.SaveBookingRequest;
import com.aelastic.xspot.bookings.models.response.DeleteBookingResponse;
import com.aelastic.xspot.bookings.models.response.GetBookingsResponse;
import com.aelastic.xspot.bookings.repo.BookingsRepository;
import com.aelastic.xspot.bookings.repo.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class BookingsService {


    private BookingsRepository bookingsRepository;
    private PlacesRepository placesRepository;
    private BookingProducer bookingProducer;

    @Autowired
    public BookingsService(BookingsRepository bookingsRepository,
                           PlacesRepository placesRepository,
                           BookingProducer bookingProducer) {
        this.bookingsRepository = bookingsRepository;
        this.placesRepository = placesRepository;
        this.bookingProducer = bookingProducer;
    }

    @Transactional
    public BookingDto saveBooking(SaveBookingRequest saveBookingRequest) {
        BookingDto booking = saveBookingRequest.getBooking();

        int totalSeats = getTotalNumberOfSeats(booking.getPlaceId());
        int requestedSeats = booking.getNrOfPeople();
        if (totalSeats - requestedSeats < 0) {
            throw new ValidationException("Number of participants bigger than capacity");
        }

        BookingDao bookingDao = BookingMapper.fromDto2Dao(booking);
        List<BookingDao> bookingsInTimeFrame = getBookingsInTimeFrame(bookingDao);

        int numberOfSeatsAvailable = totalSeats - getNumberOfSeatReserved(booking, bookingsInTimeFrame);

        if (numberOfSeatsAvailable - requestedSeats >= 0) {
            bookingDao.setBookingState(BookingState.CONFIRMED);
        } else {
            //TODO to implement an algorithm to determine how long should be the waiting list
            bookingDao.setBookingState(BookingState.ON_WAITING_LIST);
        }

        BookingDao save = bookingsRepository.save(BookingMapper.fromDto2Dao(booking));

        bookingProducer.publishBooking(save);

        return BookingMapper.fromDao2Dto(save);
    }

    private List<BookingDao> getAllBookingsInTimeFrame(String placeId, LocalDateTime startDate, LocalDateTime endDate) {
        return bookingsRepository.findAllBookingsInTimeFrame(placeId, startDate, endDate)
                .orElse(Collections.emptyList());
    }


    public DeleteBookingResponse deleteBooking(DeleteBookingRequest deleteBookingRequest) {
        return null;
    }

    public GetBookingsResponse getBookings(GetBookingsRequest getBookingsRequest) {
        return null;
    }

    public BookingDto getBookingById(GetBookingByIdRequest id) {
        return null;
    }

    public BookingDto updateBooking(SaveBookingRequest build) {
        return null;
    }


    private int getNumberOfSeatReserved(BookingDto booking, List<BookingDao> bookingsInTimeFrame) {

        return (int) bookingsInTimeFrame.stream()
                .filter(b -> BookingState.CONFIRMED.equals(b.getBookingState()))
                .count();

    }

    private List<BookingDao> getBookingsInTimeFrame(BookingDao dao) {
        return getAllBookingsInTimeFrame(dao.getPlaceId(), dao.getStartDate(), dao.getEndDate());
    }


    private int getTotalNumberOfSeats(String placeId) {
        return placesRepository.findById(placeId)
                .map(PlaceDao::getTotalNumberOfSeats)
                .orElse(0);
    }

}
