package Executor;



import reasearch.mybatis.dal.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @param
 * @package: Executor
 * @Author:
 * @Description:
 * @Date: 2018/4/19 0019 下午 3:04
 */
public class WZExecutor {
    public void insert(String statement,Object o) throws Exception {

        Connection conn = null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
           // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",);
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?charset=utf-8","root","123456");
           // conn.setAutoCommit(false);
            String sql=null;
             if(o instanceof User){
                 User u=(User)o;
                 sql=String.format(statement,u.getName(),u.getAge());
             }
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
         //   conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
          //  conn.rollback();
        }finally {
             if(ps!=null) ps.close();
           if(null!=conn)conn.close();
        }

    }
}
