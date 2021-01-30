package com.kyle.commit.widgets.swings;

import com.kyle.commit.value.CommitType;
import com.kyle.commit.value.FieldDefaultText;
import com.kyle.commit.value.FooterType;
import com.kyle.commit.widgets.DefaultTextListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static com.kyle.commit.value.CommitType.TYPE_FEAT;
import static com.kyle.commit.value.CommitType.TYPE_FIX;

/**
 * @author xb.zou
 * @date 2021/1/17
 * @option
 */
public class BuildMessagePanel implements IBuildMessagePanel {
    private JComboBox cbType;
    private JTextField tfScope;
    private JTextField tfSubject;
    private JTextArea taBody;
    private JTextField tfFooter;
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JPanel footerPanel;
    private JRadioButton rbCompatibleChange;
    private JRadioButton rbCloseIssue;
    private JTextField tfJiraCode;
    private JRadioButton rbCloseStory;

    public BuildMessagePanel() {
        CommitType[] commitTypes = CommitType.values();

        // 初始化界面
        initUi(commitTypes);
    }

    private void initUi(CommitType[] commitTypes) {
        // 对提交类型选择进行填充
        for (CommitType commitType : commitTypes) {
            cbType.addItem(commitType);
        }
        cbType.setSelectedItem(TYPE_FEAT);
        cbType.addItemListener(new TypeItemSelectedListener());

        // 设置默认文案
        tfScope.addFocusListener(new DefaultTextListener(tfScope, FieldDefaultText.DT_SCORE.getDefaultText()));
        tfSubject.addFocusListener(new DefaultTextListener(tfSubject, FieldDefaultText.DT_SUBJECT.getDefaultText()));
        taBody.addFocusListener(new DefaultTextListener(taBody, FieldDefaultText.DT_BODY.getDefaultText()));
        tfFooter.addFocusListener(new DefaultTextListener(tfFooter, FieldDefaultText.DT_FOOTER.getDefaultText()));
        tfJiraCode.addFocusListener(new DefaultTextListener(tfJiraCode, FieldDefaultText.DT_JIRA_CODE.getDefaultText()));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbCompatibleChange);
        buttonGroup.add(rbCloseIssue);
        buttonGroup.add(rbCloseStory);
        rbCloseStory.setSelected(true);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public String getType() {
        CommitType commitType = (CommitType) this.cbType.getSelectedItem();
        return commitType.getType();
    }

    @Override
    public String getScope() {
        return getText(FieldDefaultText.DT_SCORE, tfScope);
    }

    @Override
    public String getSubject() {
        return getText(FieldDefaultText.DT_SUBJECT, tfSubject);
    }

    @Override
    public String getBody() {
        return getText(FieldDefaultText.DT_BODY, taBody);
    }

    @Override
    public String getFooter() {
        return getText(FieldDefaultText.DT_FOOTER, tfFooter);
    }

    @Override
    public FooterType getFooterType() {
        if (this.rbCloseIssue.isSelected()) {
            return FooterType.FOOTER_CLOSE_BUG;
        } else if (this.rbCompatibleChange.isSelected()) {
            return FooterType.FOOTER_COMPATIBLE_CHANGE;
        } else if (this.rbCloseStory.isSelected()) {
            return FooterType.FOOTER_CLOSE_STORY;
        }

        return FooterType.FOOTER_CLOSE_BUG;
    }

    @Override
    public String getJiraCode() {
        return getText(FieldDefaultText.DT_JIRA_CODE, tfJiraCode);
    }

    private String getText(FieldDefaultText fieldDefaultText, JTextComponent textComponent) {
        if (!fieldDefaultText.getDefaultText().equals(textComponent.getText().trim())) {
            return textComponent.getText().trim();
        }
        return "";
    }

    private class TypeItemSelectedListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                changeFooterByType((CommitType) e.getItem());
            }
        }

        private void changeFooterByType(CommitType commitType) {
            String type = commitType.getType();
            if (TYPE_FEAT.getType().equals(type)) {
                rbCloseStory.setSelected(true);
            } else if (TYPE_FIX.getType().equals(type)) {
                rbCloseIssue.setSelected(true);
            } else {
                rbCompatibleChange.setSelected(true);
            }
        }
    }
}
