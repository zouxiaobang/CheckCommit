package com.kyle.commit;

import com.intellij.openapi.util.text.StringUtil;
import com.kyle.commit.utils.TranslationUtil;
import com.kyle.commit.value.OriginElementMessage;

import java.util.Arrays;

/**
 * @author zouxiaobang
 * @date 2021/1/30
 */
public class BackfillMessage {
    public OriginElementMessage translate(String originData) {
        if (StringUtil.isEmpty(originData)) {
            return new OriginElementMessage();
        }

//        String[] messages = originData.split("\n\n");
        String[] messages = originData.split("\r?\n\r?\n");
        return TranslationUtil.translate(Arrays.asList(messages));
    }
}
