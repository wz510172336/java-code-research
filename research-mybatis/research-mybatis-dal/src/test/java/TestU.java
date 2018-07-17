import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reasearch.mybatis.dal.mapper.UserMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @param
 * @Author: YanLong
 * @Description: 测试类
 * @Date: 2018/4/6 0006 下午 8:31
 */
public class TestU {
    private  final static Logger log= LoggerFactory.getLogger(TestU.class);



    @Test
    public void insertUser() {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("F:/springbootworkspace/research-mybatis/research-mybatis-dal/src/main/java/reasearch/mybatis/dal/config/sqlConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
         /*   User user = new User();

            user.setAge("7");
            user.setName("WZWZ");
            userMapper.insert(user);
*/

            System.out.println(userMapper.selectByPrimaryKey(26));
           //  log.info("hello");
            sqlSession.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




    }
}
