package hw2visitors;

import hw2.Group;
import hw2.User;
import hw2.Visitor;

public class UpdateVisitor implements Visitor {
    String lastUpdateUser = "No one";
    long mostRecentUpdateTime = 0;

    @Override
    public void visitUser(User user) {
        //if this user was updated more recently keep track of it
        if (user.getLastUpdateTime() > mostRecentUpdateTime){
            mostRecentUpdateTime = user.getLastUpdateTime();
            lastUpdateUser = user.getID();
        }
    }

    @Override
    public void visitGroup(Group group) {
    //do nothing
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }
}
