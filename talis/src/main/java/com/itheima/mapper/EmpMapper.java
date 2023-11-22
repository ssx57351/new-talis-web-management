package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
 /*

  */
 /*分页查询所有员工*//*

    @Select("select * from emp")
     List<Emp> selectAll(Integer page,Integer pageSize);

*/

 /*条件分页查询员工*/
 List<Emp> list(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

 /*新增员工
  * */
 //主键返回
 @Options(keyProperty = "id", useGeneratedKeys = true)
 @Insert("insert into emp (username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
         "VALUES (#{username},#{password},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
 int insert(Emp emp);

 /*修改员工
  * */
 int update(Emp emp);

 /*根据id查询员工
  * */
  @Select("select * from emp where id=#{id}")
 Emp selectById(Integer id);

 /*根据id删除员工(支持批量删除)
  * */
 void delete(List<Integer> ids);

 //    用户登录
 @Select("select id,username,password,name,gender,image,job,entrydate,dept_id,create_time,update_time" +
         " from emp where username=#{username} and password=#{password}")
  Emp getByUsernameAndPassword(Emp emp);


 /*根据部门id删除员工*/
 @Delete("delete from emp where dept_id=#{id}")
 void deleteByDeptId(Integer id);
}
