package com.cristph.template.constraint;

public class Code {

    //define ERROR Code and info
    public enum ERROR {

        Exception_UnrecordedException("未记录在案的系统异常"),
        Exception_MissingServletRequestParameterException("存在方法必填参数缺失"),
        Exception_MethodArgumentTypeMismatchException("请求的参数类型不符合方法要求"),
        Exception_HttpMessageNotReadableException("请求/响应数据无法读取(数据类型不匹配)"),

        ERROR_COMMON_PAGE_PARAMS("分页参数出错"),
        ERROR_COMMON_CREATE("新建失败"),
        ERROR_COMMON_UPDATE("更新失败"),
        ERROR_COMMON_RETRIEVE("查询失败"),
        ERROR_COMMON_DELETE("删除失败"),
        ERROR_COMMON_ID_NOT_EXIST("ID不存在"),
        ERROR_COMMON_LACK_REQUIRED_PARAM("存在必填参数为空"),
        ERROR_COMMON_FORMAT("部分参数格式错误"),
        ERROR_COMMON_NAME_ALREADY_EXIST("名称已经被占用");

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
