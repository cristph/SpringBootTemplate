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

public class BaseController<DTO extends BaseDTO> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected boolean isIntegerParamPositive(Integer... integers) {
        boolean legal = Arrays.stream(integers).anyMatch(integer -> integer == null);
        if (!legal) {
            legal = Arrays.stream(integers).anyMatch(integer -> integer <= 0);
        }
        return !legal;
    }

    protected boolean isIntegerParamNatural(Integer... integers) {
        boolean legal = Arrays.stream(integers).anyMatch(integer -> integer == null);
        if (!legal) {
            legal = Arrays.stream(integers).anyMatch(integer -> integer < 0);
        }
        return !legal;
    }

    protected boolean isLongParamPositive(Long... longs) {
        boolean legal = Arrays.stream(longs).anyMatch(s -> s == null);
        if (!legal) {
            legal = Arrays.stream(longs).anyMatch(s -> s <= 0);
        }
        return !legal;
    }

    protected boolean isStringParamLegal(String... strs) {
        boolean legal = Arrays.stream(strs).anyMatch(s -> StringUtils.isEmptyOrWhitespaceOnly(s));
        return !legal;
    }

    protected boolean isDTOParamLegal(DTO... dtos) {
        boolean legal = Arrays.stream(dtos).anyMatch(dto -> dto == null || (!dto.checkLegal()));
        return !legal;
    }

    protected boolean isDTOParamLegal(List<DTO> dtos) {
        boolean legal = dtos.stream().anyMatch(dto -> dto == null || (!dto.checkLegal()));
        return !legal;
    }

    protected boolean isPageParamLegal(int currentPage, int pageSize) {
        return currentPage > 0 && pageSize > 0;
    }

    @ExceptionHandler(Exception.class)
    public Response handleError(Exception ex) {

        logger.error("Controller.Exception.Info", ex);

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return Response.error(new Code(Code.ERROR.Exception_MethodArgumentTypeMismatchException));
        }

        if (ex instanceof MissingServletRequestParameterException) {
            return Response.error(new Code(Code.ERROR.Exception_MissingServletRequestParameterException));
        }

        if (ex instanceof HttpMessageNotReadableException) {
            return Response.error(new Code(Code.ERROR.Exception_HttpMessageNotReadableException));
        }
        return Response.error(new Code(Code.ERROR.Exception_UnrecordedException));
    }

}
