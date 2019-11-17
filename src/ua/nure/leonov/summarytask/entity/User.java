package ua.nure.leonov.summarytask.entity;

public class User extends Entity {
    private long id;
    private String login;
    private String pass;
    private String email;
    private String name;
    private String surname;
    private boolean isAdmin;
    private boolean isBan;
    private double balance;

    public User() {}

    public User(String login, String pass, String email, String name, String surname, boolean isAdmin, boolean isBan, double balance) {
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.isAdmin = isAdmin;
        this.isBan = isBan;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean getBan() { return isBan;}

    public void setBan(boolean ban) {
        isBan = ban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", getAdmin=" + isAdmin +
                ", getBan=" + isBan +
                ", balance=" + balance +
                '}';
    }
}
