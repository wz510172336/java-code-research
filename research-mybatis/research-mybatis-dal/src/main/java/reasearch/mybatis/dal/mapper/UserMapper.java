package reasearch.mybatis.dal.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import reasearch.mybatis.dal.domain.User;
import reasearch.mybatis.dal.domain.UserExample;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    @Select("SELECT * FROM tb_user  where id = #{id,jdbcType=INTEGER}")
    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}