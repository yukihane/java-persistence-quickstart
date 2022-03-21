package examples.mybatis.mapper

import examples.mybatis.entity.Person
import org.apache.ibatis.annotations.*

@Mapper
interface PersonMapper {
    @Update("create table if not exists person(id integer primary key, name text, age integer)")
    fun createTable()

    @Select("select * from person where id = \${id}")
    fun find(@Param("id") id: Long?): Person?

    @Select("select * from person where name = #{name}")
    fun findByName(@Param("name") name: String?): Person?

    @Insert("insert into person (id, name, age) values (#{id}, #{name}, #{age})")
    fun insert(person: Person?)
}
