package org.peng.OA.dao;

import static org.peng.OA.util.common.OAConstants.NOTICETABLE;

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
import org.peng.OA.dao.provider.NoticeDynaSqlProvider;
import org.peng.OA.domain.Notice;

public interface NoticeDao {
	//动态查询
	@SelectProvider(type=NoticeDynaSqlProvider.class ,method = "selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createdate",javaType = Date.class),
		@Result(column="USER_ID",property="user",one = @One(select = "org.pwb.OA.dao.UserDao.selectById",fetchType = FetchType.EAGER)),	
	})
	List<Notice> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=NoticeDynaSqlProvider.class ,method = "count")
	Integer count(Map<String, Object> params);
	@Select("select*from "+NOTICETABLE+" where ID = #{id}")
	Notice selectById(int id);
	
	//根据 id 删除公告
	@Delete("delete from "+NOTICETABLE+"where ID = #{id}")
	void deleteById(Integer id);
	
	//动态插入公告
	@SelectProvider(type =NoticeDynaSqlProvider.class,method = "insertNotice")
	void save(Notice notice);	
	
	//动态修改公告
	@SelectProvider(type =NoticeDynaSqlProvider.class,method = "updateNotice")
	void update(Notice notice);
	
	
}
