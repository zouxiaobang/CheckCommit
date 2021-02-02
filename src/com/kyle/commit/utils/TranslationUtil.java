package com.kyle.commit.utils;

import com.kyle.commit.value.CommitType;
import com.kyle.commit.value.FooterType;
import com.kyle.commit.value.OriginElementMessage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zouxiaobang
 * @date 2021/2/1
 * todo 后面应做成模板对比
 */
public class TranslationUtil {
    public static OriginElementMessage translate(List<String> messages) {
        OriginElementMessage originElementMessage = new OriginElementMessage();
        messages = messages.stream().map(TranslationUtil::removeFirstWrap).collect(Collectors.toList());
        int lineCount = messages.size();
        if (lineCount < 2) {
            return originElementMessage;
        }

        try {
            parseHeader(originElementMessage, messages.get(0));
        } catch (Exception e) {
            System.err.println("头部解析失败: " + e);
            return originElementMessage;
        }
        if (lineCount > 2) {
            // 有body
            parseBody(originElementMessage, messages.get(1));
        }
        parseFooter(originElementMessage, messages.get(lineCount - 1));

        return originElementMessage;
    }

    private static String removeFirstWrap(String text) {
        while (text.startsWith("\n")) {
            text = text.replaceFirst("\n", "");
        }
        return text;
    }

    /**
     * (Story|Bug|Change) (jira) footer
     */
    private static void parseFooter(OriginElementMessage originElementMessage, String line) {
        String[] typeAndJiraAndFooter = line.split("\\)");
        String type = typeAndJiraAndFooter[0].substring(1).trim();
        originElementMessage.setFooterType(FooterType.parse(type));
        if (typeAndJiraAndFooter.length > 2) {
            String jira = typeAndJiraAndFooter[1].trim().substring(1);
            originElementMessage.setJiraCode(jira);
            String footer = typeAndJiraAndFooter[2].trim();
            originElementMessage.setFooter(footer);
        } else if (typeAndJiraAndFooter.length > 1) {
            if (typeAndJiraAndFooter[1].contains("(")) {
                String jira = typeAndJiraAndFooter[1].trim().substring(1);
                originElementMessage.setJiraCode(jira);
            } else {
                String footer = typeAndJiraAndFooter[1].trim();
                originElementMessage.setFooter(footer);
            }
        }
    }

    /**
     * body
     */
    private static void parseBody(OriginElementMessage originElementMessage, String line) {
        originElementMessage.setBody(line.trim());
    }

    /**
     * type(scope): subject
     */
    private static void parseHeader(OriginElementMessage originElementMessage, String line) throws Exception {
        String[] typeAndSubject = line.split(":");
        String typeAndScope = typeAndSubject[0];
        if (typeAndScope.contains("(")) {
            String type = typeAndScope.substring(0, typeAndScope.indexOf("("));
            String scope = typeAndScope.substring(typeAndScope.indexOf("(") + 1, typeAndScope.length() - 1);
            originElementMessage.setType(CommitType.parse(type));
            originElementMessage.setScope(scope);
        } else {
            originElementMessage.setType(CommitType.parse(typeAndScope));
        }
        String subject = typeAndSubject[1].trim();
        originElementMessage.setSubject(subject);
    }
}
