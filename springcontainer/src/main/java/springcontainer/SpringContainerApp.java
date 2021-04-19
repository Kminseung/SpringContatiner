package springcontainer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class SpringContainerApp {

	public static void main(String[] args) {
		// testBeanFactory();
		testApplicationContext();

	}
	
	private static void testBeanFactory() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("config/applicationContext.xml"));
		
		// 타입으로 얻어오기
		User user = bf.getBean(User.class);
		System.out.println("by type: " + user);
		
		// id로 얻어오기
		user = (User)bf.getBean("user");	// id가 user인 bean 획득하기
		System.out.println("by id: " + user);
		
		// name으로 얻어오기
		user = (User)bf.getBean("member");		// name이 user인 bean 획득
		System.out.println("by name: " + user);
	}
	
	private static void testApplicationContext() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		
		// 타입으로 받아오기
		// -> 해당 타입으로 생성된 객체가 유일해야 함
//		User user1 = ac.getBean(User.class);
//		System.out.println("Get by type: " + user1);
		
		// id or name 으롷 받아오가
		User user1 = (User)ac.getBean("user");		// id 명시
		User user2 = (User)ac.getBean("member");	// name 명시
		
		System.out.println("Get by id: " + user1);
		System.out.println("Get by name: " + user2);
		
		// Spring Container는 기본적으로 singleton 컨테이너다
		// id로 받아오던 name으로 받아오던 등록된 객체는 1개
		System.out.println("User1 == User2 ? " + (user1 == user2));
		
		System.out.println();
		
		User user3 = (User)ac.getBean("user3");
		System.out.println("User3: " + user3);
		System.out.println("User1 == User3 ? " + (user1 == user3));
		
		System.out.println();
		
		// 생성자로 만들어진 bean 얻어오기
		User user4 = (User)ac.getBean("user4");
		System.out.println("User4: " + user4);
		
		// setter로 생성된 bean 얻어오기
		User user5 = (User)ac.getBean("user5");
		System.out.println("User5: " + user5);
		
		System.out.println("===========================================================");
		// 의존성이 주입된 bean 얻어오기
		Friend friend = (Friend)ac.getBean("friend");
		System.out.println("Friend: " + friend);
		
		User user6 = (User)ac.getBean("user6");
		System.out.println("User6: " + user6);
		
		System.out.println("===========================================================");
		// 집합객체가 주입된 bean 얻어오기
		User user7 = (User)ac.getBean("user7");
		System.out.println("User7: " + user7);
	}
}
