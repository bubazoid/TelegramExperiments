package temp;

import View.ResManager;
import org.javagram.response.object.Message;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sergey on 15.05.2016.
 */
public class DialogCellRenderer extends JPanel implements ListCellRenderer<Object> {
    private JPanel rootPanel;
    private JPanel messegePanel;
    private JEditorPane messageTextPane;
    private JLabel timeLabel;
    private JPanel messagePanel;
    private JPanel messageDialogPanel;
    private JPanel leftMessagePanel;
    private JPanel rightMessagePanel;
    private Message message;
    //    public DialogCellRenderer() {
//        setOpaque(true);
//    }


    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        message = (Message) value;
        Color background;
        Color foreground = Color.WHITE;
        int contentSize = getContentHeight(message.getMessage()) + 17;
//        messageTextPane.setPreferredSize(new Dimension(308, contentSize));
////        messageTextPane.setMaximumSize(new Dimension(308,contentSize));
//        messageTextPane.setMaximumSize(null);
        messageTextPane.setSize(308, Short.MAX_VALUE);
        messageTextPane.setText(message.getMessage());
        messagePanel.setPreferredSize(null);
        messageTextPane.setOpaque(false);
        messageTextPane.setEditable(false);
        messageDialogPanel.setPreferredSize(null);
        setPreferredSize(null);


        if (((Message) value).isOut()) {
            background = ResManager.getOutMessageColor();
            messageDialogPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

            timeLabel.setHorizontalTextPosition(JLabel.RIGHT);


        } else {
            background = ResManager.getInMessageColor();
            messageDialogPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            timeLabel.setHorizontalTextPosition(JLabel.RIGHT);

        }
//        foreground = Color.black;
        timeLabel.setText(message.getDate().toString());
        messageTextPane.setBackground(background);
        messageTextPane.setForeground(foreground);
//        setMaximumSize(new Dimension(0,contentSize+22));
//        setPreferredSize(new Dimension(0,contentSize+22));
        return this;
    }

    private void createUIComponents() {
        rootPanel = this;// TODO: place custom component creation code here
//        messagePanel = new JPanel(){
//            {
////                setPreferredSize(new Dimension(308,contentSize));
//            }
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                if (message.isOut()) {
//                    g.drawImage(ResManager.getMessageOutRight(),getWidth()-13,(getHeight()-17)/2,null);
//                } else {
//                    g.drawImage(ResManager.getMessageInLeft(),5,(getHeight()-17)/2,null);
//                }
//
//            }
//        };
        leftMessagePanel = new JPanel() {
            {
//                setPreferredSize(new Dimension(308,contentSize));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!message.isOut()) {
                    g.drawImage(ResManager.getMessageInLeft(), 0, (getHeight() - 12) / 2, null);
                }

            }
        };
        rightMessagePanel = new JPanel() {
            {
//                setPreferredSize(new Dimension(308,contentSize));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (message.isOut()) {
                    g.drawImage(ResManager.getMessageOutRight(), 0, (getHeight() / 2) - 6, null);
                }
            }
        };
        messageTextPane = new JEditorPane() {
            {
//                setPreferredSize(new Dimension(308,contentSize));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (message.isOut()) {
                    g.drawImage(ResManager.getMessageOutTop(), 0, 0, null);
                    g.drawImage(ResManager.getMessageOutBottom(), 0, getHeight() - ResManager.getMessageOutBottom().getHeight(), null);

                } else {
                    g.drawImage(ResManager.getMessageInTop(), 0, 0, null);
                    g.drawImage(ResManager.getMessageInBottom(), 0, getHeight() - ResManager.getMessageInBottom().getHeight(), null);

                }

            }
        };


    }

    private static int getContentHeight(String content) {
        JEditorPane dummyEditorPane = new JEditorPane();
        dummyEditorPane.setSize(300, Short.MAX_VALUE);
        dummyEditorPane.setText(content);

        return dummyEditorPane.getPreferredSize().height;
    }
}
