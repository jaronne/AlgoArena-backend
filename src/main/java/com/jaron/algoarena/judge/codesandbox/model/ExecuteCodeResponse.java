package com.jaron.algoarena.judge.codesandbox.model;

import com.jaron.algoarena.model.dto.questionSubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    /**
     * 执行状态
     */
    private Integer status;

    /**
     * 接口信息
     */
    private String message;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 输出
     */
    private List<String> outputList;
}
