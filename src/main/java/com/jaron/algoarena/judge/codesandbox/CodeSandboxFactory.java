package com.jaron.algoarena.judge.codesandbox;

import com.jaron.algoarena.judge.codesandbox.impl.ExampleCodeSandbox;
import com.jaron.algoarena.judge.codesandbox.impl.RemoteCodeSandbox;
import com.jaron.algoarena.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙箱工厂，根据不同的字符串参数返回不同的代码沙箱
 * 静态工厂模式
 */
public class CodeSandboxFactory {
    /**
     * 创建代码沙箱示例
     *
     * @param type 沙箱类型
     * @return
     */
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
