import Executor.WZExecutor;
import com.sun.org.apache.xpath.internal.SourceTree;
import configuration.WZConfigruation;
import reasearch.mybatis.dal.domain.User;
import reasearch.mybatis.dal.mapper.UserMapper;
import sqlSession.WZdefaultSqlSession;

/**
 * @param
 * @package: PACKAGE_NAME
 * @Author:
 * @Description:
 * @Date: 2018/4/19 0019 下午 6:05
 */
public class Test1 {
    public static void main(String[] args) {
        WZdefaultSqlSession wZdefaultSqlSession=new WZdefaultSqlSession(new WZConfigruation(),new WZExecutor());
       UserMapper userMapper= wZdefaultSqlSession.getMapper(UserMapper.class);
       User user=new User();
       user.setId(null);
       user.setName("shouxie5");
       user.setAge("117");
        int i=userMapper.insert(user);
        System.out.println(i);

    }
}
