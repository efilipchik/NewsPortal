package com.tms.myproject.config.provider;

import com.tms.myproject.model.Author;
import com.tms.myproject.model.User;
import com.tms.myproject.repository.AuthorRepository;
import com.tms.myproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppMyProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public AppMyProvider(UserRepository userRepository, AuthorRepository authorRepository) {
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userRepository.findByLogin(authentication.getName());
        if (user == null) {
            throw new UsernameNotFoundException("USER NOT FOUND!!!!!!!!!!!!!!!!!!!");
        }
        if (!user.getPassword().equals(authentication.getCredentials().toString())) {
            throw new BadCredentialsException("Incorrect password!!!");
        }

        Author author = authorRepository.findByAuthorFullName(user.getName());

        List<SimpleGrantedAuthority> authorityList = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName().toString()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(author, null, authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
