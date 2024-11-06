package com.jaron.algoarena.judge.codesandbox.impl;

import com.jaron.algoarena.judge.codesandbox.CodeSandbox;
import com.jaron.algoarena.judge.codesandbox.model.ExecuteCodeRequest;
import com.jaron.algoarena.judge.codesandbox.model.ExecuteCodeResponse;
import com.jaron.algoarena.model.dto.questionSubmit.JudgeInfo;
import com.jaron.algoarena.model.enums.JudgeInfoMessageEnum;
import com.jaron.algoarena.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        executeCodeResponse.setMessage("执行成功");
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMemory(1000L);
        judgeInfo.setTime(1000l);
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        executeCodeResponse.setJudgeInfo(judgeInfo);
        executeCodeResponse.setOutputList(inputList);
        return executeCodeResponse;
    }
}
