package models;

/**
 * Created by vilso on 08/08/2015.
 */
public class Like {
    public String likeID;
    public String userID;
    public String ideaID;

    public Like(String likeID, String userID, String ideaID){
        this.likeID = likeID;
        this.userID = userID;
        this.ideaID = ideaID;
    }

    public String getLikeID() {
        return likeID;
    }

    public void setLikeID(String likeID) {
        this.likeID = likeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getIdeaID() {
        return ideaID;
    }

    public void setIdeaID(String ideaID) {
        this.ideaID = ideaID;
    }
}
