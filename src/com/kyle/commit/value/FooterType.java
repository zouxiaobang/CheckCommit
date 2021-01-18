package com.kyle.commit.value;

/**
 * @author xb.zou
 * @date 2021/1/17
 * @option
 */
public enum FooterType {
    /**
     * 关闭issue
     */
    FOOTER_CLOSE_ISSUE("Closes: "),
    /**
     * 不兼容变动
     */
    FOOTER_COMPATIBLE_CHANGE("BREAKING CHANGE: ");

    private final String displayText;


    FooterType(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
