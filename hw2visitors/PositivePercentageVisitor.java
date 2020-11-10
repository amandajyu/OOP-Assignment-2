package hw2visitors;
/*
visitor for finding percentage of positive messages posted
by users in the group and by users in its sub groups
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import hw2.Visitor;
import hw2.User;
import hw2.Group;

public class PositivePercentageVisitor implements Visitor {

    private double positiveMessageCount = 0;
    private double totalMessagesCount = 0;
    private double positivePercentage = 0;
    private List<String> positiveWords= new ArrayList<String>(Arrays.asList("good", "great", "excellent", "nice"));

    @Override
    public void visitUser(User user) {
        //check each message
        for (String message : user.getMyMessages()){
            totalMessagesCount+=1;
            //for each positive word see if the message contains one of them
            for (String positive : positiveWords){
                if (message.toLowerCase().contains(positive.toLowerCase())){
                    positiveMessageCount+=1;
                    //break out of for loop for finding positive words in a message once at most 1 positive word is found
                    break;
                }
            }
        }
    }

    @Override
    public void visitGroup(Group group) {
        //do nothing
    }

    public double getPositivePercentage() {
        //can't divide by 0, if total number of messages is 0
        if (totalMessagesCount ==0) {
            return positivePercentage;
        }
        setPositivePercentage((positiveMessageCount/totalMessagesCount)*100.0);
        return (positivePercentage);
    }

    public void setPositivePercentage(double positivePercentage) {
        this.positivePercentage = positivePercentage;
    }
}
