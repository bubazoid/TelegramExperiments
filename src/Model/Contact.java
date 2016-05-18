package Model;

import java.awt.*;

/**
 * Created by bubnyshev on 19.04.2016.
 */
public class Contact {
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Contact(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLable() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFormatetPhoneNumber() {
        String formattedPhoneNumber = phoneNumber.charAt(0) + " " + phoneNumber.substring(1, 4) + " " +
                phoneNumber.substring(4, 7) + "-" + phoneNumber.substring(7, 9) + "-" + phoneNumber.substring(9, 11);
        return formattedPhoneNumber;
    }


}
