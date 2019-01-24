package com.aelastic.xspot.bookings.service;

import com.aelastic.xspot.bookings.models.BookingState;
import com.aelastic.xspot.bookings.models.dao.BookingDao;
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

@Service
public class BookingsService {


    private BookingsRepository bookingsRepository;
    private PlacesRepository placesRepository;

    @Autowired
    public BookingsService(BookingsRepository bookingsRepository, PlacesRepository placesRepository) {
        this.bookingsRepository = bookingsRepository;
        this.placesRepository = placesRepository;
    }

    @Transactional
    public BookingDto saveBooking(SaveBookingRequest saveBookingRequest) {
        BookingDto booking = saveBookingRequest.getBooking();

        //TODO save in kafka...

        processAvailability(booking);

        BookingDao save = bookingsRepository.save(BookingMapper.fromDto2Dao(booking));


        return BookingMapper.fromDao2Dto(save);
    }

    private BookingState processAvailability(BookingDto booking) {
        //TODO implement
        //use kafka streams to get availability by time(use windowing)
        //or discuss other solutions
        return null;
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
}
