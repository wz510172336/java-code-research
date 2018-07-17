package reasearch.mybatis.dal.typeHandle;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @param
 * @Author: YanLong
 * @Description:
 * @Date: 2018/4/7 0007 下午 9:34
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class UserTypehandler extends BaseTypeHandler<String>{


    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,s+"wzwz");
    }

    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getString(s)+"nihao";
    }

    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
