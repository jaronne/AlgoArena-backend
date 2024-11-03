package com.jaron.algoarena.model.dto.question;

import lombok.Data;

/**
 * 判题用例
 */
@Data
public class JudgeCase {

    /**
     * 输入
     */
    private String input;

    /**
     * 输出
     */
    private String output;

}
