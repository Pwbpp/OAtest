package org.peng.OA.dao;

import static org.peng.OA.util.common.OAConstants.DOCUMENTTABLE;

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
import org.peng.OA.dao.provider.DocumentDynaSqlProvider;
import org.peng.OA.domain.Document;

public interface DocumentDao {
	//动态查询
	@SelectProvider(type=DocumentDynaSqlProvider.class ,method = "selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createdate",javaType = Date.class),
		@Result(column="USER_ID",property="user",one = @One(select = "org.pwb.OA.dao.UserDao.selectById",fetchType = FetchType.EAGER)),	
	})
	List<Document> selectByPage(Map<String, Object> params);
	@SelectProvider(type=DocumentDynaSqlProvider.class ,method = "count")
	Integer count(Map<String, Object> params);
	
	//动态插入文档
	@Select("select*from "+DOCUMENTTABLE+" where ID = #{id}")
	void save(Document document);
	
	@Select("select * from "+DOCUMENTTABLE+" where ID = #{id}")
	Document selectById(int id);
	
	//根据 id 删除文档
	@Delete("delete from "+DOCUMENTTABLE+"where ID = #{id}")
	void deleteById(Integer id);
	
	//动态修改文档
	@SelectProvider(type =DocumentDynaSqlProvider.class,method = "updateNotice")
	void update(Document document);
	
		
}
