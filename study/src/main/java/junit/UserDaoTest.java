package junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.study.dao.users.UserDao;
import net.study.domain.users.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class UserDaoTest {

	private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);
	@Autowired
	private UserDao userDao;
	
	@Test
	public void findById() {
		User user = userDao.findById("test1");
		log.info("User : {}", user);
	}
	
	@Test
	public void create() throws Exception {
		User user = new User("test7", "test5", "test5", "test@test.com");
		userDao.create(user);
		User actual = userDao.findById(user.getUserId());
		log.info("User : {}", actual);
		assertThat(actual, is(user));
	}
	
	@Test
	public void update() throws Exception {
		User user = new User("test5", "test5", "test5", "test@test.com");
		userDao.update(user);
		User actual = userDao.findById(user.getUserId());
		log.info("User : {}", actual);
		assertThat(actual, is(user));
	}

}
