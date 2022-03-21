package examples.mybatis

import examples.mybatis.entity.Person
import examples.mybatis.mapper.PersonMapper
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.junit.jupiter.api.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class AppTest {
    @Test
    @Throws(IOException::class)
    fun testApp() {
        val resource = "mybatis-config.xml"
        val inputStream = Resources.getResourceAsStream(resource)
        val sqlSessionFactory = SqlSessionFactoryBuilder().build(inputStream)
        sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(PersonMapper::class.java)
            mapper.createTable()
            val p = Person(1L, "yuki", 20)
            mapper.insert(p)
            val p1 = mapper.find(1L)
            val p2 = mapper.findByName("yuki")
            val p3 = mapper.findByName("yuki")

            assertEquals(p1, p2)
            // 同じ人を指すもの同士ではあるが、別インスタンス
            assertNotSame(p1, p2)

            assertEquals(p2, p3)
            // クエリ結果をキャッシュするので同一オブジェクト
            assertSame(p2, p3)
        }
    }
}
