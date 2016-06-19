package main.java.ru.javawebinar.topjava;

import main.java.ru.javawebinar.topjava.model.User;
import main.java.ru.javawebinar.topjava.web.user.AdminRestController;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import main.java.ru.javawebinar.topjava.model.Role;
import main.java.ru.javawebinar.topjava.model.User;
import main.java.ru.javawebinar.topjava.repository.UserRepository;
import main.java.ru.javawebinar.topjava.service.UserService;

import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            System.out.println(adminUserController.create(new User(1, "userName", "email", "password", Role.ROLE_ADMIN)));

//       UserRepository userRepository = (UserRepository) appCtx.getBean("mockUserRepository");
            UserRepository userRepository = appCtx.getBean(UserRepository.class);
            userRepository.getAll();

            UserService userService = appCtx.getBean(UserService.class);
            System.out.println(userService.save(new User(1, "userName", "email", "password", Role.ROLE_ADMIN)));

            appCtx.close();
        }
        }
}
