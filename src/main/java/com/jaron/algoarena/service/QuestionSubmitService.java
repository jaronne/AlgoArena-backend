package com.jaron.algoarena.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jaron.algoarena.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.jaron.algoarena.model.entity.QuestionSubmit;
import com.jaron.algoarena.model.vo.QuestionSubmitVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交服务
 *
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 校验数据
     *
     * @param questionSubmit
     * @param add 对创建的数据进行校验
     */
    void validQuestionSubmit(QuestionSubmit questionSubmit, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);
    
    /**
     * 获取题目提交封装
     *
     * @param questionSubmit
     * @param request
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, HttpServletRequest request);

    /**
     * 分页获取题目提交封装
     *
     * @param questionSubmitPage
     * @param request
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, HttpServletRequest request);
}
