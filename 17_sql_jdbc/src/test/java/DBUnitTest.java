import com.project.ppp.dao.RoleDao;
import com.project.ppp.dao.UserDao;
import com.project.ppp.dao.jdbc.JdbcRoleDao;
import com.project.ppp.dao.jdbc.JdbcUserDao;
import com.project.ppp.entity.Role;
import com.project.ppp.entity.User;
import configuration.ConnectionSupplier;
import configuration.jdbc.ConnectionSupplierJdbc;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class DBUnitTest extends DataSourceBasedDBTestCase {

    private final ConnectionSupplier supplier;
    private final RoleDao roleDao;
    private final UserDao userDao;

    public DBUnitTest() throws IOException {
        this.supplier = new ConnectionSupplierJdbc();
        this.roleDao = new JdbcRoleDao(this.supplier);
        this.userDao = new JdbcUserDao(this.supplier);
    }

    @Override
    protected DataSource getDataSource() {
        return supplier.getDataSource();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("data.xml"));
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    @Test
    public void checkTheContentOfRoleAndUser() {
        Role role = roleDao.findByName("ADMIN");
        User user = userDao.findByLogin("loginMaks");
        assertThat(role.getName(), is("ADMIN"));
        assertThat(user.getLogin(), is("loginMaks"));
    }

    @Test
    public void checkTheCreationOfRoleAndUser() {
        Role role = new Role();
        role.setId(3);
        role.setName("TestRole");
        roleDao.create(role);
        Role role2 = roleDao.findByName("TestRole");
        User user = new User();
        user.setLogin("TestLogin");
        user.setEmail("TestEmail");
        user.setPassword("TestPassword");
        user.setFirstName("TestFN");
        user.setLastName("TestLN");
        user.setBirthday(new Date(204587433443L));
        user.setRole(roleDao.findById(2));
        userDao.create(user);
        User user2 = userDao.findByLogin("TestLogin");
        assertThat(role2.getName(), is("TestRole"));
        assertThat(user2.getFirstName(), is("TestFN"));
    }

    @Test
    public void checkTheUpdateOfRoleAndUser() {
        Role role1 = roleDao.findByName("USER");
        assertThat(role1.getId(), is(2L));
        Role role2 = new Role();
        role2.setId(2);
        role2.setName("Test_the_update");
        roleDao.update(role2);
        Role role3 = roleDao.findByName("Test_the_update");
        assertThat(role3.getId(), is(2L));
        User user1 = userDao.findByEmail("dima@gmail.com");
        assertThat(user1.getId(), is(2L));
        User user2 = new User();
        user2.setId(2);
        user2.setLogin("Test_the_update");
        user2.setPassword("Test_the_update");
        user2.setEmail("Test_the_update");
        user2.setFirstName("Test_the_update");
        user2.setLastName("Test_the_update");
        user2.setBirthday(new Date(204587433443L));
        user2.setRole(roleDao.findById(1));
        userDao.update(user2);
        User user3 = userDao.findByEmail("Test_the_update");
        assertThat(user3.getId(), is(2L));
    }

    @Test(expected = RuntimeException.class)
    public void checkTheRemoveOfRoleAndUser() {
        Role role1 = roleDao.findByName("USER");
        Assert.assertNotNull(role1);
        Role role2 = new Role();
        role2.setId(2);
        roleDao.remove(role2);
        roleDao.findByName("USER");
        User user1 = userDao.findByEmail("maks@gmail.com");
        Assert.assertNotNull(user1);
        User user2 = new User();
        user2.setId(1);
        userDao.remove(user2);
    }

    @Test
    public void chekFindAll(){
        List<Role> roleList1 = roleDao.findAll();
        List<Role> roleList2 = new ArrayList<>();
        Role role1 = new Role();
        role1.setId(1);
        role1.setName("ADMIN");
        Role role2 = new Role();
        role2.setId(2);
        role2.setName("USER");
        roleList2.add(role1);
        roleList2.add(role2);
        Assert.assertEquals(roleList1, roleList2);
        List<User> userList1 = userDao.findAll();
        List<User> userList2 = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setLogin("loginMaks");
        user1.setPassword("password1");
        user1.setEmail("maks@gmail.com");
        user1.setFirstName("Maks");
        user1.setLastName("Bar");
        user1.setBirthday(new Date(958856400000L));
        user1.setRole(roleDao.findById(1));
        User user2 = new User();
        user2.setId(2);
        user2.setLogin("loginDima");
        user2.setPassword("password2");
        user2.setEmail("dima@gmail.com");
        user2.setFirstName("Dima");
        user2.setLastName("B");
        user2.setBirthday(new Date(768603600000L));
        user2.setRole(roleDao.findById(2));
        userList2.add(user1);
        userList2.add(user2);
        Assert.assertEquals(userList1, userList2);
    }
}
