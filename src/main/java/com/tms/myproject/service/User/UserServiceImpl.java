package com.tms.myproject.service.User;

import com.tms.myproject.model.Role;
import com.tms.myproject.model.User;
import com.tms.myproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@Service
@Transactional
public class UserServiceImpl implements UserService{
   private final UserRepository userRepository;

   @Autowired
   public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

}
