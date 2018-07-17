package mapperproxy;

import configuration.WZConfigruation;
import reasearch.mybatis.dal.domain.User;
import sqlSession.WZdefaultSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @param
 * @package: mapperproxy
 * @Author:
 * @Description:
 * @Date: 2018/4/19 0019 下午 3:34
 */
   public class WZMapperProxy implements InvocationHandler{
    public WZdefaultSqlSession wZdefaultSqlSession;


    public WZMapperProxy(WZdefaultSqlSession wZdefaultSqlSession) {
        this.wZdefaultSqlSession = wZdefaultSqlSession;
    }

    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        if(method.getDeclaringClass().getName()==WZConfigruation.XmlMapper.nameSpace){
            String sql= WZConfigruation.XmlMapper.methodSqlMapping.get(method.getName());
            wZdefaultSqlSession.insertOne(sql,objects[0]);

        }


        return null;
    }
}
