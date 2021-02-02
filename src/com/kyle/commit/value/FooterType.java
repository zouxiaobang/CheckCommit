package com.kyle.commit.value;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xb.zou
 * @date 2021/1/17
 * @option
 */
public enum FooterType {
    /**
     * 关闭issue
     */
    FOOTER_CLOSE_BUG("bug"),
    FOOTER_CLOSE_STORY("story"),
    /**
     * 不兼容变动
     */
    FOOTER_COMPATIBLE_CHANGE("change");

    private final String displayText;


    FooterType(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }

    public static FooterType parse(String displayText) {
        List<FooterType> footerTypes = Arrays.stream(FooterType.values())
                .filter(footerType -> footerType.getDisplayText().equalsIgnoreCase(displayText))
                .collect(Collectors.toList());
        return CollectionUtils.isEmpty(footerTypes) ? null : footerTypes.get(0);
    }
}
