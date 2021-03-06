package junit;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.study.domain.users.User;

public class UserTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	private static Validator validator;
	
	@BeforeClass
	public static void setUp() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
	}
	
	@Test
	public void whenUserIdIsEmpty() {
		User user = new User("","test5","test5","test@test.com");
		
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		
		assertThat(constraintViolations.size(), is(2));
		
		for (ConstraintViolation<User> constraintViolation : constraintViolations) {
			log.info("violation error message : {}", constraintViolation.getMessage());
		}
	}

}
