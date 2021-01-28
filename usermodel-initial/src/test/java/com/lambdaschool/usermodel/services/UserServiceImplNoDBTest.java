package com.lambdaschool.usermodel.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.usermodel.UserModelApplicationTesting;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.models.Useremail;
import com.lambdaschool.usermodel.repository.RoleRepository;
import com.lambdaschool.usermodel.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplicationTesting.class,
properties = {
        "command.line.runner.enabled=false"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplNoDBTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private List<User> userList = new ArrayList<>();

    private  List<Role> roleList = new ArrayList<>();




    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);


        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1.setRoleid(1);
        r2.setRoleid(2);
        r3.setRoleid(3);

        roleList.add(r1);
        roleList.add(r2);
        roleList.add(r3);

        // admin, data, user
        User u1 = new User("admin",
                "password",
                "admin@lambdaschool.local");
        u1.getRoles()
                .add(new UserRoles(u1,
                        r1));
        u1.getRoles()
                .add(new UserRoles(u1,
                        r2));
        u1.getRoles()
                .add(new UserRoles(u1,
                        r3));
        u1.getUseremails()
                .add(new Useremail(u1,
                        "admin@email.local"));
        u1.getUseremails()
                .add(new Useremail(u1,
                        "admin@mymail.local"));


        // data, user
        User u2 = new User("cinnamon",
                "1234567",
                "cinnamon@lambdaschool.local");
        u2.getRoles()
                .add(new UserRoles(u2,
                        r2));
        u2.getRoles()
                .add(new UserRoles(u2,
                        r3));
        u2.getUseremails()
                .add(new Useremail(u2,
                        "cinnamon@mymail.local"));
        u2.getUseremails()
                .add(new Useremail(u2,
                        "hops@mymail.local"));
        u2.getUseremails()
                .add(new Useremail(u2,
                        "bunny@email.local"));

        // user
        User u3 = new User("barnbarn",
                "ILuvM4th!",
                "barnbarn@lambdaschool.local");
        u3.getRoles()
                .add(new UserRoles(u3,
                        r2));
        u3.getUseremails()
                .add(new Useremail(u3,
                        "barnbarn@email.local"));

        User u4 = new User("puttat",
                "password",
                "puttat@school.lambda");
        u4.getRoles()
                .add(new UserRoles(u4,
                        r2));

        User u5 = new User("misskitty",
                "password",
                "misskitty@school.lambda");
        u5.getRoles()
                .add(new UserRoles(u5,
                        r2));

        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);
        userList.add(u5);


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void b_findUserById() {
        Mockito.when(userRepository.findById((long) 3))
                .thenReturn(Optional.of(userList.get(2)));
        assertEquals("barnbarn",
                userService.findUserById(3).getUsername());
    }

    @Test
    public void c_findByNameContaining() {
        Mockito.when(userRepository.findByUsernameContainingIgnoreCase("utt"))
                .thenReturn(userList);

        assertEquals(5,
                userService.findByNameContaining("utt").size());
    }

    @Test
    public void a_findAll() {
        Mockito.when(userRepository.findAll())
                .thenReturn(userList);

        assertEquals(5,
                userService.findAll().size());
    }

    @Test
    public void g_delete() {

        Mockito.when(userRepository.findById(2L))
                .thenReturn(Optional.of(userList.get(0)));

        Mockito.doNothing()
                .when(userRepository)
                .deleteById(2L);

        userService.delete(2);
        assertEquals(5, userList.size());

    }

    @Test
    public void d_findByName() {
        Mockito.when(userRepository.findByUsername("misskitty"))
                .thenReturn(userList.get(4));

        assertEquals("misskitty",
                userService.findByName("misskitty").getUsername());
    }

    @Test
    public void f_save() {
    }

    @Test
    public void e_update() {

    }

    @Test
    public void k_deleteAll() {
    }
}