import models.BookingOrder;
import repositories.BookingRepository;
import repositories.PostgreSQLBookingRepository;
import services.BookingService;
import services.BookingServiceImpl;
import services.PollingService;
import services.PollingServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingRepository bookingRepository = new PostgreSQLBookingRepository(
        "jdbc:postgresql://localhost:5432/super_project",
        "postgres",
        "1"
        );
        BookingService bookingService = new BookingServiceImpl(bookingRepository);

        PollingService pollingService = new PollingServiceImpl();
        pollingService.start(bookingService);
    }
}
