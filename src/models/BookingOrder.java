package models;

public class BookingOrder {
    private Long id;
    private String category;
    private int duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "BookingOrder{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", duration=" + duration +
                '}';
    }
}
