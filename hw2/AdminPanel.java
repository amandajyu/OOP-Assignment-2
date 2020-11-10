package hw2;
/*
AdminPanel is a singleton using the Singleton pattern
creation of the AdminPanel GUI is in the private constructor
 */
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import hw2visitors.MessageTotalVisitor;
import hw2visitors.GroupTotalVisitor;
import hw2visitors.PositivePercentageVisitor;
import hw2visitors.UserTotalVisitor;

public class AdminPanel {

    private static AdminPanel pointer;
    public static AdminPanel getInstance() {
        if (pointer == null) {
            pointer = new AdminPanel();
        }
        return pointer;
    }

    private HBox mainBox;

    private AdminPanel(){
        final Image rootIcon = new Image("hw2/folder-icon.png", 16, 16, false, false);
        Group rootGroup = new Group("Root");

        //create root and tree view
        TreeItem<TreeElement> root = new TreeItem<TreeElement> (rootGroup, new ImageView(rootIcon));
        root.setExpanded(true);
        TreeView<TreeElement> treeView = new TreeView<TreeElement>(root);

        // create dialogs
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);

        //text field for new user ID
        TextField userField = new TextField();
        userField.setPromptText("User ID");

        //button for adding new user
        Button addUser = new Button("Add User");
        addUser.setOnAction(actionEvent -> {
            TreeItem<TreeElement> selectedItem = treeView.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                alert.setContentText("Please add to a group.");
                alert.showAndWait();
                userField.clear();
            }
            else if (selectedItem.getValue() instanceof User) {
                alert.setContentText("Please add to a group.");
                alert.showAndWait();
            }
            else if (selectedItem.getValue() instanceof Group){
                String userIDInput = userField.getText();
                // check if the user is in any groups already
                if (rootGroup.containsUserID(userIDInput)){
                    alert.setContentText("This user is already in a group!");
                    alert.showAndWait();
                }
                /* else the user ID does not exist yet, it is unique
                and not in a group and should be added
                 */
                else {
                    User temp = new User(userIDInput);
                    ((Group) selectedItem.getValue()).addGroupMember(temp);
                    selectedItem.getChildren().add(new TreeItem<TreeElement>(temp));
                }
            }
            userField.clear();
        });

        //text field for new group ID
        TextField groupField = new TextField();
        groupField.setPromptText("Group ID");

        //button for adding new group
        Button addGroup = new Button("Add Group");
        addGroup.setOnAction(actionEvent -> {
            TreeItem<TreeElement> selectedItem = treeView.getSelectionModel().getSelectedItem();
            if (selectedItem.getValue() instanceof User){
                alert.setContentText("Please add to a group.");
                alert.showAndWait();
            }
            else if (selectedItem.getValue() instanceof Group){
                String groupIDInput = groupField.getText();
                // check if the group already exists
                if (rootGroup.containsGroupID(groupIDInput)){
                    alert.setContentText("This group already exists.");
                    alert.showAndWait();
                }
                /* else the group ID does not exist yet
                it is unique and should be added
                 */
                else {
                    Group temp = new Group(groupIDInput);
                    ((Group) selectedItem.getValue()).addGroupMember(temp);
                    selectedItem.getChildren().add(new TreeItem<TreeElement>(temp, new ImageView(rootIcon)));
                }
            }
            groupField.clear();
        });

        //button for opening the individual user views when
        Button openUserButton = new Button("Open User View");
        openUserButton.setOnAction(actionEvent -> {
            TreeItem<TreeElement> selectedItem = treeView.getSelectionModel().getSelectedItem();
            if (selectedItem.getValue() instanceof Group){
                alert.setContentText("Please select a user to open user view");
                alert.showAndWait();
            }
            else if (selectedItem.getValue() instanceof User){
                User userViewUser = (User) selectedItem.getValue();
//                new UserView(userViewUser, rootGroup);
                UserView.openUserView(userViewUser, rootGroup);
            }
        });

        //button for showing total number of users
        Button userTotalButton = new Button("Show User Total");
        userTotalButton.setOnAction(actionEvent -> {
            UserTotalVisitor userTotalVisitor = new UserTotalVisitor();
            rootGroup.accept(userTotalVisitor);
            infoAlert.setContentText("There are " + userTotalVisitor.getUserTotal() + " users in total.");
            infoAlert.showAndWait();
        });
        //button for showing total number of groups
        Button groupTotalButton = new Button("Show Group Total");
        groupTotalButton.setOnAction(actionEvent -> {
            GroupTotalVisitor groupTotalVisitor = new GroupTotalVisitor();
            rootGroup.accept(groupTotalVisitor);
            infoAlert.setContentText("There are " + groupTotalVisitor.getGroupTotal() + " groups in total including Root.");
            infoAlert.showAndWait();
        });
        // button for showing total number of messages posted by users
        Button messageTotalButton = new Button("Show Message Total");
        messageTotalButton.setOnAction(actionEvent -> {
            MessageTotalVisitor messageTotalVisitor = new MessageTotalVisitor();
            rootGroup.accept(messageTotalVisitor);
            infoAlert.setContentText("There are " + messageTotalVisitor.getMessageTotal() + " messages.");
            infoAlert.showAndWait();
        });
        // button for showing percentage of positive messages
        Button positivePercentageButton = new Button("Show Positive Percentage Total");
        positivePercentageButton.setOnAction(actionEvent -> {
            PositivePercentageVisitor positivePercentageVisitor = new PositivePercentageVisitor();
            rootGroup.accept(positivePercentageVisitor);
            infoAlert.setContentText("The percentage of positive messages is "
                    + positivePercentageVisitor.getPositivePercentage() + "%");
            infoAlert.showAndWait();
        });

        //put in boxes
        VBox treeBox = new VBox(treeView);
        HBox userButtonsBox = new HBox(userField, addUser);
        HBox groupButtonsBox = new HBox(groupField, addGroup);
        HBox showUserGroupBox = new HBox(userTotalButton, groupTotalButton);
        HBox showMessagePositiveBox = new HBox(messageTotalButton, positivePercentageButton);
        VBox allButtonsBox = new VBox(userButtonsBox, groupButtonsBox, openUserButton, showUserGroupBox, showMessagePositiveBox);
        mainBox = new HBox(treeBox, allButtonsBox);
    }

    //method for returning the admin panel to the driver as an HBox
    public HBox getAdminPanel() {
        return mainBox;
    }
}