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
    EL_TYPE("提交类型(type)不能为空", true),
    EL_SCOPE("提交范围(scope)不能为空", false),
    EL_SUBJECT("提交简介描述(subject)不能为空", true),
    EL_BODY("提交详细描述(body)不能为空", false),
    EL_FOOTER("提交备注(footer)不能为空", false)
    ;

    private final String elementErrorMessage;
    private final Boolean isNecessary;

    ElementNecessary(String elementType, Boolean isNecessary) {
        elementErrorMessage = elementType;
        this.isNecessary = isNecessary;
    }

    public String getElementErrorMessage() {
        return elementErrorMessage;
    }

    public Boolean getNecessary() {
        return isNecessary;
    }
}
