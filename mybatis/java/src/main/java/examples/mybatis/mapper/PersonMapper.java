package examples.mybatis.mapper;

import examples.mybatis.entity.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PersonMapper {

    @Select("select * from person where id = ${id}")
    Person find(Long id);

    @Insert("insert into person (id) values (${id})")
    void insert(Person person);

    @Update("create table if not exists person(id integer primary key)")
    void createTable();
}
