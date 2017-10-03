package com.cristph.template.core.pojos.entity;


import com.cristph.template.core.pojos.dto.BaseDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by ENLINK on 2017/8/8.
 */
@Data
public class BaseEntity<T extends BaseDTO>{

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 删除的时间
     */
    @Column(name = "delete_time")
    private Date deleteTime;

    /**
     * 创建者id
     */
    @Column(name = "create_operator")
    private Long createOperator;

    /**
     * 最后修改者id
     */
    @Column(name = "update_operator")
    private Long updateOperator;

    /**
     * 删除者id
     */
    @Column(name = "delete_operator")
    private Long deleteOperator;

    /**
     * 是否删除标记（0未删除，1已删除）
     */
    @Column(name = "delete_flag")
    private Long deleteFlag;

    public T toDTO(Class<T> tClass){
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
}
