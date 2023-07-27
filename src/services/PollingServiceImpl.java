package services;

import models.BookingOrder;

import java.util.List;
import java.util.Scanner;

public class PollingServiceImpl implements PollingService{

    Scanner sc = new Scanner(System.in);

    @Override
    public void start(BookingService bookingService) {
        System.out.println("Welcome to online booking service!");
        while (true) {
            System.out.println("Please, choose what you'd like to do.");
            System.out.println(
                    "1. Make a booking.\n2. Prolong booking.\n3. Cancel booking\n4. Get all bookings by name\n-1. To exit"
            );

            int number = sc.nextInt();
            switch (number) {
                case 1 -> makeBooking(bookingService);
                case 2 -> prolongBook(bookingService);
                case 3 -> cancelBook(bookingService);
                case 4 -> getBookingsByCategory(bookingService);
                case -1 -> System.exit(0);
                default -> System.out.println("You entered number out of range!");
            }
        }
    }

    @Override
    public void makeBooking(BookingService bookingService) {
        System.out.println("Enter name of booking");
        String name = sc.next();
        System.out.println("Enter duration of booking (integer)");
        int duration = sc.nextInt();
        BookingOrder order = bookingService.makeBookingOrder(name, duration);
        System.out.println("Your order id #" + order.getId());
    }

    @Override
    public void prolongBook(BookingService bookingService) {
        System.out.println("Enter id of booking to prolong");
        int idx = sc.nextInt();
        System.out.println("Enter duration of booking to prolong (integer)");
        int prolongDuration = sc.nextInt();
        bookingService.prolongBookingOrder((long) idx, prolongDuration);
    }

    @Override
    public void cancelBook(BookingService bookingService) {
        System.out.println("Enter booking id");
        int idx = sc.nextInt();
        bookingService.cancelBookingOrder((long) idx);
    }

    @Override
    public void getBookingsByCategory(BookingService bookingService) {
        System.out.println("Enter name of your booking");
        String category = sc.next();
        List<BookingOrder> bookingOrders = bookingService.getBookingsByCategory(category);
        for (BookingOrder bookingOrder: bookingOrders) {
            System.out.println(bookingOrder);
        }
    }
}
