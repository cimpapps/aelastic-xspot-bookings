package com.aelastic.xspot.bookings.service;

import com.aelastic.xspot.bookings.models.dto.BookingDto;
import com.aelastic.xspot.bookings.models.request.DeleteBookingRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingByIdRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingsRequest;
import com.aelastic.xspot.bookings.models.request.SaveBookingRequest;
import com.aelastic.xspot.bookings.models.response.DeleteBookingResponse;
import com.aelastic.xspot.bookings.models.response.GetBookingsResponse;
import org.springframework.stereotype.Service;

@Service
public class BookingsService {

    public BookingDto saveBooking(SaveBookingRequest saveBookingRequest) {
        //TODO implement
        return null;
    }

    public GetBookingsResponse getBookingsByUser(String userId, GetBookingsRequest getBookingsRequest) {
        return null;
    }

    public GetBookingsResponse getBookingsByPlace(String placeId, GetBookingsRequest getBookingsRequest) {
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
