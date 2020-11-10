package hw2visitors;
/*
visitor for finding how many users there are in total in
the group and its sub groups
 */
import hw2.Visitor;
import hw2.User;
import hw2.Group;

public class UserTotalVisitor implements Visitor {

    private int userTotal = 0;

    @Override
    public void visitUser(User user) {
        setUserTotal(getUserTotal() + 1);
    }

    @Override
    public void visitGroup(Group group) {
        //do nothing
    }

    public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }
}
