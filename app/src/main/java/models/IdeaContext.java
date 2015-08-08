package models;

import android.support.annotation.NonNull;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by vilso on 08/08/2015.
 */
public class IdeaContext {

    public List<ParseObject> getListOfIdeas(ParseObject user) {
        ParseQuery query = ParseQuery.getQuery("Idea");
        query.whereEqualTo("user", user);
        List<ParseObject> ideas = null;
        try {
            ideas = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ideas;
    }

    public List<ParseObject> getListOfIdeas(){
        ParseQuery query = ParseQuery.getQuery("Idea");
        query.setLimit(10);
        List<ParseObject> ideas = null;
        try {
            ideas = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ideas;
    }

    public void likeIdea(ParseObject idea, ParseObject user) {

        ParseQuery query = ParseQuery.getQuery("Like");
        query.whereEqualTo("user", user);
        query.whereEqualTo("idea", idea);

        try {
            List<ParseObject> lists = query.find();

            // the user has not yet liked the idea
            if(lists.size() < 1) {
                ParseObject like = new ParseObject("Like");
                like.put("user", user);
                like.put("idea", idea);
            } else {
                lists.get(0).deleteInBackground();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void addComment(ParseUser user, ParseObject idea, String comment){
        ParseObject commentIdea = new ParseObject("Comment");
        commentIdea.put("user", user);
        commentIdea.put("description", comment);
        commentIdea.put("idea", idea);
        commentIdea.saveInBackground();
    }

    public void addIdea(ParseUser user, String title, String link){
        ParseObject idea = new ParseObject("Idea");
        idea.put("title", title);
        idea.put("youtube", link);
        idea.put("status", "draft");
        idea.put("rating", 0.0);
        idea.put("user", user);
        idea.saveInBackground();
    }

    /*
     * You can set the limit for each category.
     * Just set the min like and the max like
     */

    public List<ParseObject> getListOfIdeas(int minLike, int maxLike){

        ParseQuery<ParseObject> likes = ParseQuery.getQuery("Like");

        List<ParseObject> freshIdeas = new ArrayList<ParseObject>();
        List<ParseObject> listOfIdeas = getListOfIdeas();
        for(ParseObject idea: listOfIdeas) {
            likes.whereEqualTo("idea", idea);
            try {
                int numLikes = likes.count();
                if (numLikes >= minLike && numLikes <= maxLike) {
                    freshIdeas.add(idea);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return freshIdeas;

    }

    public List<ParseObject> createRating(List<Rate> ratings){
        List<ParseObject> listRate = new ArrayList<>();
        for(Rate item  :ratings){
            ParseObject rate = new ParseObject("Rate");
            rate.put(item.getQuestion(), item.getScore());
        }

        return listRate;
    }

    public void addRating(ParseUser user, ParseObject idea, ParseObject rating){
        ParseObject userRateIdea = new ParseObject("UserRateIdea");
        userRateIdea.put("user", user);
        userRateIdea.put("idea", idea);
        userRateIdea.put("rating", rating);
        userRateIdea.saveInBackground();
    }
}
