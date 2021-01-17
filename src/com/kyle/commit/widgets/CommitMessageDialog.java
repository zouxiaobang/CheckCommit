package com.kyle.commit.widgets;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.kyle.commit.widgets.swings.IBuildMessagePanel;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author xb.zou
 * @date 2021/1/17
 * @option 提交消息的对话框 -- 对填写内容进行规定
 */
public class CommitMessageDialog extends DialogWrapper {
    private IBuildMessagePanel buildMessagePanel;

    public CommitMessageDialog(@Nullable Project project, IBuildMessagePanel buildMessagePanel) {
        super(project);

        this.buildMessagePanel = buildMessagePanel;
        setTitle("构建提交信息");
        setOKButtonText("开始构建");
        setCancelButtonText("取消构建");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return buildMessagePanel.getMainPanel();
    }

    public String getCommitMessage() {
        // 判断输入内容

        return null;
    }
}
