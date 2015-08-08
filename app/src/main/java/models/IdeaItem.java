package models;

/**
 * Created by vilso on 08/08/2015.
 */
public class IdeaItem {
    private String title;
    private float ratings;
    private int likes;
    private String youtubeLink;
    private String status;
    //TODO: Add private int views

    public IdeaItem(String title, float ratings, int likes, String youtubeLink, String status){
        this.title = title;
        this.ratings = ratings;
        this.likes = likes;
        this.youtubeLink = youtubeLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
