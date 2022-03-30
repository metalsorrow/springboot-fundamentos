package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.Entity.User;
import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropeties;
import com.fundamentos.springboot.fundamentos.bean.MyPrinter;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog((FundamentosApplication.class));

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithPropeties myBeanWithPropeties;
	private UserPojo userPojo;

	private MyBeanWithDependency myBeanWithDependency;
	private MyPrinter myPrinter;
	private UserRepository userRepository;


	@Autowired
	public FundamentosApplication(
			@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
			MyBean myBean,
			MyBeanWithDependency myBeanWithDependency,
			MyPrinter myPrinter,
			MyBeanWithPropeties myBeanWithPropeties,
			UserPojo userPojo,
			UserRepository userRepository
	) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myPrinter = myPrinter;
		this.myBeanWithPropeties = myBeanWithPropeties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	private void getInformationJpsqlFromUser(){
		LOGGER.info("Usuario encontrado: "+userRepository.findByUserEmail("john@domain.com").orElseThrow(()-> new RuntimeException("No se encontro el usuario")));
		userRepository.findAndSort("domain", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort "+ user));

		userRepository.findByName("John")
				.stream()
				.forEach(user ->  LOGGER.info("Usuario con query method " + user));
		LOGGER.info(
			userRepository.findByEmailAndName("john@domain.com", "John")
					.orElseThrow(()-> new RuntimeException("Usuario no encontrado"))
		);
	}

	private void saveUserInDataBase(){
		User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));
		List<User> list = Arrays.asList(user1,user2,user3, user4, user5,user6,user7,user8,user9);
		list.forEach(userRepository::save);
	}

	@Override
	public void run(String... args) throws Exception {
//		this.ejemplos();
		this.saveUserInDataBase();
		this.getInformationJpsqlFromUser();
	}

	private void ejemplos(){
		this.componentDependency.saludar();
		this.myBean.print();
		this.myBeanWithDependency.printWithDependency();
		this.myPrinter.printerInt();
		System.out.println(
				this.myBeanWithPropeties.function()
		);
		System.out.println(
				this.userPojo.getEmail()
		);
		try {
			int value = 10 / 0;
			this.LOGGER.info("Mi valor:" + value);
		} catch (Exception e){
			this.LOGGER.error("Esto es un Error: " + e.getStackTrace());
		}
	}
}
