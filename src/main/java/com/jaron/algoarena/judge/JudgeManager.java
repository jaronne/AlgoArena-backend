package com.jaron.algoarena.judge;


import com.jaron.algoarena.judge.strategy.DefaultJudgeStrategy;
import com.jaron.algoarena.judge.strategy.JavaLanguageJudgeStrategy;
import com.jaron.algoarena.judge.strategy.JudgeContext;
import com.jaron.algoarena.judge.strategy.JudgeStrategy;
import com.jaron.algoarena.model.dto.questionSubmit.JudgeInfo;
import com.jaron.algoarena.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        // 策略模式
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
