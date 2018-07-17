package configuration;

import mapperproxy.WZMapperProxy;
import sqlSession.WZdefaultSqlSession;

import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @param
 * @package: configuration
 * @Author:
 * @Description:
 * @Date: 2018/4/19 0019 下午 3:03
 */
public class WZConfigruation {


    public <T> T getMapper(Class<T> clazz, WZdefaultSqlSession wZdefaultSqlSession) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new WZMapperProxy(wZdefaultSqlSession));
    }

    public static class XmlMapper {
        public static final String nameSpace = "reasearch.mybatis.dal.mapper.UserMapper";
        public static ConcurrentHashMap<String, String> methodSqlMapping = new ConcurrentHashMap<String, String>();

        static {
            methodSqlMapping.put("insert", "insert into tb_user(id,name,age) values(null,'%s','%s')");
        }
    }

}
