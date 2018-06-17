package junit;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageRenderer {
	private MessageProvider messageProvider;
	private static ApplicationContext ctx = null;
	

		
		
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}
	
	public void render() {
		System.out.println(messageProvider.getMessage());
	}
	
	public static void main(String[] args) {
		/*renderer.setMessageProvider(new HelloMessageProvider());
		renderer.render();
		renderer.setMessageProvider(new HiWorldProvider());
		renderer.render();*/
		
		ApplicationContext ac =
				new ClassPathXmlApplicationContext("di.xml");
		
		MessageRenderer render = (MessageRenderer) ac.getBean("messageRenderer");
		render.render();
		ac = new GenericXmlApplicationContext("classpath:application-properties.xml");
		
		render.render();
	}
}
