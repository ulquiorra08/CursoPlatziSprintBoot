package com.example.demo;

import com.example.demo.bean.MyBean;
import com.example.demo.bean.MyBeanWithDependency;
import com.example.demo.bean.MyBeanWithProperties;
import com.example.demo.component.ComponentDependency;
import com.example.demo.entity.User;
import com.example.demo.pojo.UserPojo;
import com.example.demo.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Log log = LogFactory.getLog(DemoApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	public DemoApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,MyBean myBean,MyBeanWithDependency myBeanWithDependency,MyBeanWithProperties myBeanWithProperties,UserPojo userPojo,UserRepository userRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUserInDataBase();
	}

	private void saveUserInDataBase(){
		User user = new User("Camilo","camilo@gmail.com", LocalDate.of(2000,05,01));
		User user1 = new User("Jesus","Jesus@gmail.com", LocalDate.of(2000,12,01));
		User user2 = new User("Daniela","daniela@gmail.com", LocalDate.of(2000,05,02));
		User user3 = new User("Maria","maria@gmail.com", LocalDate.of(2000,05,03));
		User user4 = new User("Pacho","pacho@gmail.com", LocalDate.of(2000,05,04));
		User user5 = new User("Lorena","lorena@gmail.com", LocalDate.of(2000,05,05));
		User user6 = new User("Angie","angie@gmail.com", LocalDate.of(2000,05,06));
		User user7 = new User("Pablo","pablo@gmail.com", LocalDate.of(2000,05,07));
		User user8 = new User("Mafe","mafe@gmail.com", LocalDate.of(2000,05,07));
		User user9 = new User("Diana","diana@gmail.com", LocalDate.of(2000,05,07));
		List<User> userList = Arrays.asList(user,user1,user2,user3,user4,user5,user6,user7,user8,user9);
		userList.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+"-"+userPojo.getPassword());
		try{
			int value = 10/0;
			log.info("Mi valor :"+value);
		}catch (Exception e){
			log.error("Esto es un error al dividir por cero "+e.getMessage());
		}
	}
}
