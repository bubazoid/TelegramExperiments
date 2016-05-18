package Model;

import java.awt.image.BufferedImage;

/**
 * Created by HerrSergio on 06.05.2016.
 */
public class Person {

    private int id;
    private String firstName = "";
    private String lastName = "";
    private BufferedImage smallProfilePhoto = new BufferedImage(41, 41, BufferedImage.TYPE_INT_ARGB);


    Person() {

    }

    public String getLable() {
        return firstName + " " + lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public BufferedImage getSmallProfilePhoto() {
        return smallProfilePhoto;
    }

    public void setSmallProfilePhoto(BufferedImage smallProfilePhoto) {
        this.smallProfilePhoto = smallProfilePhoto;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof Person))
            return false;

        Person person = (Person) o;

        return getId() == person.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
