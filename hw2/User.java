package hw2;/*
leaf class in the composite pattern
subject and observer in the observer pattern
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User extends Subject implements TreeElement, Observer{
    private long creationTime;
    private long lastUpdateTime = 0;

    private String userID;
    final int MAX = 280;
    private List<User> following = new ArrayList<User>();
    private ObservableList<User> followingList = FXCollections.observableList(following);
    private List<String> myMessages = new ArrayList<String>();
    private List<String> newsFeed = new ArrayList<String>(Arrays.asList("News Feed:", "last update was " + lastUpdateTime));
    //observable list used to update the user view's newsfeed list view
    private ObservableList<String> newsFeedList = FXCollections.observableList(newsFeed);
    private Alert longTweet = new Alert(Alert.AlertType.WARNING);

    public User(String newID) {
        this.userID = newID;
        this.creationTime = System.currentTimeMillis();
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public String getID() {
        return this.userID;
    }

    @Override
    public String toString() {
        return this.userID;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitUser(this);
    }

    //override observer update
    @Override
    public void update(Subject subject, String message) {
        if (subject instanceof User) {
            this.newsFeedList.add(((User) subject).getID() + " : " + message);
            lastUpdateTime = System.currentTimeMillis();
            this.newsFeedList.set(1,"last update time was: " + lastUpdateTime);
        }
    }

    public ObservableList<User> getFollowingList() {
        return followingList;
    }

    public void addFollowingList(User user) {
        followingList.add(user);
    }

    public List<String> getMyMessages() {
        return myMessages;
    }

    public ObservableList<String> getNewsFeedList() {
        return newsFeedList;
    }

    /*when a user posts a message, users following this user should be notified
    using the observer pattern
     */
    public void postMessage (String message){
        if (message.length()<=MAX){
            myMessages.add(message);
            //add to own newsfeed
            newsFeedList.add(this.userID + " : " + message);
            lastUpdateTime = System.currentTimeMillis();
            this.newsFeedList.set(1,"last update time was: " + lastUpdateTime);
            notifyFollowers(message);
        }
        else {
            longTweet.setTitle("Tweet Too Long");
            longTweet.setContentText( "The Tweet needs to be 280 characters or less!");
            longTweet.showAndWait();
        }
    }
}
