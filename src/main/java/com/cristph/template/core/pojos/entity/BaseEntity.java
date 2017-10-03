package com.cristph.template.core.pojos.entity;


import com.cristph.template.core.pojos.dto.BaseDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import java.util.Date;

@Data
public class BaseEntity<T extends BaseDTO> {

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "delete_time")
    private Date deleteTime;

    @Column(name = "create_operator")
    private Long createOperator;

    @Column(name = "update_operator")
    private Long updateOperator;

    @Column(name = "delete_operator")
    private Long deleteOperator;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    public T toDTO(Class<T> tClass) {
        T t = null;
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
