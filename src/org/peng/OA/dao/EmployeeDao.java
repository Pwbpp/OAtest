package org.peng.OA.dao;

import static org.peng.OA.util.common.OAConstants.EMPLOYEETABLE;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.peng.OA.dao.provider.EmployeeDynaSqlProvider;
import org.peng.OA.domain.Employee;

public interface EmployeeDao {
	
	//根据参数查询员工总数
	@SelectProvider(type=EmployeeDynaSqlProvider.class ,method = "count")
	Integer count(Map<String, Object> params);

	//动态查询
	@SelectProvider(type=EmployeeDynaSqlProvider.class ,method = "selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardid"),
		@Result(column="POST_CODE",property="postcode"),
		@Result(column="QQ",property="qq"),
		@Result(column="BIRTHDAY",property="birthday",javaType = Date.class),
		@Result(column="CREATE_DATE",property="createdate",javaType = Date.class),
		@Result(column="DEPT_ID",property="dept",one = @One(select = "org.pwb.OA.dao.DeptDao.selectById",fetchType = FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",one = @One(select = "org.pwb.OA.dao.JobDao.selectById",fetchType = FetchType.EAGER)),	
	})
	List<Employee> selectByPage(Map<String, Object> params);

	//动态插入员工
	@SelectProvider(type =EmployeeDynaSqlProvider.class,method = "insertEmployee")
	void save(Employee employee);

	//根据 id 删除员工
	@Delete("delete from "+EMPLOYEETABLE+"where ID = #{id}")
	void deleteById(Integer id);
	
	//根据 id 查询员工
	@Select("select*from "+EMPLOYEETABLE+" where ID = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardid"),
		@Result(column="POST_CODE",property="postcode"),
		@Result(column="QQ",property="qq"),
		@Result(column="BIRTHDAY",property="birthday",javaType = Date.class),
		@Result(column="CREATE_DATE",property="createdate",javaType = Date.class),
		@Result(column="DEPT_ID",property="dept",one = @One(select = "org.pwb.OA.dao.DeptDao.selectById",fetchType = FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",one = @One(select = "org.pwb.OA.dao.JobDao.selectById",fetchType = FetchType.EAGER)),	
	})
	Employee selectById(Integer id);
	//动态修正
	@SelectProvider(type =EmployeeDynaSqlProvider.class,method = "updataEmployee")
	void update(Employee employee);
}
