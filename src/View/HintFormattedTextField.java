package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Sergey on 03.05.2016.
 */
public class HintFormattedTextField extends JFormattedTextField implements FocusListener {
    private String hint;
    private Color ghostColor;
    private Color foregroundColor;

    public HintFormattedTextField(String hint, Color ghostColor, Color foregroundColor) {
        super(hint);
        this.ghostColor = ghostColor;
        this.hint = hint;
        this.foregroundColor = foregroundColor;
        super.setForeground(ghostColor);
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (super.getText().equals(hint)) {
            setForeground(foregroundColor);
            super.setText("");

        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (super.getText().isEmpty()) {
            setForeground(ghostColor);
            super.setText(hint);

        }
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

}
