package Form;

import View.ResManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HerrSergio on 17.05.2016.
 */
public class MessageForm extends JPanel {
    private JEditorPane textPane = new JEditorPane();
    private JLabel dateLabel = new JLabel();
    private BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

    private Color color;

    private final int MARGIN = 5;
    private final int RADIUS = 15;

    public MessageForm(String text, String date, int width, Color color) {

        setLayout(boxLayout);

//        textPane.setSize(width, Short.MAX_VALUE);
//        textPane.setText(text);
//        textPane.setForeground(Color.WHITE);
//        textPane.setOpaque(false);
//        textPane.setEditable(false);
//        textPane.setMargin(new Insets(MARGIN, MARGIN, MARGIN, MARGIN));

        textPane.setAlignmentX(0.05f);
        add(textPane);

        dateLabel.setAlignmentX(0.0f);
        add(dateLabel);
        textPane.setSize(width, Short.MAX_VALUE);
        textPane.setPreferredSize(new Dimension(width, getContentHeight(text) + MARGIN));
        textPane.setContentType("text/html");
        text = "<font color=\"white\">" + text + "</font>";
        textPane.setText(text);
        textPane.setForeground(Color.WHITE);

        textPane.setOpaque(false);
        textPane.setEditable(false);
        textPane.setMargin(new Insets(MARGIN, MARGIN, MARGIN, MARGIN));

        dateLabel.setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 10));
        dateLabel.setForeground(new Color(0x6A6D6F));
        dateLabel.setText(date);

        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(color);
        graphics.fillRoundRect(textPane.getX(), textPane.getY(), textPane.getWidth(), textPane.getHeight(), RADIUS, RADIUS);
    }

    private static int getContentHeight(String content) {
        JEditorPane dummyEditorPane = new JEditorPane();
        dummyEditorPane.setSize(300, Short.MAX_VALUE);
        dummyEditorPane.setText(content);

        return dummyEditorPane.getPreferredSize().height;
    }
}
