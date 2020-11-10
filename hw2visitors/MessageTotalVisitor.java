package hw2visitors;
/*
visitor for finding how many messages there are in total
posted by users in the group and by users in its sub groups
 */
import hw2.Visitor;
import hw2.User;
import hw2.Group;

public class MessageTotalVisitor implements Visitor {

    private int MessageTotal = 0;

    @Override
    public void visitUser(User user) {

        setMessageTotal(getMessageTotal() + user.getMyMessages().size());
    }

    @Override
    public void visitGroup(Group group) {
        //do nothing
    }

    public int getMessageTotal() {
        return MessageTotal;
    }

    public void setMessageTotal(int messageTotal) {
        MessageTotal = messageTotal;
    }
}
