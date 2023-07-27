package repositories;

import models.BookingOrder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLBookingRepository implements BookingRepository {
    private final String url;
    private final String username;
    private final String password;

    public PostgreSQLBookingRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public BookingOrder save(BookingOrder order) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO bookings (category, duration) VALUES (?, ?)",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setString(1, order.getCategory());
            statement.setInt(2, order.getDuration());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating booking failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating booking failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling strategy.
        }

        return order;
    }

    @Override
    public void update(BookingOrder order) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE bookings SET category = ?, duration = ? WHERE id = ?")) {
            statement.setString(1, order.getCategory());
            statement.setInt(2, order.getDuration());
            statement.setLong(3, order.getId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating booking failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling strategy.
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM bookings WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling strategy.
        }
    }

    @Override
    public BookingOrder findById(Long id) {
        BookingOrder order = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM bookings WHERE id = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = extractBookingOrderFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling strategy.
        }
        return order;
    }

    @Override
    public List<BookingOrder> findByCategory(String category) {
        List<BookingOrder> bookings = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM bookings WHERE category = ?")) {
            statement.setString(1, category);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    bookings.add(extractBookingOrderFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling strategy.
        }
        return bookings;
    }

    private BookingOrder extractBookingOrderFromResultSet(ResultSet resultSet) throws SQLException {
        BookingOrder order = new BookingOrder();
        order.setId(resultSet.getLong("id"));
        order.setCategory(resultSet.getString("category"));
        order.setDuration(resultSet.getInt("duration"));
        return order;
    }
}