import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reasearch.mybatis.dal.config.DataSourceConfig;
import reasearch.mybatis.dal.config.MybatisConfig;
import reasearch.mybatis.dal.domain.User;
import reasearch.mybatis.dal.mapper.UserMapper;

/**
 * @param
 * @package: PACKAGE_NAME
 * @Author:
 * @Description:
 * @Date: 2018/4/16 0016 下午 11:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataSourceConfig.class, MybatisConfig.class})
public class SpringTest {
    private  final static Logger log= LoggerFactory.getLogger(SpringTest.class);
    @Autowired
  private UserMapper userMapper;
    @Test
    public void springTestMapper(){
        User user=new User();
        user.setId(null);
        user.setName("ninghai");
        user.setAge("18");
        //userMapper.insert(user);

    }
}
