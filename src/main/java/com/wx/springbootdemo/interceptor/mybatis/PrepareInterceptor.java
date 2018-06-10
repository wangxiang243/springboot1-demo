package com.wx.springbootdemo.interceptor.mybatis;


import com.wx.springbootdemo.constants.GlobalConstants;
import com.wx.springbootdemo.entity.SysDataRule;
import com.wx.springbootdemo.util.ContextHolderUtils;
import com.wx.springbootdemo.util.MySqlParserUtils;
import com.wx.springbootdemo.util.ReflectUtil;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * mybatis数据权限拦截器 - prepare
 * @author GaoYuan
 * @date 2018/4/17 上午9:52
 */
@Intercepts({
//          @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class })
//        @Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
@Component
public class PrepareInterceptor implements Interceptor {
    /** 日志 */
    private static final Logger log = LoggerFactory.getLogger(PrepareInterceptor.class);

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {}

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        log.info("===进入 PrepareInterceptor 数据权限拦截器...===");
        HttpServletRequest request = ContextHolderUtils.getRequest();
        String requestPath = request.getRequestURI().substring(request.getContextPath().length()+1);
        if(request != null) {
            List<SysDataRule> sysDataRuleList = (List<SysDataRule>) request.getAttribute(GlobalConstants.SYS_DATA_RULE_LIST);

            //判断当前请求是否有数据权限
            if (sysDataRuleList != null && sysDataRuleList.size() > 0) {
                log.info("===数据权限处理【拼接SQL】...");
                if (invocation.getTarget() instanceof RoutingStatementHandler) {
                    RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
                    StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
                    //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
                    MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
                    //千万不能用下面注释的这个方法，会造成对象丢失，以致转换失败
                    //MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
                    String methodName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".")+1);
                    if(!requestPath.contains(methodName)) {
                        return invocation.proceed();
                    }
                    BoundSql boundSql = delegate.getBoundSql();
                    //Connection connection = (Connection) invocation.getArgs()[0];
                    StringBuilder conditionStringbuilder = new StringBuilder();
                    for (SysDataRule sysDataRule : sysDataRuleList) {
                        if(conditionStringbuilder.length() > 0) {
                            conditionStringbuilder.append(" and ");
                        }
                        conditionStringbuilder.append(sysDataRule.getRuleValue());
                    }
                    String newSql = MySqlParserUtils.parseSelectSqlStatementAndAddWhereCondition(boundSql.getSql(), conditionStringbuilder.toString());
                    ReflectUtil.setFieldValue(boundSql, "sql", newSql);
                }
            }
        }
        return invocation.proceed();

    }

}
