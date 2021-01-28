package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplicationTesting;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplicationTesting.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplDBTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void b_findUserById() {
        assertEquals("barnbarn",
                userService.findUserById(11).getUsername());
    }

    @Test
    public void d_findByNameContaining() {
        assertEquals(1,
                userService.findByNameContaining("utt").size());
    }

    @Test
    public void a_findAll() {
        assertEquals(5,
                userService.findAll().size());
    }

    @Test
    public void g_delete() {
        userService.delete(13);
        assertEquals(5, userService.findAll().size());
    }

    @Test
    public void c_findByName() {
        assertEquals(4,
                userService.findByName("admin").getUserid());
    }

    @Test
    public void f_save() {

        Role role = roleService.findRoleById(1);

        User user = new User("mimimi",
                "password",
                "my@school.lambda");
        user.getRoles().add(
                new UserRoles(user, role)
        );

        User addUser = userService.save(user);
        assertNotNull(addUser);
        assertEquals(user.getUsername(),
                addUser.getUsername());


    }

    @Test
    public void e_update() {
        Role role = roleService.findRoleById(1);

        User user = new User("mimimi",
                "password",
                "my@school.lambda");
        user.getRoles().add(
                new UserRoles(user, role)
        );

        User addUser = userService.update(user, 14);
        assertNotNull(addUser);
        assertEquals(user.getUsername(),
                addUser.getUsername());


    }

    @Test
    public void k_deleteAll() {
        userService.deleteAll();
        assertEquals(0,
                userService.findAll().size());
    }
}