package services;

import models.BookingOrder;
import repositories.BookingRepository;

import java.util.List;

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingOrder makeBookingOrder(String category, int duration) {
        BookingOrder order = new BookingOrder();
        order.setCategory(category);
        order.setDuration(duration);
        return bookingRepository.save(order);
    }

    @Override
    public void cancelBookingOrder(Long id) {
        bookingRepository.delete(id);
    }

    @Override
    public void prolongBookingOrder(Long id, int additionalDays) {
        BookingOrder order = bookingRepository.findById(id);
        if (order != null) {
            order.setDuration(order.getDuration() + additionalDays);
            bookingRepository.update(order);
        }
    }

    @Override
    public List<BookingOrder> getBookingsByCategory(String category) {
        return bookingRepository.findByCategory(category);
    }
}
