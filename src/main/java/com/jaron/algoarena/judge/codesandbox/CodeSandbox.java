package com.jaron.algoarena.judge.codesandbox;

import com.jaron.algoarena.judge.codesandbox.model.ExecuteCodeRequest;
import com.jaron.algoarena.judge.codesandbox.model.ExecuteCodeResponse;

public interface CodeSandbox {
    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
