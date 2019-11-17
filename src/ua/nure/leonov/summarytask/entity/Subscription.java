package ua.nure.leonov.summarytask.entity;

import java.util.Date;

public class Subscription extends Entity{
    private int id;
    private Edition edition;
    private User user;
    private String date;

    public Subscription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", edition=" + edition +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
