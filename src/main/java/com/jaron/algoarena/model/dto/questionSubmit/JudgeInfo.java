package com.jaron.algoarena.model.dto.questionSubmit;

import lombok.Data;

/**
 * 判题信息
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存
     */
    private long memory;

    /**
     * 执行时间
     */
    private long time;
}
