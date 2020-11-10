package hw2;/*
subject used in the observer pattern. Users are subjects
 */
import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> followers = new ArrayList<Observer>();

    /*the follower observes the one they are following, attach an observer to
    the one they are following
     */
    public void attach(Observer observer) {
        followers.add(observer);
    }

    public void notifyFollowers(String message) {
        for(Observer observer : this.followers) {
            observer.update(this, message);
        }
    }

}
