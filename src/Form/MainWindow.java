package Form;

import Model.Contact;
import Model.Me;
import temp.SelfUser;
import View.ResManager;


import javax.swing.*;
import java.awt.*;
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
    private Me me;

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

    public void setMessages(ArrayList<Model.Message> messages) {
        dialogPanel.removeAll();
        if (messages != null) {
            for (int i = messages.size(); i > 0; i--) {
                Model.Message message = messages.get(i - 1);
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
                panel.add(new MessageForm(message.getText(), message.getDate().toString(), 300,
                        message.isOut() ? new Color(0x4C41AC) : new Color(0x00A8DA)));
                dialogPanel.add(panel);
            }

        }
        dialogScrollPane.getVerticalScrollBar().setValue(dialogScrollPane.getHeight());
    }

    public void setUser(Me user) {
        this.me = user;
        userFioLable.setText(me.getLable());
        userIconImage = me.getSmallProfilePhoto().getScaledInstance(29, 29, Image.SCALE_SMOOTH);

    }

    public void setContact(Contact contact) {
        contactIconImage = null;
        contactNameLable.setText(contact.getLable());

        if (contact.getSmallProfilePhoto() != null) {
            contactIconImage = contact.getSmallProfilePhoto().getScaledInstance(29, 29, Image.SCALE_SMOOTH);
            }


        contactIconPanel.repaint();

    }

}
