package com.kyle.commit.widgets.swings;

import com.kyle.commit.value.CommitType;
import com.kyle.commit.value.FieldDefaultText;
import com.kyle.commit.value.FooterType;
import com.kyle.commit.widgets.DefaultTextListener;

import javax.swing.*;

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
        cbType.setSelectedItem(CommitType.TYPE_FEAT);

        // 设置默认文案
        tfScope.addFocusListener(new DefaultTextListener(tfScope, FieldDefaultText.DT_SCORE.getDefaultText()));
        tfSubject.addFocusListener(new DefaultTextListener(tfSubject, FieldDefaultText.DT_SUBJECT.getDefaultText()));
        taBody.addFocusListener(new DefaultTextListener(taBody, FieldDefaultText.DT_BODY.getDefaultText()));
        tfFooter.addFocusListener(new DefaultTextListener(tfFooter, FieldDefaultText.DT_FOOTER.getDefaultText()));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbCompatibleChange);
        buttonGroup.add(rbCloseIssue);
        rbCloseIssue.setSelected(true);
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
        if (!FieldDefaultText.DT_SCORE.getDefaultText().equals(this.tfScope.getText().trim())) {
            return this.tfScope.getText().trim();
        }
        return "";
    }

    @Override
    public String getSubject() {
        if (!FieldDefaultText.DT_SUBJECT.getDefaultText().equals(this.tfSubject.getText().trim())) {
            return this.tfSubject.getText().trim();
        }
        return "";
    }

    @Override
    public String getBody() {
        if (!FieldDefaultText.DT_BODY.getDefaultText().equals(this.taBody.getText().trim())) {
            return this.taBody.getText().trim();
        }
        return "";
    }

    @Override
    public String getFooter() {
        if (!FieldDefaultText.DT_FOOTER.getDefaultText().equals(this.tfFooter.getText().trim())) {
            return this.tfFooter.getText().trim();
        }
        return "";
    }

    @Override
    public FooterType getFooterType() {
        if (this.rbCloseIssue.isSelected()) {
            return FooterType.FOOTER_CLOSE_ISSUE;
        } else if (this.rbCompatibleChange.isSelected()) {
            return FooterType.FOOTER_COMPATIBLE_CHANGE;
        }

        return FooterType.FOOTER_CLOSE_ISSUE;
    }
}
