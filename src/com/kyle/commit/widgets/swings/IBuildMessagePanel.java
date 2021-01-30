package com.kyle.commit.widgets.swings;

import com.kyle.commit.value.FooterType;

import javax.swing.*;

/**
 * @author xb.zou
 * @date 2021/1/17
 * @option 构建提交信息的界面接口
 */
public interface IBuildMessagePanel {
    /**
     * 获取主界面
     */
    JPanel getMainPanel();

    /**
     * 获取提交类型
     */
    String getType();

    /**
     * 获取影响范围
     */
    String getScope();

    /**
     * 获取简要信息
     */
    String getSubject();

    /**
     * 获取详细信息
     */
    String getBody();

    /**
     * 获取备注信息
     */
    String getFooter();

    /**
     * true：关闭issue
     * false：不兼容变动
     */
    FooterType getFooterType();

    /**
     * 获取Jira编码
     */
    String getJiraCode();
}
