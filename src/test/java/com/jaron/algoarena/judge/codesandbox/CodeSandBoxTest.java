package com.jaron.algoarena.judge.codesandbox;

import com.jaron.algoarena.judge.codesandbox.impl.ExampleCodeSandbox;
import com.jaron.algoarena.judge.codesandbox.model.ExecuteCodeRequest;
import com.jaron.algoarena.judge.codesandbox.model.ExecuteCodeResponse;
import com.jaron.algoarena.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CodeSandboxTest {

    @Test
    void executeCode() {
        CodeSandbox codeSandbox = new ExampleCodeSandbox();
        String code = "int main() { }";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
}
