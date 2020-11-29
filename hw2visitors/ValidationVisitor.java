package hw2visitors;

import hw2.Group;
import hw2.User;
import hw2.Visitor;
import java.util.ArrayList;

public class ValidationVisitor implements Visitor {
    ArrayList allID = new ArrayList();
    Boolean valid = true;

    @Override
    public void visitUser(User user) {
        //check if it's already in the array
        if (allID.contains(user.getID())){
            valid = false;
        }
        //check if contains spaces
        if (user.getID().contains(" ")){
            valid = false;
        }
        allID.add(user.getID());
    }

    @Override
    public void visitGroup(Group group) {
        //check if it's already in the array
        if (allID.contains(group.getID())){
            valid = false;
        }
        //check if contains spaces
        if (group.getID().contains(" ")){
            valid = false;
        }
        allID.add(group.getID());
    }

    public Boolean getValid() {
        return valid;
    }
}
