/*
import com.project.ppp.dao.jdbc.JdbcRoleService;
import com.project.ppp.dao.jdbc.JdbcUserService;
import com.project.ppp.entity.Role;
import com.project.ppp.entity.User;
import com.project.ppp.util.SessionUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.sql.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class DBUnitTest {


    private JdbcRoleService roleService;
    private JdbcUserService userService;

    public DBUnitTest() throws IOException {
        this.roleService = new JdbcRoleService();
        this.userService = new JdbcUserService();
    }


    @Test
    public void checkTheContentOfRoleAndUser() {
        Role role = roleService.findByName("ADMIN");
        User user = userService.findByLogin("loginMaks");
        assertThat(role.getName(), is("ADMIN"));
        assertThat(user.getLogin(), is("loginMaks"));
    }

    @Test
    public void checkTheCreationOfRoleAndUser() {
        Role role = new Role();
        role.setName("RoleForTest");
        roleService.create(role);
        Role role2 = roleService.findByName("RoleForTest");
        User user = new User();
        user.setLogin("LoginForTest");
        user.setEmail("TestEmail");
        user.setPassword("TestPassword");
        user.setFirstName("FirstNameForTest");
        user.setLastName("TestLN");
        user.setBirthday(new Date(204587433443L));
        user.setRole(role);
        userService.create(user);
        User user2 = userService.findByLogin("LoginForTest");
        assertThat(role2.getName(), is("RoleForTest"));
        assertThat(user2.getFirstName(), is("FirstNameForTest"));
        userService.remove(user);
        roleService.remove(role);
    }

    @Test
    public void checkTheUpdateOfRoleAndUser() {
        Role role1 = roleService.findByName("USER");
        assertThat(role1.getId(), is(2L));
        Role role2 = new Role();
        role2.setId(2);
        role2.setName("Test_the_update");
        roleService.update(role2);
        Role role3 = roleService.findByName("Test_the_update");
        assertThat(role3.getId(), is(2L));
        User user1 = userService.findByEmail("dima@gmail.com");
        assertThat(user1.getId(), is(2L));
        User user2 = new User();
        user2.setId(2);
        user2.setLogin("Test_the_update");
        user2.setPassword("Test_the_update");
        user2.setEmail("Test_the_update");
        user2.setFirstName("Test_the_update");
        user2.setLastName("Test_the_update");
        user2.setBirthday(new Date(204587433443L));
        user2.setRole(roleService.findById(1));
        userService.update(user2);
        User user3 = userService.findByEmail("Test_the_update");
        assertThat(user3.getId(), is(2L));
        Role role4 = new Role();
        role4.setId(2);
        role4.setName("USER");
        roleService.update(role4);
        User user5 = new User();
        user5.setId(2);
        user5.setLogin("loginDima");
        user5.setPassword("password2");
        user5.setEmail("dima@gmail.com");
        user5.setFirstName("Dima");
        user5.setLastName("B");
        user5.setBirthday(new Date(768603600000L));
        user5.setRole(roleService.findById(2));
        userService.update(user5);
    }

    @Test(expected = Exception.class)
    public void checkTheRemoveOfRoleAndUser() {
        Role role = new Role();
        role.setName("TEST");
        Role role1 = roleService.findByName("TEST");
        Assert.assertNotNull(role1);
        roleService.remove(role1);
        roleService.findByName("TEST");
        User user = new User();
        user.setEmail("TEST_EMAIL");
        User user1 = userService.findByEmail("TEST_EMAIL");
        Assert.assertNotNull(user1);
        userService.remove(user1);
    }

    @Test
    public void checkGetByLoginAndPassword() {
        User user = new User();
        user.setLogin("TEST");
        user.setEmail("TEST");
        user.setBirthday(new Date(768603600000L));
        user.setLastName("TEST");
        user.setFirstName("TEST");
        user.setPassword("TEST");
        user.setRole(roleService.findById(1));
        userService.create(user);
        User user1 = userService.findByLogin("TEST");
        assertThat(user1.getPassword(), is("TEST"));
        userService.remove(user);
    }

    @Test
    public void checkExistingUser() {
        User user = new User();
        user.setLogin("TEST");
        user.setEmail("TEST");
        user.setBirthday(new Date(768603600000L));
        user.setLastName("TEST");
        user.setFirstName("TEST");
        user.setPassword("TEST");
        user.setRole(roleService.findById(1));
        userService.create(user);
        User user1 = userService.findByLogin("TEST");
        Assert.assertNotNull(user1);
        userService.remove(user);
    }
}

*/
