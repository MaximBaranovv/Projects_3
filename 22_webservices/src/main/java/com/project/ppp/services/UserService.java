package com.project.ppp.services;

import com.project.ppp.dao.UserDao;
import com.project.ppp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByLogin(username);
        System.out.println("UserInfo= " + user);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        if (user.getRole() == null) {
            throw new UsernameNotFoundException("User doesn't have any role");
        }

        List<GrantedAuthority> grantList = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantList);
    }
}
