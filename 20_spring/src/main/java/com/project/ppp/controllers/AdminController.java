package com.project.ppp.controllers;

import com.project.ppp.dao.RoleDao;
import com.project.ppp.dao.UserDao;
import com.project.ppp.entity.Role;
import com.project.ppp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "getUsers";
    }

    @DeleteMapping ("/{id}")
    public String deleteUserById(@PathVariable(name = "id", required = false) int id, Model model) {
        userDao.remove(userDao.findById(id));
        model.addAttribute("name", "User has been deleted successively. Redirecting");
        return "ok";
    }

    @GetMapping("/add")
    public String getFromForNewUser(Model model) {
        model.addAttribute("roles", roleDao.findAll());
        return "addUsers";
    }

    @PostMapping()
    public String addNewUser(@RequestParam("login") String login, @RequestParam("password") String password,
                             @RequestParam("email") String email, @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName, @RequestParam("birthday") String birthday,
                             @RequestParam("role") long roleId, Model model) {
        User user = setUser(0, login, password, email, firstName, lastName, birthday, roleId);
        userDao.create(user);
        model.addAttribute("name", "User " + user.getFirstName()
                + " has been created successively. Redirecting");
        return "ok";
    }

    @GetMapping("/{id}/edit")
    public String getFormForUpdateUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userDao.findById(id));
        model.addAttribute("roles", roleDao.findAll());
        return "updateUsers";
    }

    @PatchMapping("/{id}/edit")
    public String updateUser(@RequestParam("login") String login, @RequestParam("password") String password,
                             @RequestParam("email") String email, @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName, @RequestParam("birthday") String birthday,
                             @RequestParam("role") long roleId, @PathVariable("id") long userId, Model model){

        User user = setUser(userId, login, password, email, firstName, lastName, birthday, roleId);
        userDao.update(user);
        model.addAttribute("name", "User " + user.getFirstName()
                + " has been updated successively. Redirecting");
        return "ok";
    }

    public User setUser(long userId, String login, String password, String email, String firstName, String lastName, String birthday, long roleId){
        User user = new User();
        user.setId(userId);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = sdf.parse(birthday);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        user.setBirthday(sqlStartDate);
        Role role = roleDao.findById(roleId);
        user.setRole(role);
        return user;

    }

}
