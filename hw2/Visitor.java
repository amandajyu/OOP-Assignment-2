package hw2;

/*
interface for the visitor in visitor pattern
 */
public interface Visitor {
    void visitUser(User user);
    void visitGroup(Group group);
}
