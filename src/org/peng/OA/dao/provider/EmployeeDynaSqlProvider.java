package org.peng.OA.dao.provider;

import static org.peng.OA.util.common.OAConstants.EMPLOYEETABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.peng.OA.domain.Employee;

public class EmployeeDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(EMPLOYEETABLE);
				if(params.get("employee") != null) {
					Employee employee = (Employee)params.get("employee");
					if(employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0) {
						WHERE(" DEPT_ID = #{employee.dept.id} ");
					}
					if(employee.getJob() != null && employee.getJob().getId() != null && employee.getJob().getId() != 0) {
						WHERE(" JOB_ID = #{employee.job.id} ");
					}
					if(employee.getName() != null && !employee.getName().equals("")) {
						WHERE(" NAME like concat ('%',#{employee.name},'%') ");
					}
					if(employee.getPhone() != null && !employee.getPhone().equals("")) {
						WHERE(" PHONE like concat ('%',#{employee.phone},'%') ");
					}
					if(employee.getCardid() != null && !employee.getCardid().equals("")) {
						WHERE(" CARD_ID like concat ('%',#{employee.cardid},'%') ");
					}
					if(employee.getSex() != null && employee.getSex() != 0) {
						WHERE(" SEX = #{employee.sex} ");
					}
				}
			}
		}.toString();
		if(params.get("pageModel") != null) {
			sql += "limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	//动态查询总数量
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(EMPLOYEETABLE);
				if(params.get("employee") != null) {
					Employee employee = (Employee)params.get("employee");
					if(employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0) {
						WHERE(" DEPT_ID = #{employee.dept.id} ");
					}if(employee.getJob() != null && employee.getJob().getId() != null && employee.getJob().getId() != 0) {
						WHERE(" JOB_ID = #{employee.job.id} ");
					}
					if(employee.getName() != null && !employee.getName().equals("")) {
						WHERE(" NAME like concat ('%',#{employee.name},'%') ");
					}
					if(employee.getPhone() != null && !employee.getPhone().equals("")) {
						WHERE(" PHONE like concat ('%',#{employee.phone},'%') ");
					}
					if(employee.getCardid() != null && !employee.getCardid().equals("")) {
						WHERE(" CARD_ID like concat ('%',#{employee.cardid},'%') ");
					}
					if(employee.getSex() != null && employee.getSex() != 0) {
						WHERE(" SEX = #{employee.sex} ");
					}
				}
			}
		}.toString();
	}
	
	
		//动态插入
	public String inserEmployee(Employee employee) {
		return new SQL() {
			{
				INSERT_INTO(EMPLOYEETABLE);
				if(employee.getName() != null) {
					VALUES("name", "#{name}");
				}
				if(employee.getCardid() != null) {
					VALUES("cardid", "#{cardid}");
				}
				if(employee.getAddress() != null) {
					VALUES("address", "#{address}");
				}
				if(employee.getPostCode() != null) {
					VALUES("post_code", "#{postCode}");
				}
				if(employee.getTel() != null) {
					VALUES("tel", "#{tel}");
				}
				if(employee.getPhone() != null) {
					VALUES("phone", "#{phone}");
				}
				if(employee.getQq() != null) {
					VALUES("qq", "#{qq}");
				}
				if(employee.getEmail() != null) {
					VALUES("email", "#{email}");
				}
				if(employee.getSex() != null) {
					VALUES("sex", "#{sex}");
				}
				if(employee.getBirthday() != null) {
					VALUES("birthday", "#{birthday}");
				}
				if(employee.getRace() != null) {
					VALUES("race", "#{race}");
				}
				if(employee.getEducation() != null) {
					VALUES("education", "#{education}");
				}
				if(employee.getSpeciality() != null) {
					VALUES("speciality", "#{speciality}");
				}
				if(employee.getHobby() != null) {
					VALUES("hobby", "#{hobby}");
				}
				if(employee.getRemark() != null) {
					VALUES("remark", "#{remark}");
				}
				if(employee.getCreateDate() != null) {
					VALUES("create_Date", "#{createDate}");
				}
				if(employee.getDept() != null) {
					VALUES("dept_id", "#{dept.id}");
				}
				if(employee.getJob() != null) {
					VALUES("job_id", "#{job.id}");
				}
			}
		}.toString();
	}
	
	//动态更新
	public String updateDept(Employee employee) {
		return new SQL() {
			{
				UPDATE(EMPLOYEETABLE);
				if(employee.getName() != null) {
					SET(" name = #{name}");
				}
				if(employee.getCardid() != null) {
					SET("cardid = #{cardid}");
				}
				if(employee.getAddress() != null) {
					SET("address = #{address}");
				}
				if(employee.getPostCode() != null) {
					SET("post_code = #{postCode}");
				}
				if(employee.getTel() != null) {
					SET("tel = #{tel}");
				}
				if(employee.getPhone() != null) {
					SET("phone = #{phone}");
				}
				if(employee.getQq() != null) {
					SET("qq = #{qq}");
				}
				if(employee.getEmail() != null) {
					SET("email = #{email}");
				}
				if(employee.getSex() != null) {
					SET("sex = #{sex}");
				}
				if(employee.getBirthday() != null) {
					SET("birthday = #{birthday}");
				}
				if(employee.getRace() != null) {
					SET("race = #{race}");
				}
				if(employee.getEducation() != null) {
					SET("education = #{education}");
				}
				if(employee.getSpeciality() != null) {
					SET("speciality = #{speciality}");
				}
				if(employee.getHobby() != null) {
					SET("hobby = #{hobby}");
				}
				if(employee.getRemark() != null) {
					SET("remark = #{remark}");
				}
				if(employee.getCreateDate() != null) {
					SET("create_Date = #{createDate}");
				}
				if(employee.getDept() != null) {
					SET("dept_id = #{dept.id}");
				}
				if(employee.getJob() != null) {
					SET("job_id = #{job.id}");
				}
				WHERE(" id = #{id}");
			}
		}.toString();
	}
}
