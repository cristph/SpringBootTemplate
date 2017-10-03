package com.cristph.template.constraint;

public class Code {

    //失败提示信息状态码
    public enum ERROR {

        Exception_000("系统异常"),
        Exception_001("存在必填参数缺失"),
        Exception_002("部分参数格式错误"),
        Exception_003("请求的参数类型不符合要求"),
        Exception_004("无权限访问"),
        Exception_005("请求数据的body缺失/类型不符合"),
        Exception_006("下载发生错误"),
        Exception_007("导入的文件存在问题，导入失败"),


        ERROR_COMMON_PAGE_PARAMS("分页参数出错"),
        ERROR_COMMON_CREATE("新建失败"),
        ERROR_COMMON_UPDATE("更新失败"),
        ERROR_COMMON_DELETE("删除失败"),
        ERROR_COMMON_ID_NOT_EXIST("ID不存在"),
        ERROR_COMMON_LACK_REQUIRED_PARAM("存在必填参数为空"),
        ERROR_COMMON_FORMAT("部分参数格式错误"),
        ERROR_COMMON_NAME_ALREADY_EXIST("名称已经被占用"),

        ERROR_LOGIN_COUNT("登录失败超过5次"),
        ERROR_LOGIN_FAIL("用户名或密码错误"),

        ERROR_MANAGER_DENY("禁止删除超级管理员"),

        ERROR_APP_ICON_UPLOAD("图标上传失败"),
        ERROR_APP_ICON_FORMAT("图标格式错误"),


        ERROR_COMMON_000("分页参数出错"),
        ERROR_COMMON_001("新建失败"),
        ERROR_COMMON_002("更新失败"),
        ERROR_COMMON_003("删除失败"),

        ERROR_LOGIN_100("登录失败超过5次"),
        ERROR_LOGIN_101("用户名或密码错误"),

        ERROR_MANAGER_100("部分必填参数为空"),
        ERROR_MANAGER_101("部分参数格式错误"),
        ERROR_MANAGER_102("用户名已存在"),
        ERROR_MANAGER_103("注册用户失败"),
        ERROR_MANAGER_104("删除用户失败"),
        ERROR_MANAGER_105("更新用户失败"),
        ERROR_MANAGER_106("用户id不存在"),
        ERROR_MANAGER_107("分页参数错误"),

        ERROR_APP_100("应用名已存在"),
        ERROR_APP_101("图标格式错误"),
        ERROR_APP_102("添加应用失败"),
        ERROR_APP_103(""),
        ERROR_APP_104("部分参数格式错误"),

        ERROR_User_200("新建用户存在必填参数为空"),
        ERROR_User_201("存在参数格式错误"),
        ERROR_User_202("用户名已存在"),
        ERROR_User_204("删除用户失败"),
        ERROR_User_205("更新用户失败"),
        ERROR_User_206("用户id不存在"),
        ERROR_User_207("新建用户失败"),

        ERROR_GROUP_000("用户组名称已经存在"),
        ERROR_GROUP_001("新建用户组失败"),
        ERROR_GROUP_002("更新用户组失败"),
        ERROR_GROUP_003("存在必填参数为空"),
        ERROR_GROUP_004("用户组id不存在"),
        ERROR_GROUP_005("删除用户组失败"),


        ERROR_RULE_000("策略名称已经存在"),
        ERROR_RULE_001("新建策略失败"),
        ERROR_RULE_002("更新策略失败"),
        ERROR_RULE_003("存在必填参数为空"),
        ERROR_RULE_004("策略id不存在"),
        ERROR_RULE_005("删除策略失败"),

        ERROR_NET_INTERFACE_000("网络接口名称已经存在"),
        ERROR_NET_INTERFACE_001("新建网络接口失败"),
        ERROR_NET_INTERFACE_002("更新网络接口失败"),
        ERROR_NET_INTERFACE_003("存在必填参数为空"),
        ERROR_NET_INTERFACE_004("网络接口id不存在"),
        ERROR_NET_INTERFACE_005("删除网络接口失败"),
        ERROR_NET_INTERFACE_006("参数中需指明是链路聚合"),
        ERROR_NET_INTERFACE_007("聚合链路中未设置slaver设备"),
        ERROR_NET_INTERFACE_008("聚合链路中设置slaver设备名称不能为空"),
        ERROR_NET_INTERFACE_009("聚合链路中设置slaver设备名称不存在"),
        ERROR_NET_INTERFACE_010("当前网卡已被聚合"),
        ERROR_NET_INTERFACE_011("该网卡不存在"),


        ERROR_LICENSE_001("读取硬件ID失败"),
        ERROR_LICENSE_002("主模块未被激活，需要先激活主模块"),
        ERROR_LICENSE_003("读取license解密配置信息失败"),
        ERROR_LICENSE_004("License 与模块不对应"),
        ERROR_LICENSE_005("License 数据异常"),
        ERROR_LICENSE_006("License 不合法"),
        ERROR_LICENSE_007("Module不合法"),
        ERROR_LICENSE_008("License不存在或存在多个同模块license"),

        ERROR_MAIL_001("编辑邮件服务器失败"),
        ERROR_MAIL_002("邮件服务器测试失败"),
        ERROR_MAIL_003("邮件服务器未启用"),



        ERROR_ROUTE_POLICY_001("新建策略路由失败"),
        ERROR_ROUTE_POLICY_002("更新策略路由失败"),
        ERROR_ROUTE_POLICY_003("存在必填参数为空"),
        ERROR_ROUTE_POLICY_004("策略路由id不存在"),
        ERROR_ROUTE_POLICY_005("删除策略路由失败,删除的id不存在"),

        ERROR_HOST_001("新建Hosts设置失败"),
        ERROR_HOST_002("更新Hosts设置失败"),
        ERROR_HOST_003("存在必填参数为空"),
        ERROR_HOST_004("Hosts设置id不存在"),
        ERROR_HOST_005("删除Hosts设置失败,删除的id不存在"),


        ERROR_DNS_001("编辑DNS失败"),

        ERROR_NET_001("读取网卡信息异常"),
        //（ex:数据库记录为STATIC，但是网卡配置文件中，不是STATIC）
        ERROR_NET_002("当前的连接状态，可能被人为非法改变，请重新配置网卡"),
        ERROR_NET_003("该设备的网卡配置文件不存在"),
        ERROR_NET_004("asdl参数设置异常"),
        ERROR_NET_005("默认网关设置失败"),
        ERROR_NET_006("当前网卡是虚拟网卡"),
        ERROR_NET_007("写入网卡配置文件异常"),
        ERROR_NET_008("DEVICE名称为空"),

        ERROR_AUTH_001("新建认证设置失败"),
        ERROR_AUTH_002("更新认证设置失败"),
        ERROR_AUTH_003("存在必填参数为空或者不合法"),
        ERROR_AUTH_004("认证id不存在"),
        ERROR_AUTH_005("删除认证失败,删除的id不存在"),
        ERROR_AUTH_006("认证名称已经存在");


        private String info;

        private ERROR(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "ERROR{" +
                    "info='" + info + '\'' +
                    "} " + super.toString();
        }
    }

    private ERROR error;
    private String info;

    public Code(ERROR error) {
        this.error = error;
        this.info = error.getInfo();
    }

    public ERROR getError() {
        return error;
    }

    public void setErrorAndInfo(ERROR error) {
        this.error = error;
        this.info = error.getInfo();
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Code{" +
                "error=" + error +
                ", info='" + info + '\'' +
                '}';
    }
}
