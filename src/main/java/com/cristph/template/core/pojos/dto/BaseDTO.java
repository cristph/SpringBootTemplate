package com.cristph.template.core.pojos.dto;


import com.cristph.template.core.pojos.entity.BaseEntity;
import com.cristph.template.utils.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/8/16.
 */
public abstract class BaseDTO<T extends BaseEntity> {

    public abstract boolean checkLegal();

    public T toEntity(Class<T> tClass){
        T t= null;
        try {
            t = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(this, t);
        return t;
    }

    protected boolean isStringParamLegal(String... strs){
        boolean legal= Arrays.stream(strs).anyMatch(s -> StringUtils.isEmptyOrWhitespaceOnly(s));
        return !legal;
    }

    protected boolean isLongParamLegal(Long... strs){
        boolean legal=Arrays.stream(strs).anyMatch(s -> s==null);
        return !legal;
    }

}
