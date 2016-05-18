package Form;

import View.ResManager;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.ParseException;

/**
 * Created by Sergey on 10.04.2016.
 */
public class LoginForm {
    private JPanel rootPane;
    private JButton phoneNumberAcceptButton;
    private JFormattedTextField phoneNumberField;
    private JTextPane введитеКодСтраныИTextPane;
    private JPanel panel;
    private JPanel logoLable;
    private JPanel phoneNumberPanel;
    private ResManager resManager;
    private BufferedImage background;


    public LoginForm() throws ParseException {

//        MaskFormatter displayFormat = new MaskFormatter("# ### ### ## ##");
//        MaskFormatter editFormat = new MaskFormatter("# ### ### ## ##");
        MaskFormatter formatter = new MaskFormatter("# ### ### ## ##");
        DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter,
                formatter, formatter);
        phoneNumberField.setFormatterFactory(factory);
    }

    public JPanel getRootPane() {
        return rootPane;
    }

    public JButton getPhoneNumberAcceptButton() {
        return phoneNumberAcceptButton;
    }

    public JFormattedTextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public void clearFields() {
        phoneNumberField.setText("");
    }


    private void createUIComponents() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getBackground(), 0, 0, panel.getWidth(), panel.getHeight(), null);
            }
        };// TODO: place custom component creation code here
        phoneNumberAcceptButton = new JButton() {
            {
                setContentAreaFilled(false);
                setBorderPainted(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getButtonBackground(), 0, 0, null);
                g.setFont(ResManager.getSansLight().deriveFont(Font.PLAIN, 20));
                g.setColor(Color.WHITE);
                g.drawString("ПРОДОЛЖИТЬ", 100, 37);
            }
        };
        phoneNumberField = new JFormattedTextField() {
            {
                setCaretColor(Color.WHITE);
                setBorder(BorderFactory.createEmptyBorder());
            }
        };
        logoLable = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getLogo(), 0, 0, null);
            }
        };
        phoneNumberPanel = new JPanel() {
            {
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ResManager.getIconPhone(), 10, 5, null);
                g.setColor(Color.WHITE);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(40, 25, 55, 25);
                g2d.drawLine(48, 18, 48, 33);
                g2d.dispose();
            }
        };

    }
}
