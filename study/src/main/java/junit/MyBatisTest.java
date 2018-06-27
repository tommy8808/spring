package junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.study.domain.users.User;

public class MyBatisTest {

	private static final Logger log = LoggerFactory.getLogger(MyBatisTest.class);
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setup() throws IOException{
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void gettingStarted() throws Exception {
		
		
		try (SqlSession session = sqlSessionFactory.openSession()){
			User user = session.selectOne("UserMapper.findById", "test1");
			log.info("User : {}", user );
		}
	}
	
	@Test
	public void insert() throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()){
			User user = new User("testman", "password", "testman", "test@test.test");
			session.insert("UserMapper.create", user);
			User actual = session.selectOne("UserMapper.findById", user.getUserId());
			log.info("user : {}", actual );
			assertThat(actual, is(user));
		}
	}

}
