package com.tms.myproject.repository;

import com.tms.myproject.model.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class InitClass implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextRefreshedEvent) {
            ApplicationContext context = ((ContextRefreshedEvent) applicationEvent).getApplicationContext();
            RoleRepository roleRepsoitory = context.getBean(RoleRepository.class);

            Role roleUser = new Role();
            Role roleAdmin = new Role();
            roleUser.setName("USER");
            roleAdmin.setName("ADMIN");
            roleRepsoitory.save(roleUser);
            roleRepsoitory.save(roleAdmin);
        }
    }
}
