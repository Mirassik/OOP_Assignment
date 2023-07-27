package services;

import java.util.Scanner;

public interface PollingService {
    void start(BookingService bookingService);
    void makeBooking(BookingService bookingService);
    void prolongBook(BookingService bookingService);
    void cancelBook(BookingService bookingService);
    void getBookingsByCategory(BookingService bookingService);
}
