package com.kyle.commit.widgets;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.util.text.StringUtil;
import com.kyle.commit.value.ElementNecessary;
import com.kyle.commit.value.FooterType;
import com.kyle.commit.widgets.swings.IBuildMessagePanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author xb.zou
 * @date 2021/1/17
 * @option 提交消息的对话框 -- 对填写内容进行规定
 */
public class CommitMessageDialog extends DialogWrapper {
    private final static String CHAR_POINT = ".";
    private final static String CHAR_POINT_CN = "。";
    private final static int LENGTH_SUBJECT = 50;

    private final IBuildMessagePanel buildMessagePanel;

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

    @NotNull
    @Override
    protected Action[] createActions() {
        DialogWrapperExitAction cancelAction = new DialogWrapperExitAction("取消构建", CANCEL_EXIT_CODE);
        CustomOkAction okAction = new CustomOkAction("开始构建");
        // 设置默认获取焦点
        okAction.putValue(DialogWrapper.DEFAULT_ACTION, true);

        return new Action[]{okAction, cancelAction};
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        // 必要性判断
        if (ElementNecessary.EL_TYPE.isNecessary() && StringUtil.isEmpty(buildMessagePanel.getType())) {
            return new ValidationInfo(ElementNecessary.EL_TYPE.getElementErrorMessage());
        }

        if (ElementNecessary.EL_SCOPE.isNecessary() && StringUtil.isEmpty(buildMessagePanel.getScope())) {
            return new ValidationInfo(ElementNecessary.EL_SCOPE.getElementErrorMessage());
        }

        if (ElementNecessary.EL_SUBJECT.isNecessary() && StringUtil.isEmpty(buildMessagePanel.getSubject())) {
            return new ValidationInfo(ElementNecessary.EL_SUBJECT.getElementErrorMessage());
        }

        if (ElementNecessary.EL_BODY.isNecessary() && StringUtil.isEmpty(buildMessagePanel.getBody())) {
            return new ValidationInfo(ElementNecessary.EL_BODY.getElementErrorMessage());
        }

        if (ElementNecessary.EL_FOOTER.isNecessary() && StringUtil.isEmpty(buildMessagePanel.getFooter())) {
            return new ValidationInfo(ElementNecessary.EL_FOOTER.getElementErrorMessage());
        }

        // 填写格式判断(如末尾字符、字数等)
        if (buildMessagePanel.getSubject().endsWith(CHAR_POINT) || buildMessagePanel.getSubject().endsWith(CHAR_POINT_CN)) {
            return new ValidationInfo("提交简要描述(subject)不能以\".\"或\"。\"结尾");
        }

        if (buildMessagePanel.getSubject().length() > LENGTH_SUBJECT) {
            return new ValidationInfo("提交简要描述(subject)的长度不能超过50");
        }

        boolean isBugOrStory = buildMessagePanel.getFooterType() == FooterType.FOOTER_CLOSE_BUG
                || buildMessagePanel.getFooterType() == FooterType.FOOTER_CLOSE_STORY;
        if (isBugOrStory && StringUtil.isEmpty(buildMessagePanel.getJiraCode())) {
            return new ValidationInfo(ElementNecessary.EL_JIRA_CODE.getElementErrorMessage());
        }

        return null;
    }

    public String getCommitMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        // 构建Header
        String type = buildMessagePanel.getType();
        if (StringUtil.isNotEmpty(type)) {
            stringBuilder.append(type);
        }

        String scope = buildMessagePanel.getScope();
        if (StringUtil.isNotEmpty(scope)) {
            stringBuilder.append("(").append(scope).append(")");
        }

        String subject = buildMessagePanel.getSubject();
        if (StringUtil.isNotEmpty(subject)) {
            stringBuilder.append(": ").append(subject).append("\n\n");
        }

        // 构建Body
        String body = buildMessagePanel.getBody();
        if (StringUtil.isNotEmpty(body)) {
            stringBuilder.append("body: ").append(body).append("\n\n");
        }

        // 构建Footer
        FooterType footerType = buildMessagePanel.getFooterType();
        stringBuilder.append("(").append(footerType.getDisplayText()).append(")");
        String jiraCode = buildMessagePanel.getJiraCode();
        if (StringUtil.isNotEmpty(jiraCode)) {
            stringBuilder.append("(").append(jiraCode).append(")");
        }
        String footer = buildMessagePanel.getFooter();
        if (StringUtil.isNotEmpty(footer)) {
            stringBuilder.append(" ").append(footer);
        }

        return stringBuilder.toString();
    }

    private class CustomOkAction extends DialogWrapperAction {
        protected CustomOkAction(String name) {
            super(name);
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            ValidationInfo validationInfo = doValidate();
            if (validationInfo != null) {
                // 检验不通过
                Messages.showMessageDialog(validationInfo.message, "检验不通过", Messages.getWarningIcon());
            } else {
                close(OK_EXIT_CODE);
            }
        }
    }
}
