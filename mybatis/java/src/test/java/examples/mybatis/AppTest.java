package examples.mybatis;

import examples.mybatis.entity.Person;
import examples.mybatis.mapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class AppTest {

    @Test
    public void testApp() throws IOException {

        final String resource = "mybatis-config.xml";
        final InputStream inputStream = Resources.getResourceAsStream(resource);
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (final SqlSession session = sqlSessionFactory.openSession()) {
            final PersonMapper mapper = session.getMapper(PersonMapper.class);
            mapper.createTable();
            final Person p = new Person(10L);
            mapper.insert(p);
            final Person p1 = mapper.find(10L);
        }
    }
}
