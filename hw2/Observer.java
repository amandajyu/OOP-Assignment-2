package hw2;

/*
observer interface used in the observer pattern. Users are observers
 */
public interface Observer {
    public void update(Subject subject, String message);
}
