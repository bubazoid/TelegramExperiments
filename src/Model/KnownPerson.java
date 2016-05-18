package Model;

/**
 * Created by HerrSergio on 15.05.2016.
 */
public class KnownPerson extends Person {

    private String phoneNumber = "";

    KnownPerson() {

    }

    public String getFormatetPhoneNumber() {
        String formattedPhoneNumber = phoneNumber.charAt(0) + " " + phoneNumber.substring(1, 4) + " " +
                phoneNumber.substring(4, 7) + "-" + phoneNumber.substring(7, 9) + "-" + phoneNumber.substring(9, 11);
        return formattedPhoneNumber;
    }
    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "KnownPerson{" +
                "phoneNumber='" + phoneNumber + '\'' +
                "} " + super.toString();
    }
}
