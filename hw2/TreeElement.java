package hw2;

/*
composite pattern interface
TreeElement should accept visitors in the visitor pattern
 */
public interface TreeElement {
    public String getID();

    public String toString();

    public void accept (Visitor visitor);
}
