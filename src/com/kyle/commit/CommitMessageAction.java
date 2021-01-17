package com.kyle.commit;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vcs.CommitMessageI;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.vcs.ui.Refreshable;
import com.kyle.commit.widgets.CommitMessageDialog;
import com.kyle.commit.widgets.swings.BuildMessagePanel;
import org.jetbrains.annotations.NotNull;


/**
 * @author xb.zou
 * @date 2021/1/17
 * @option 创建符合Angular规范的提交信息的Action
 */
public class CommitMessageAction extends AnAction implements DumbAware {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        CommitMessageI commitMessagePanel = this.commitPanel(event);
        if (commitMessagePanel == null) {
            return;
        }

        // 创建提交信息的对话框
        CommitMessageDialog commitMessageDialog = new CommitMessageDialog(event.getProject(), new BuildMessagePanel());
        commitMessageDialog.show();
        if (commitMessageDialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            if (!commitMessageDialog.checkMessage()) {

            } else {
                commitMessagePanel.setCommitMessage(commitMessageDialog.getCommitMessage());
            }
        }
    }

    /**
     * 获取Commit对话框中的CommitMessage输入框 -- CommitMessage
     * 显示方式：
     * 1、菜单栏中VCS菜单 -> Commit...
     * 2、工具栏中绿色打勾的图像
     * 3、快捷键：Ctrl+K
     *
     * @return CommitMessageI: CommitMessage的顶层接口，输入提交信息的输入框
     */
    private CommitMessageI commitPanel(@NotNull AnActionEvent event) {
        // 如果当前页面为CheckinPanel
        Refreshable refreshable = Refreshable.PANEL_KEY.getData(event.getDataContext());
        if (refreshable instanceof CommitMessageI) {
            // CheckinProjectPanel extends Refreshable, CommitMessageI
            return (CommitMessageI) refreshable;
        }

        return VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(event.getDataContext());
    }
}
