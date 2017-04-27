import java.util.*;

public class Person implements Cloneable
{
    public Person(String name) { this.name = name; }

    public void befriend(Person p) { friends.add(p); }
    public Person getFriend(int i)
    {
        return 0 <= i && i < friends.size() ? friends.get(i) : null;
    }

    public String toString()
    {
        String result = name + ", friends =";
        for (Person f : friends) result += " " + f.name;
        return result;
    }

    @Override
    public Person clone() {
        try {
            Person clonedPerson = (Person) super.clone();
            clonedPerson.friends = new ArrayList<>(friends);
            return clonedPerson;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    private String name;
    private ArrayList<Person> friends = new ArrayList<>();
}
