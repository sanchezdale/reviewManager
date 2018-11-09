package me.sanchezdale.reviewManager.data;

import java.util.Date;
import java.util.Objects;

public class ReviewRequest {

    private User requestingUser;

    private Date dateCreated;

    private String clientName;

    private String clientEmail;

    private String message;

    public ReviewRequest() { }

    public ReviewRequest(User requestingUser, Date dateCreated, String clientName, String clientEmail, String message) {
        this.requestingUser = requestingUser;
        this.dateCreated = dateCreated;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.message = message;
    }

    public User getRequestingUser() {
        return requestingUser;
    }

    public void setRequestingUser(User requestingUser) {
        this.requestingUser = requestingUser;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewRequest that = (ReviewRequest) o;
        return Objects.equals(requestingUser, that.requestingUser) &&
                Objects.equals(dateCreated, that.dateCreated) &&
                Objects.equals(clientName, that.clientName) &&
                Objects.equals(clientEmail, that.clientEmail) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestingUser, dateCreated, clientName, clientEmail, message);
    }

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "requestingUser=" + requestingUser +
                ", dateCreated=" + dateCreated +
                ", clientName='" + clientName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
