package hw2visitors;
/*
visitor for finding how many groups there are in total in
the group and its sub groups
 */
import hw2.User;
import hw2.Group;
import hw2.Visitor;

public class GroupTotalVisitor implements Visitor {

    private int groupTotal = 0;

    @Override
    public void visitUser(User user) {
        //do nothing
    }

    @Override
    public void visitGroup(Group group) {
        setGroupTotal(getGroupTotal() + 1);
    }

    public int getGroupTotal() {
        return groupTotal;
    }

    public void setGroupTotal(int userTotal) {
        this.groupTotal = userTotal;
    }
}
