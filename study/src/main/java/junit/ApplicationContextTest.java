package junit;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class ApplicationContextTest {
	
	ApplicationContext ac = new GenericXmlApplicationContext("classpath:/applicationContext.xml");
	private DataSource dataSource= ac.getBean("dataSource", DataSource.class);
	@Test
	public void test() {
		assertNotNull(dataSource);
	}

}
