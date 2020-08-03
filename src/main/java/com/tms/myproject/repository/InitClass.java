package com.tms.myproject.repository;

import com.tms.myproject.model.Author;
import com.tms.myproject.model.Role;
import com.tms.myproject.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Arrays;

@Repository
@Transactional
public class InitClass implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextRefreshedEvent) {
            ApplicationContext context = ((ContextRefreshedEvent) applicationEvent).getApplicationContext();
            RoleRepository roleRepsoitory = context.getBean(RoleRepository.class);
            UserRepository userRepository = context.getBean(UserRepository.class);
            AuthorRepository authorRepository = context.getBean(AuthorRepository.class);

            Role roleUser = new Role();
            Role roleAdmin = new Role();
            roleUser.setName("USER");
            roleAdmin.setName("ADMIN");
            roleRepsoitory.save(roleUser);
            roleRepsoitory.save(roleAdmin);

            User user = new User();
            User admin = new User();
            user.setName("user");
            user.setRoles(Arrays.asList(roleUser));
            user.setPassword("qwert");
            user.setLogin("user");
            Author author = new Author();
            author.setFullName(user.getName());
            userRepository.save(user);
            authorRepository.save(author);

            admin.setName("admin");
            admin.setRoles(Arrays.asList(roleAdmin));
            admin.setPassword("qwert");
            admin.setLogin("admin");
            Author author1 = new Author();
            author1.setFullName(admin.getName());
            userRepository.save(admin);
            authorRepository.save(author1);

        }
    }
}
