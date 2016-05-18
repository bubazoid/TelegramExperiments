package Form;

import Model.Contact;
import View.ResManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sergey on 17.04.2016.
 */
public class EditContact {
    private JPanel rootPanel;
    private JButton editContactAcceptButton;
    private JButton backButton;
    private JFormattedTextField fioFTF;
    private JButton deleteUserButton;
    private JLabel phoneNumberLabel;
    private JPanel panel;
    private JLabel editContactTitle;
    private JPanel fioPanel;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getDeleteUserButton() {
        return deleteUserButton;
    }

    public JButton getEditContactAcceptButton() {
        return editContactAcceptButton;
    }

    public JFormattedTextField getFioFTF() {
        return fioFTF;
    }

    public JLabel getPhoneNumberLabel() {
        return phoneNumberLabel;
    }


    private void createUIComponents() {
        panel = new JPanel() {
            {
                setBackground(new Color(0, 0, 255, 255));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
//                g.drawImage(ResManager.getBackground(),0,0,null);
                Graphics2D g2d = (Graphics2D) g.create();
                // 50% transparent Alpha
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
                g2d.setColor(getBackground());
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();

            }
        };
        editContactTitle = new JLabel() {
            {
                setFont(ResManager.getSansLight().deriveFont(Font.PLAIN, 36));
            }
        };
        backButton = new JButton() {
            {
                setOpaque(false);
                setContentAreaFilled(false);
                setBorderPainted(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconBack(), 0, 0, null);
            }
        };
        editContactAcceptButton = new JButton() {
            {
                setContentAreaFilled(false);
                setBorderPainted(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getButtonBackground(), 0, 0, null);
                g.setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 20));
                g.setColor(Color.WHITE);
                g.drawString("СОХРАНИТЬ", 100, 37);
            }
        };
        deleteUserButton = new JButton() {
            {
                setBorder(BorderFactory.createLineBorder(Color.RED, 2, false));
                setOpaque(false);
                setContentAreaFilled(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconTrash(), 7, 5, null);
            }
        };
        fioPanel = new JPanel() {
            {
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
            }
        };
        fioFTF = new JFormattedTextField() {
            {
                setCaretColor(Color.WHITE);
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
    }

    public void setContact(Contact selectedUser) {
        phoneNumberLabel.setText(selectedUser.getPhoneNumber());
        fioFTF.setText(selectedUser.getFirstName() + " " + selectedUser.getLastName());
    }
}
