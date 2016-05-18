package View;

import Model.Contacts;
import org.javagram.response.object.UserContact;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Sergey on 15.05.2016.
 */
public class ContactCellRenderer extends JPanel implements ListCellRenderer<Object> {
    private JPanel rootPanel;
    private JLabel fioLable;
    private JLabel topMessageLable;
    private JPanel labelPanel;
    private JPanel contactPane;
    private UserContact user;
    private boolean isSelected;
    Image icon = null;
    private Contacts contacts;
    private String topMessage = "";

    public ContactCellRenderer(Contacts contacts) {
        this.contacts = contacts;
    }

    private void createUIComponents() {
        rootPanel = this;
        labelPanel = new JPanel() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
        contactPane = new JPanel() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
        // TODO: place custom component creation code here
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        user = (UserContact) value;
        this.isSelected = isSelected;

        try {
            BufferedImage image = new BufferedImage(41, 41, BufferedImage.TYPE_INT_ARGB);
            if (user.getPhoto(true) != null) {
                image = ImageIO.read(new ByteArrayInputStream(user.getPhoto(true)));
//            Image newIcon = image.getScaledInstance(41,41,Image.SCALE_SMOOTH);
            }
            icon = image.getScaledInstance(41, 41, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }


        fioLable.setText(value.toString());
        topMessageLable.setText(contacts.getTopMessage(user.getId()));

        Color background;
        Color foreground;
        Border mainBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
        Border border = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY);

        if (isSelected) {
            background = ResManager.getWhiteColor();
            foreground = Color.BLACK;
            border = BorderFactory.createMatteBorder(0, 0, 0, 3, ResManager.getBlueColor());


            // unselected, and not the DnD drop location
        } else {
            background = ResManager.getLightGrayColor();
            foreground = Color.BLACK;
        }

        setBackground(background);
        setBorder(mainBorder);
        labelPanel.setBorder(border);

        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
//        topMessageLable.setText(contacts.getTopMessage(user.getId()));
        super.paintComponent(g);

        g.drawImage(icon, 15, 9, null);

        if (isSelected) {
            if (user.isOnline()) {
                g.drawImage(ResManager.getMaskWhiteOnline(), 15, 9, null);
            } else {
                g.drawImage(ResManager.getMaskWhite(), 15, 9, null);
            }
        } else {
            if (user.isOnline()) {
                g.drawImage(ResManager.getMaskGrayOnline(), 15, 9, null);
            } else {
                g.drawImage(ResManager.getMaskGray(), 15, 9, null);
            }
        }

    }
}
