package com.kyle.commit.widgets.swings;

import com.kyle.commit.value.CommitType;
import com.kyle.commit.value.FieldDefaultText;
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
    private ButtonGroup buttonGroup;

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

        buttonGroup = new ButtonGroup();
        buttonGroup.add(rbCompatibleChange);
        buttonGroup.add(rbCloseIssue);
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
        return this.tfScope.getText().trim();
    }

    @Override
    public String getSubject() {
        return this.tfSubject.getText().trim();
    }

    @Override
    public String getBody() {
        return this.taBody.getText().trim();
    }

    @Override
    public String getFooter() {
        return this.tfFooter.getText().trim();
    }
}
