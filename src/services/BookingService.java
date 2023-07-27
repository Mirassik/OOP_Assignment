package services;

import models.BookingOrder;
import repositories.BookingRepository;
import java.util.List;

public interface BookingService {
    BookingOrder makeBookingOrder(String category, int duration);
    void cancelBookingOrder(Long id);
    void prolongBookingOrder(Long id, int additionalDays);
    List<BookingOrder> getBookingsByCategory(String category);
}



