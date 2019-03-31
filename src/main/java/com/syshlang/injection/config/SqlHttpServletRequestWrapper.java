package com.syshlang.injection.config;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
* @Description:    自定义的HttpServletRequestWrapper
* @Author:         sunys
* @CreateDate:     2019/3/31 14:39
* @UpdateUser:     
* @UpdateDate:     2019/3/31 14:39
* @UpdateRemark:
* @Version:        1.0
*/
public class SqlHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public SqlHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 重写getParameter方法 过滤一些关键字，替换成安全的
     * @param s
     * @return
     */
    @Override
    public String getParameter(String s) {
        String parameter = super.getParameter(s);
        parameter = stripSqlInject(parameter);
        return parameter;
    }

    /**
     * 对每个请求的参数过滤一些关键字，替换成安全的
     * @param parameter
     * @return
     */
    private String stripSqlInject(String parameter) {
        if(!StringUtils.isEmpty(parameter)) {
            //干掉or 攻击
            parameter=parameter.replaceAll("(?i)\\w*\\s*((\\%27)|(\\'))\\s*((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))", "");
            //干掉union 攻击
            parameter=parameter.replaceAll("(?i)\\w*\\s*((\\%27)|(\\'))\\s*union", "");
            //干掉截断攻击--原理：通过注释符号来截断后面的查询条件
            parameter=parameter.replaceAll("(?i)\\s*((\\%27)|(\\'))[\\s\\S^-]*--\\s*[and|exec|execute|insert|select|delete|"
                    + "update|count|drop|truncate|information_schema.columns|table_schema|union]*", "");
        }
        return parameter;
    }
}
