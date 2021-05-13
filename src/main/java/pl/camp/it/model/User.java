package pl.camp.it.model;

public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private Status status;

    public User() {
    }

    public User(String name, String surname, String login, String password, Status status) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.status = status;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status{
        USER,
        ADMIN
    }
}
