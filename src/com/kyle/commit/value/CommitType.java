package com.kyle.commit.value;

import com.intellij.openapi.util.text.StringUtil;

/**
 * @author xb.zou
 * @date 2021/1/17
 * @option 提交信息的类型，指定
 */
public enum CommitType {
    /**
     * 提交类型
     */
    TYPE_FEAT("feat", "新增功能", ""),
    TYPE_FIX("fix", "修改Bug", ""),
    TYPE_DOCS("docs", "修改文档", "如修改了README.md"),
    TYPE_STYLE("style", "对格式进行修改", "如果缩进、空格等"),
    TYPE_REFACTOR("refactor", "重构", ""),
    TYPE_BUG_PERF("bug perf", "优化", "如提升性能，用户体验等"),
    TYPE_TEST("test", "测试用例", ""),
    TYPE_CHORE("chore", "工具", "如添加依赖、工具等"),
    TYPE_REVERT("revert", "版本回滚", "")
    ;

    private final String type;
    private final String typeDesc;
    private final String typeExplanation;

    CommitType(String type, String typeDesc, String typeExplanation) {
        this.type = type;
        this.typeDesc = typeDesc;
        this.typeExplanation = typeExplanation;
    }


    public String getType() {
        return type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public String getTypeExplanation() {
        return typeExplanation;
    }

    @Override
    public String toString() {
        String first = this.type + " -- " + this.typeDesc;
        String second = StringUtil.isEmpty(this.typeExplanation) ? "" : " (" + this.typeExplanation + ")";
        return first + second;
    }
}
