package com.cristph.template.core.pojos.dto;

import com.cristph.template.core.pojos.entity.Manager;
import com.cristph.template.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2017/8/10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDTO extends BaseDTO<Manager>{

    private Long id;


    /**
     * 外键权限id
     */
    private Long accessId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 最大登录数
     */
    private Integer maxLogonNum;

    /**
     * 准入ip
     */
    private String ip;

    /**
     * 准入mac
     */
    private String mac;

    /**
     * ip/mac绑定
     */
    private String ipMac;

    /**
     * 用户状态位（'Enabled','Disabled','Locked'）
     */
    private String status;

    @Override
    public boolean checkLegal() {
        if(StringUtils.isEmptyOrWhitespaceOnly(userName)){
            return false;
        }
        if(StringUtils.isEmptyOrWhitespaceOnly(password)){
            return false;
        }
        if(StringUtils.isEmptyOrWhitespaceOnly(status)){
            return false;
        }
        return true;
    }
}
