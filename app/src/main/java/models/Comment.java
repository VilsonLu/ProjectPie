package models;

/**
 * Created by vilso on 09/08/2015.
 */
public class Comment {
    private String dateCreated;
    private String user;
    private String message;

    public Comment(String user, String message, String dateCreated){
        this.user = user;
        this.dateCreated = dateCreated;
        this.message = message;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
