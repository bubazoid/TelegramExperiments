package Form;

import View.ResManager;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static String href = "(https?:\\/\\/){1}([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-\\?\\-#&+]*)*\\/?";
    private static Pattern pattern = Pattern.compile(href);
    private static String startHref = "<a color=\"FF00CC\" href=\"";
    private static String middleHref = "\">";
    private static String endHref = "</a>";

    public MessageForm(String text, String date, int width, Color color) {

        setLayout(boxLayout);
        setOpaque(false);
        textPane.setAlignmentX(0.05f);
        add(textPane);

        dateLabel.setAlignmentX(0.0f);
        add(dateLabel);
        textPane.setPreferredSize(new Dimension(width, getContentHeight(text, width) + MARGIN));
        text = decorateLinks(text);
        text = "<font color=\"white\">" + text + "</font>";


        textPane.setContentType("text/html");

        textPane.setText(text);
        textPane.setForeground(Color.WHITE);

        textPane.setOpaque(false);
        textPane.setEditable(false);
        textPane.setMargin(new Insets(MARGIN, MARGIN, MARGIN, MARGIN));

        dateLabel.setFont(ResManager.getSansRegular().deriveFont(Font.PLAIN, 10));
        dateLabel.setForeground(new Color(0x6A6D6F));
        dateLabel.setText(date);

        this.color = color;
        textPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    openLink(e);
                }

            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(color);
        graphics.fillRoundRect(textPane.getX(), textPane.getY(), textPane.getWidth(), textPane.getHeight(), RADIUS, RADIUS);
    }

    private static int getContentHeight(String content, int width) {
        JEditorPane dummyEditorPane = new JEditorPane();
        dummyEditorPane.setSize(width, Short.MAX_VALUE);
        dummyEditorPane.setText(content);

        return dummyEditorPane.getPreferredSize().height;
    }

    private static String decorateLinks(String text) {
        Matcher matcher = pattern.matcher(text);
        String temp = "";
        System.out.println(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            temp = temp + text.substring(0, matcher.start()) + startHref + text.substring(start, end) +
                    middleHref + text.substring(start, end) + endHref;
            text = text.substring(end);
            matcher = pattern.matcher(text);
        }
        return temp + text;
    }

    private void openLink(HyperlinkEvent e) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(e.getURL().toURI());
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        }
    }

}
