package reasearch.mybatis.dal.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.Properties;



/**
 * @param
 * @Author: YanLong
 * @Description:
 * @Date: 2018/4/7 0007 下午 10:35
 */
@Intercepts(
        {

        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class ExecutorPlugin implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement= (MappedStatement)invocation.getArgs()[0];
           BoundSql boundSql=mappedStatement.getBoundSql(invocation.getArgs()[1]);
        System.out.println("sql:"+boundSql.getSql()+" "+ "param:"+ boundSql.getParameterObject());
        return  invocation.proceed();


    }

    public Object plugin(Object target) {
        return Plugin.wrap( target, this);
    }

    public void setProperties(Properties properties) {

    }
}
