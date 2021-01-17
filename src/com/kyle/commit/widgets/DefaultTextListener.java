package com.kyle.commit.widgets;

import com.intellij.ui.JBColor;

import javax.swing.text.JTextComponent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author xb.zou
 * @date 2021/1/17
 * @option 该监听器用于设置默认文案
 */
public class DefaultTextListener implements FocusListener {
    private final JTextComponent textComponent;
    private final String defaultText;

    public DefaultTextListener(JTextComponent textComponent, String defaultText) {
        this.textComponent = textComponent;
        this.defaultText = defaultText;

        setDefaultText();
    }

    @Override
    public void focusGained(FocusEvent e) {
        String text = textComponent.getText();
        if (text.equals(defaultText)) {
            textComponent.setText("");
            textComponent.setForeground(JBColor.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        String text = textComponent.getText();
        if ("".equals(text.trim())) {
            setDefaultText();
        }
    }

    private void setDefaultText() {
        textComponent.setText(defaultText);
        textComponent.setForeground(JBColor.LIGHT_GRAY);
    }
}
