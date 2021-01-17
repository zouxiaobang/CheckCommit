package com.kyle.commit.value;

/**
 * @author xb.zou
 * @date 2021/1/17
 * @option 输入框的默认文案
 */
public enum FieldDefaultText {
    /**
     * 默认文案
     */
    DT_SCORE("用于说明影响的范围(如service、spi、web...)"),
    DT_SUBJECT("修改目的的简要描述，不超过50个字符，且不能以'.'结尾(一般使用jira中的标题)"),
    DT_BODY("对修改内容描述的补充(可包含：1、修改内容的必要性；2、如何解决该问题；3、影响的地方... 不做强制要求)"),
    DT_FOOTER("备注信息(如果为关闭Issue或完成需求，请填写jira中的编号；如果为不兼容变动，请填写变动描述、变动理由和迁移方法)")
    ;

    private final String defaultText;

    FieldDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public String getDefaultText() {
        return defaultText;
    }
}
