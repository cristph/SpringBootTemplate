package com.cristph.template.web.controller;


import com.cristph.template.constraint.Code;
import com.cristph.template.constraint.Response;
import com.cristph.template.core.pojos.dto.BaseDTO;
import com.cristph.template.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2017/8/15.
 */
public class BaseController<DTO extends BaseDTO> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //判断整数大于0
    protected boolean isIntegerParamPositive(Integer... integers) {
        boolean legal = Arrays.stream(integers).anyMatch(integer -> integer == null);
        if (!legal) {
            legal = Arrays.stream(integers).anyMatch(integer -> integer <= 0);
        }
        return !legal;
    }

    //判断整数大于等于0
    protected boolean isIntegerParamNatural(Integer... integers) {
        boolean legal = Arrays.stream(integers).anyMatch(integer -> integer == null);
        if (!legal) {
            legal = Arrays.stream(integers).anyMatch(integer -> integer < 0);
        }
        return !legal;
    }

    //判断Long是否大于0
    protected boolean isLongParamPositive(Long... longs) {
        boolean legal = Arrays.stream(longs).anyMatch(s -> s == null);
        if (!legal) {
            legal = Arrays.stream(longs).anyMatch(s -> s <= 0);
        }
        return !legal;
    }

    //判断字符串参数是否为空
    protected boolean isStringParamLegal(String... strs) {
        boolean legal = Arrays.stream(strs).anyMatch(s -> StringUtils.isEmptyOrWhitespaceOnly(s));
        return !legal;
    }

    //判断dto或者内部属性参数是否为空
    protected boolean isDTOParamLegal(DTO... dtos) {
        boolean legal = Arrays.stream(dtos).anyMatch(dto -> dto == null || (!dto.checkLegal()));
        return !legal;
    }

    //判断dto或者内部属性参数是否为空
    protected boolean isDTOParamLegal(List<DTO> dtos) {
        boolean legal = dtos.stream().anyMatch(dto -> dto == null || (!dto.checkLegal()));
        return !legal;
    }

    //判断分页参数是否合法
    protected boolean isPageParamLegal(int currentPage, int pageSize) {
        return currentPage > 0 && pageSize > 0;
    }

    @ExceptionHandler(Exception.class)
    public Response handleError(Exception ex) {
        logger.error("Controller.Exception.Info", ex);

        //请求的参数类型不符合接口要求
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return Response.error(new Code(Code.ERROR.Exception_003));
        }
        //必填参数为空的情况
        if (ex instanceof MissingServletRequestParameterException) {
            return Response.error(new Code(Code.ERROR.Exception_001));
        }
        //缺失body数据或者body数据无法转化
        if(ex instanceof HttpMessageNotReadableException){
            return Response.error(new Code(Code.ERROR.Exception_005));
        }

        return Response.error(new Code(Code.ERROR.Exception_000));
    }

}
