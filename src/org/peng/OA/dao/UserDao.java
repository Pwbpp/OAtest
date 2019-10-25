package org.peng.OA.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.peng.OA.dao.provider.UserDynaSqlProvider;
import org.peng.OA.domain.User;

import static org.peng.OA.util.common.OAConstants.USERTABLE;

import java.util.List;
import java.util.Map;

public interface UserDao {
	
	//根据 登陆名 和 密码  查询员工
	@Select("select*from "+USERTABLE+"where loginname = #{loginname} and PASSWORD = #{password}")
	User selectByLoginnameAndPassword(
			@Param("loginname") String loginname,
			@Param("password") String password);
	
	//根据 id 查询用户
	@Select("select*from "+USERTABLE+"where ID = #{id}")
	User selectById(Integer id);
	
	//根据 id 删除用户
	@Delete("delete from "+USERTABLE+"where ID = #{id}")
	User deleteById(Integer id);
	
	// 动态修改用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);
		
	
	// 动态查询
	@SelectProvider(type = UserDynaSqlProvider.class,method = "selectWhitParam")
	List<User> selectByPage(Map<String, Object> params);
	
	//根据参数查询用户总数
	@SelectProvider(type = UserDynaSqlProvider.class,method = "count")
	Integer count(Map<String, Object> params);
	
	//动态插入用户
	@SelectProvider(type = UserDynaSqlProvider.class,method = "insertUser")
	void save(User user);
}
