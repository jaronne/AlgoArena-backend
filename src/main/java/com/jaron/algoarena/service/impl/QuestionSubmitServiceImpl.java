package com.jaron.algoarena.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jaron.algoarena.common.ErrorCode;
import com.jaron.algoarena.constant.UserConstant;
import com.jaron.algoarena.exception.ThrowUtils;
import com.jaron.algoarena.mapper.QuestionSubmitMapper;
import com.jaron.algoarena.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.jaron.algoarena.model.entity.QuestionSubmit;
import com.jaron.algoarena.model.entity.User;
import com.jaron.algoarena.model.enums.QuestionSubmitLanguageEnum;
import com.jaron.algoarena.model.enums.QuestionSubmitStatusEnum;
import com.jaron.algoarena.model.vo.QuestionSubmitVO;
import com.jaron.algoarena.service.QuestionService;
import com.jaron.algoarena.service.QuestionSubmitService;
import com.jaron.algoarena.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目提交服务实现
 */
@Service
@Slf4j
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit> implements QuestionSubmitService {

    @Resource
    private UserService userService;

    @Resource
    private QuestionService questionService;

    /**
     * 校验数据
     *
     * @param questionSubmit
     * @param add            对创建的数据进行校验
     */
    @Override
    public void validQuestionSubmit(QuestionSubmit questionSubmit, boolean add) {
        ThrowUtils.throwIf(questionSubmit == null, ErrorCode.PARAMS_ERROR);
        // todo 从对象中取值
        Long questionId = questionSubmit.getQuestionId();
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        // 创建数据时，参数不能为空
        if (add) {
            // todo 补充校验规则
            ThrowUtils.throwIf(questionId == null, ErrorCode.PARAMS_ERROR);
        }
        // 修改数据时，有参数则校验
        // todo 补充校验规则
        if (questionId != null) {
            ThrowUtils.throwIf(questionService.getById(questionId) == null, ErrorCode.PARAMS_ERROR, "题目不存在");
        }
        if (StringUtils.isNotBlank(code)) {
            ThrowUtils.throwIf(code.length() > 8192, ErrorCode.PARAMS_ERROR, "代码过长");
        }
        if (StringUtils.isNotBlank(language)) {
            ThrowUtils.throwIf(QuestionSubmitLanguageEnum.getEnumByValue(language) == null, ErrorCode.PARAMS_ERROR, "不支持该语言");
        }

    }

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }
        // todo 从对象中取值
        Long id = questionSubmitQueryRequest.getId();
        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long userId = questionSubmitQueryRequest.getUserId();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        // todo 补充需要的查询条件
        // 从多字段中搜索
//        if (StringUtils.isNotBlank(searchText)) {
//            // 需要拼接查询条件
//            queryWrapper.and(qw -> qw.like("title", searchText).or().like("content", searchText));
//        }
        // 模糊查询
//        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
//        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);
        // JSON 数组查询
//        if (CollUtil.isNotEmpty(tagList)) {
//            for (String tag : tagList) {
//                queryWrapper.like("tags", "\"" + tag + "\"");
//            }
//        }
        // 精确查询
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(language), "language", language);
        queryWrapper.eq(QuestionSubmitStatusEnum.getEnumByValue(status) != null, "status", status);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        // 排序规则
//        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
//                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
//                sortField);
        return queryWrapper;
    }

    /**
     * 获取题目提交封装
     *
     * @param questionSubmit
     * @param request
     * @return
     */
    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, HttpServletRequest request) {
        // 对象转封装类
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        // region 可选
        // 1. 关联查询用户信息
        Long userId = questionSubmit.getUserId();
//        User user = null;
//        if (userId != null && userId > 0) {
//            user = userService.getById(userId);
//        }
//        UserVO userVO = userService.getUserVO(user);
//        questionSubmitVO.setUser(userVO);
        // endregion
        // 只能看到自己的代码
        User loginUser = userService.getLoginUser(request);
        if (!Objects.equals(loginUser.getId(), userId) && !Objects.equals(loginUser.getUserRole(), UserConstant.ADMIN_ROLE)) {
            questionSubmitVO.setCode(null);
        }
        return questionSubmitVO;
    }

    /**
     * 获取题目提交封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        // 对象转封装类
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        // region 可选
        // 1. 关联查询用户信息
        Long userId = questionSubmit.getUserId();
//        User user = null;
//        if (userId != null && userId > 0) {
//            user = userService.getById(userId);
//        }
//        UserVO userVO = userService.getUserVO(user);
//        questionSubmitVO.setUser(userVO);
        // endregion
        // 只能看到自己的代码
        if (!Objects.equals(loginUser.getId(), userId) && !Objects.equals(loginUser.getUserRole(), UserConstant.ADMIN_ROLE)) {
            questionSubmitVO.setCode(null);
        }
        return questionSubmitVO;
    }

    /**
     * 分页获取题目提交封装
     *
     * @param questionSubmitPage
     * @param request
     * @return
     */
    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, HttpServletRequest request) {
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVO> questionSubmitVOPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(), questionSubmitPage.getTotal());
        if (CollUtil.isEmpty(questionSubmitList)) {
            return questionSubmitVOPage;
        }
        // 对象列表 => 封装对象列表
        // 先查出来登录用户传入，避免传request重复查询
        User loginUser = userService.getLoginUser(request);
        List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream().map(questionSubmit -> {
            return getQuestionSubmitVO(questionSubmit, loginUser);
        }).collect(Collectors.toList());
        // todo 可以根据需要为封装对象补充值，不需要的内容可以删除
        // region 可选
//        // 1. 关联查询用户信息
//        // 批量查出用户信息，比一条一条查询效率更高
//        Set<Long> userIdSet = questionSubmitList.stream().map(QuestionSubmit::getUserId).collect(Collectors.toSet());
//        Map<Long, List<User>> userIdUserListMap = userService.listByIds(userIdSet).stream()
//                .collect(Collectors.groupingBy(User::getId));
//        // 填充信息
//        questionSubmitVOList.forEach(questionSubmitVO -> {
//            Long userId = questionSubmitVO.getUserId();
//            User user = null;
//            if (userIdUserListMap.containsKey(userId)) {
//                user = userIdUserListMap.get(userId).get(0);
//            }
//            questionSubmitVO.setUser(userService.getUserVO(user));
//        });
        // endregion
        questionSubmitVOPage.setRecords(questionSubmitVOList);
        return questionSubmitVOPage;
    }

}
