package Form;

import Model.Me;
import temp.SelfUser;
import View.ResManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by bubnyshev on 18.04.2016.
 */
public class ProfileSettings {
    private JPanel rootPanel;
    private JButton backButton;
    private JButton profileSettingsAcceptButton;
    private JFormattedTextField firstNameFTF;
    private JFormattedTextField lastNameFTF;
    private JButton logOffButton;
    private JLabel phoneNumberLable;
    private JPanel panel;
    private JLabel profileTitleLable;
    private SelfUser selfUser;
    private Me me;


    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getProfileSettingsAcceptButton() {
        return profileSettingsAcceptButton;
    }

    public JFormattedTextField getFirstNameFTF() {
        return firstNameFTF;
    }

    public JFormattedTextField getLastNameFTF() {
        return lastNameFTF;
    }

    public JButton getLogOffButton() {
        return logOffButton;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void fillForm(Me me) {
        this.me = me;
        firstNameFTF.setText(me.getFirstName());
        lastNameFTF.setText(me.getLastName());
        phoneNumberLable.setText(me.getFormattedPhoneNumber());
    }

    private void createUIComponents() {
        panel = new JPanel() {
            {
                setBackground(new Color(0, 0, 250, 255));
//                isOptimizedDrawingEnabled();
//                setDoubleBuffered(false);
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
        phoneNumberLable = new JLabel() {
            {
                setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 20));
            }
        };
        profileTitleLable = new JLabel() {
            {
                setFont(ResManager.getSansLight().deriveFont(Font.PLAIN, 36));
            }
        };
        backButton = new JButton() {
            {
                setOpaque(false);
                setBorderPainted(false);
                setContentAreaFilled(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconBack(), 0, 0, null);
            }
        };
        logOffButton = new JButton() {
            {
                setOpaque(false);
                setContentAreaFilled(false);
                setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ResManager.getBlueColor()));
                setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 20));
//                setText("ВЫЙТИ");
            }
        };
        firstNameFTF = new JFormattedTextField() {
            {
                setCaretColor(Color.WHITE);
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
            }
        };
        lastNameFTF = new JFormattedTextField() {
            {
                setCaretColor(Color.WHITE);
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
//                setFont(ResManager.getSansRegular().deriveFont(25));
            }
        };
        profileSettingsAcceptButton = new JButton() {
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
    }

}
