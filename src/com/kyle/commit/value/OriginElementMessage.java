package com.kyle.commit.value;

/**
 * @author zouxiaobang
 * @date 2021/1/30
 */
public class OriginElementMessage {
    private CommitType type;
    private String scope;
    private String subject;
    private String body;
    private FooterType footerType;
    private String jiraCode;
    private String footer;

    public CommitType getType() {
        return type;
    }

    public void setType(CommitType type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public FooterType getFooterType() {
        return footerType;
    }

    public void setFooterType(FooterType footerType) {
        this.footerType = footerType;
    }

    public String getJiraCode() {
        return jiraCode;
    }

    public void setJiraCode(String jiraCode) {
        this.jiraCode = jiraCode;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}
