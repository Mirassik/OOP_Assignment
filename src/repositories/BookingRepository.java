package repositories;

import models.BookingOrder;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BookingRepository {
    BookingOrder save(BookingOrder order);
    void delete(Long id);
    BookingOrder findById(Long id);
    List<BookingOrder> findByCategory(String category);
    void update(BookingOrder order);
}

