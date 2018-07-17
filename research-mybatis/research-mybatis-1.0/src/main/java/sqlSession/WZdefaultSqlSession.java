package sqlSession;

import Executor.WZExecutor;
import configuration.WZConfigruation;

import java.sql.SQLException;


/**
 * @param
 * @package: sqlSession
 * @Author:
 * @Description:
 * @Date: 2018/4/19 0019 下午 3:02
 */
public class WZdefaultSqlSession {
    public WZConfigruation wzConfigruation;
    public WZExecutor wzExecutor;

    public WZdefaultSqlSession(WZConfigruation wzConfigruation, WZExecutor wzExecutor) {
        this.wzConfigruation = wzConfigruation;
        this.wzExecutor = wzExecutor;
    }

    public  <T> T  getMapper(Class<T> clazz){
         return wzConfigruation.getMapper(clazz,this);
    }

    public void insertOne(String statement,Object objectParameter) throws Exception{
         wzExecutor.insert(statement,objectParameter);
    }

}
