package hw2;/*
composite class and component in the composite pattern
accepts a visitor in the visitor pattern
 */
import java.util.ArrayList;
import java.util.List;

public class Group implements TreeElement{

    private String groupID;
    private List<TreeElement> groupMembers = new ArrayList<TreeElement>();

    public Group(String newID) {
        //TODO: check that the ID doesn't already exist
        this.groupID = newID;
    }

    @Override
    public String getID() {
        return this.groupID;
    }

    @Override
    public String toString() {
        return this.groupID;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitGroup(this);
        for(TreeElement member : groupMembers) {
            if (member instanceof User) {
                member.accept(visitor);
            } else if (member instanceof Group) {
                member.accept(visitor);
            }
        }
    }

    public void addGroupMember(TreeElement newMember){
        this.groupMembers.add(newMember);
    }

    // check if a group and groups in the group contain a given user id
    public Boolean containsUserID(String UserID) {
        for (TreeElement member : groupMembers) {
            if (member instanceof User) {
                if (member.getID().equals(UserID)) {
                    return true;
                }
            }
            // for a group in this.groupMembers
            else if (member instanceof Group) {
                if (((Group) member).containsUserID(UserID)) {
                    return true;
                }
            }
        }
        return false;
    }

    // check if a group and groups in the group contain a given group id
    public Boolean containsGroupID(String memberID){
        for (TreeElement member : groupMembers) {
            if (member instanceof User) {
                continue;
            }
            // for a group in this.groupMembers
            else if (member instanceof Group) {
                if (member.getID().equals(memberID)){
                    return true;
                }
                // check the ID of groups in groups
                else {
                    if(((Group) member).containsGroupID(memberID)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //return a User that is in this group or in one of its sub groups
    public User getUser(String userID){
        for (TreeElement member : groupMembers) {
            if (member instanceof User) {
                if (member.getID().equals(userID)){
                    return (User) member;
                }
            }
            else if (member instanceof Group) {
                // iterate through all groups in this group and see if the user id is in there
                if (((Group) member).containsUserID(userID)) {
                    return ((Group) member).getUser(userID);
                }
            }
        }
        return null;
    }
}
