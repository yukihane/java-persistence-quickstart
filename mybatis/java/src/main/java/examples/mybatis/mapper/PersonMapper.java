package examples.mybatis.mapper;

import examples.mybatis.entity.Person;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PersonMapper {


    @Update("create table if not exists person(id integer primary key, name text, age integer)")
    void createTable();

    @Select("select * from person where id = ${id}")
    Person find(@Param("id") Long id);

    @Select("select * from person where name = #{name}")
    Person findByName(@Param("name") String name);

    @Insert("insert into person (id, name, age) values (#{id}, #{name}, #{age})")
    void insert(Person person);

}
