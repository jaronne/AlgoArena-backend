package com.jaron.algoarena.judge.strategy;

import com.jaron.algoarena.model.dto.questionSubmit.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {

    /**
     * 执行判题（给 JudgeInfo 加上 message，沙箱只返回时间和内存，这里会和时间和内存限制作比较）
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
