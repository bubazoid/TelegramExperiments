package Form;

import View.ResManager;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

/**
 * Created by Sergey on 10.04.2016.
 */
public class PhoneVerifyForm {

    private JPanel rootPanel;
    private JPasswordField phoneVerifyCodeField;
    private JButton phoneCodeAcceptButton;
    private JTextPane наДанныйНомерТелефонаTextPane;
    private JLabel phoneNumberField;
    private JPanel panel;
    private JPanel codePanel;
    private JPanel logoPanel;

    public PhoneVerifyForm() throws ParseException {


    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JPasswordField getPhoneVerifyCodeField() {
        return phoneVerifyCodeField;
    }

    public JButton getPhoneCodeAcceptButton() {
        return phoneCodeAcceptButton;
    }

    public JLabel getPhoneNumberField() {
        return phoneNumberField;
    }

    public void clearFields() {
        phoneVerifyCodeField.setText("");
    }

    private void createUIComponents() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getBackground(), 0, 0, panel.getWidth(), panel.getHeight(), null);
            }
        };
        phoneCodeAcceptButton = new JButton() {
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
                g.drawString("ПРОДОЛЖИТЬ", 100, 37);

            }
        };// TODO: place custom component creation code here
        logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getLogoMini(), 0, 0, null);
            }
        };
        codePanel = new JPanel() {
            {
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconLock(), 5, 7, null);
            }
        };
        phoneVerifyCodeField = new JPasswordField(5) {
            {
                setCaretColor(Color.WHITE);
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
    }
}
