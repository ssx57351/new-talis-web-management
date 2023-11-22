package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
 /*查询所有部门数据信息*/
   @Select("select * from dept ")
   public List<Dept> selectAll();

//根据id删除部门

    @Delete("delete from dept where id=#{id}")
    public int deleteById(Integer id);

//新增部门

    @Insert("insert into dept (name,create_time,update_time) " +
            "values (#{name},#{createTime},#{updateTime})")
    public int insert(Dept dept);

//根据id查询部门
    @Select("select id, name,create_time,update_time from dept where id=#{id}")
    public Dept selectById(Integer id);

//更新部门

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    public int update(Dept dept);

}
