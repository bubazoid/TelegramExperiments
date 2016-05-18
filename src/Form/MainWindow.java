package Form;

import Model.SelfUser;
import temp.DialogCellRenderer;
import View.ResManager;
import org.javagram.response.object.Message;
import org.javagram.response.object.MessagesMessage;
import org.javagram.response.object.UserContact;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sergey on 10.04.2016.
 */
public class MainWindow {
    private JPanel rootPanel;
    private JLabel label;
    private ContactListPane contactListPane;
    private JLabel userFioLable;
    private JTextField findContactField;
    private JButton profileSettingsButton;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JButton editContactButton;
    private JLabel contactNameLable;
    private JButton addContactButton;
    //    private SelfUser selfUser;
    private JList contactList;
    private JPanel userPanel;

    private JPanel userIconPanel;
    private JTextField messegeField;
    private JButton sendButton;
    private JPanel contactPanel;
    private JList dialogList;
    private JScrollPane dialogScrollPane;
    private JPanel contactIconPanel;
    private JPanel dialogPanel;
    private JPanel messegeDialogPanel;
    private SelfUser user;
    private Image userIconImage;
    private Image contactIconImage;

    public MainWindow() {
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.add(Box.createGlue());
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public JLabel getLabel() {
        return label;
    }

    public JLabel getContactNameLable() {
        return contactNameLable;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JList getContactList() {
        return contactList;
    }

    public JLabel getUserFioLable() {
        return userFioLable;
    }


    public JButton getAddContactButton() {
        return addContactButton;
    }

    public JTextField getFindContactField() {
        return findContactField;
    }

    public JList getDialogList() {
        return dialogList;
    }

    public JButton getEditContactButton() {
        return editContactButton;
    }

    public JButton getProfileSettingsButton() {
        return profileSettingsButton;
    }

    private void createUIComponents() {
        userPanel = new JPanel() {
            {
                setBackground(ResManager.getBlueColor());
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getLogoMicro(), 15, 10, null);

            }
        };
        profileSettingsButton = new JButton() {
            {
                setContentAreaFilled(false);
                setBorderPainted(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconSettings(), 0, 0, null);

            }
        };
        editContactButton = new JButton() {
            {
                setContentAreaFilled(false);
                setBorderPainted(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconEdit(), 0, 0, null);
            }
        };

        userIconPanel = new JPanel() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (userIconImage != null) {
                    g.drawImage(userIconImage, 0, 0, null);
                    g.drawImage(ResManager.getMaskBlueMini(), 0, 0, null);

                }
            }
        };
        contactIconPanel = new JPanel() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (contactIconImage != null) {
                    g.drawImage(contactIconImage, 0, 0, null);
                    g.drawImage(ResManager.getMaskWhiteMini(), 0, 0, null);

                }
            }
        };
        contactPanel = new JPanel() {
            {
                setBorder(BorderFactory.createLineBorder(ResManager.getGrayColor(), 1));
            }
        };

//
        sendButton = new JButton() {
            {
                setContentAreaFilled(false);
                setBorderPainted(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getButtonSend(), 0, 0, null);
            }
        };

        messegeField = new JTextField() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }
        };

        dialogScrollPane = new JScrollPane() {
            {
                JScrollBar vertical = getVerticalScrollBar();
                vertical.setPreferredSize(new Dimension(0, 0));
                setBorder(BorderFactory.createEmptyBorder());
                setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//                setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            }
        };
        dialogList = new JList() {
            {
                setBorder(BorderFactory.createEmptyBorder());
            }
        };

//        dialogPanel = new JPanel(){
//            {
//                setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
//                add(Box.createGlue());
//            }
//        };


    }

    public void setMessages(ArrayList<MessagesMessage> messages) {
        dialogPanel.removeAll();
        if (messages != null) {
            for (int i = messages.size(); i > 0; i--) {
                Message message = messages.get(i - 1);
                JPanel panel = new JPanel() {
                    @Override
                    public Dimension getMaximumSize() {
                        //Исправляем погань, на которую способен только BoxLayout
                        //Разрешаем растягиваться только по горизонтали
                        Dimension maxSize = super.getMaximumSize();
                        Dimension prefSize = super.getPreferredSize();
                        return new Dimension(maxSize.width, prefSize.height);
                    }
                };
                panel.setLayout(new FlowLayout(message.isOut() ? FlowLayout.RIGHT : FlowLayout.LEFT));
                panel.add(new MessageForm(message.getMessage(), message.getDate().toString(), 300,
                        message.isOut() ? new Color(0x4C41AC) : new Color(0x00A8DA)));
                dialogPanel.add(panel);
            }

        }
        dialogScrollPane.getVerticalScrollBar().setValue(dialogScrollPane.getHeight());
    }

    public void setUser(SelfUser user) {
        this.user = user;
        userFioLable.setText(user.getLable());
        userIconImage = user.getIconImage();

    }

    public void setContact(UserContact contact) {
        contactIconImage = null;
        contactNameLable.setText(contact.toString());
        try {
            BufferedImage buffImage = new BufferedImage(29, 29, BufferedImage.TYPE_INT_ARGB);
            if (contact.getPhoto(true) != null) {
                buffImage = ImageIO.read(new ByteArrayInputStream(contact.getPhoto(true)));
            }
            contactIconImage = buffImage.getScaledInstance(29, 29, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contactIconPanel.repaint();

    }

}
