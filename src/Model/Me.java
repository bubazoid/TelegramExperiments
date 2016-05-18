package Model;

import org.javagram.response.object.UserSelf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by HerrSergio on 06.05.2016.
 */
public class Me extends KnownPerson {

    public Me(String lastName, String firstName, String phoneNumber, int id) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
    }

    Me(UserSelf userSelf) {
        this(userSelf.getLastName(), userSelf.getFirstName(), userSelf.getPhone(), userSelf.getId());
        BufferedImage buffImage = new BufferedImage(41, 41, BufferedImage.TYPE_INT_ARGB);
        try {
            if (userSelf.getPhoto(true) != null) {
                setSmallProfilePhoto(ImageIO.read(new ByteArrayInputStream(userSelf.getPhoto(true))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Me{} " + super.toString();
    }
}
