package hw2;/*
UserView object with a private constructor that sets up the GUI
and a public method for getting the GUI container and opening the GUI

hw 3 #2 created a label for creationTime in line 28
hw 3 #3 gets displayed through the list view on line 73 and was implemented in the User class
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserView {

    private VBox mainBox;

    private UserView(User user, Group rootGroup){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Label userNameLabel = new Label("User View For: " + user.getID());
        //hw 3 #2
        Label creationTimeLabel = new Label("This user's creation time is: " + user.getCreationTime());

        TextField userIDField = new TextField();
        userIDField.setPromptText("User ID");
        Button followButton = new Button("Follow User");
        followButton.setOnAction(actionEvent -> {
            String userToFollow = userIDField.getText();
            User toFollow = rootGroup.getUser(userToFollow);
            if (toFollow == null){
                alert.setContentText("This user does not exist!");
                alert.showAndWait();
            }
            else if (toFollow == user) {
                alert.setContentText("You can't follow yourself!");
                alert.showAndWait();
            }
            else if (user.getFollowingList().contains(toFollow)){
                alert.setContentText(user+ " already follows this user!");
                alert.showAndWait();
            }
            else {
                //if user exists , this user becomes a follower (observer) of the one they are following
                toFollow.attach(user);
                //add the user you are following to your list of following
                user.addFollowingList(toFollow);
            }
            userIDField.clear();
        });

        ListView currentFollowingView = new ListView(user.getFollowingList());
        currentFollowingView.setPrefSize(500, 200);

        TextArea tweetField = new TextArea();
        tweetField.setWrapText(true);
        tweetField.setPrefSize(200, 100);
        tweetField.setPromptText("Tweet Message");
        Button postButton = new Button("Post Tweet");
        postButton.setOnAction(actionEvent -> {
            String tweet = tweetField.getText();
            //postMessage will notify observers
            user.postMessage(tweet);
            tweetField.clear();
        });

        //hw 3 #3 gets displayed through this object
        ListView newsFeedView = new ListView(user.getNewsFeedList());
        newsFeedView.setPrefSize(500, 200);

        //put in boxes
        HBox followUserBox = new HBox(userIDField, followButton);
        HBox postTweetBox = new HBox(tweetField, postButton);
        postTweetBox.setAlignment(Pos.BOTTOM_LEFT);
        mainBox = new VBox(userNameLabel, creationTimeLabel, followUserBox, currentFollowingView, postTweetBox, newsFeedView);
    }

    public VBox getMainBox(){
        return mainBox;
    }

    public static void openUserView(User user, Group rootGroup){
        UserView thisUserView = new UserView(user, rootGroup);
        VBox mainBox = thisUserView.getMainBox();
        Scene secondScene = new Scene(mainBox, 400, 500);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle(user.getID());
        secondStage.show();
    }
}
