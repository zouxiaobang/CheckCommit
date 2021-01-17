package com.kyle.commit.value;

/**
 * @author xb.zou
 * @date 2021/1/17
 * @option
 */
public enum ElementNecessary {
    /**
     * 各个元素是否必填
     */
    EL_TYPE("TYPE", true),
    EL_SCOPE("SCOPE", false),
    EL_SUBJECT("SUBJECT", true),
    EL_BODY("BODY", false),
    EL_FOOTER("FOOTER", false)
    ;

    private final String ElementType;
    private final Boolean isNecessary;

    ElementNecessary(String elementType, Boolean isNecessary) {
        ElementType = elementType;
        this.isNecessary = isNecessary;
    }

    public String getElementType() {
        return ElementType;
    }

    public Boolean getNecessary() {
        return isNecessary;
    }
}
