package com.odmytrenko.spring.dao;

import com.odmytrenko.spring.model.Roles;
import com.odmytrenko.spring.model.RolesClassWrapper;
import com.odmytrenko.spring.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl(JdbcTemplate jt) {
        jdbcTemplate = jt;
        String createUsersTable = "CREATE TABLE IF NOT EXISTS USERS (" +
                "   ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "   NAME VARCHAR(30)," +
                "   TOKEN VARCHAR(255)," +
                "   PASSWORD VARCHAR(255)," +
                "   EMAIL VARCHAR(30)," +
                ");";
        String createRolesTable = "CREATE TABLE IF NOT EXISTS ROLES (" +
                "   ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "   NAME VARCHAR(30)" +
                ");";
        String createUserToRoleTable = "CREATE TABLE IF NOT EXISTS USERTOROLE (" +
                "   ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "   USERID INT," +
                "   ROLEID INT," +
                "   FOREIGN KEY (ROLEID) REFERENCES ROLES(ID)," +
                "   FOREIGN KEY (USERID) REFERENCES USERS(ID)" +
                ");";
        jdbcTemplate.execute(createUsersTable);
        jdbcTemplate.execute(createRolesTable);
        jdbcTemplate.execute(createUserToRoleTable);

        String user1 = "MERGE INTO USERS (NAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                "'User1', '123123', 'player1@gmail.com', 'token1', 1);";
        String user2 = "MERGE INTO USERS (NAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                "'User2', '123123', 'player2@gmail.com', 'token2', 2);";
        String user3 = "MERGE INTO USERS (NAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                "'User3', '123123', 'player3@gmail.com', 'token3', 3);";
        String roles = "MERGE INTO ROLES (NAME, ID) VALUES('ADMIN', 1);" +
                "MERGE INTO ROLES (NAME, ID) VALUES('USER', 2);" +
                "MERGE INTO ROLES (NAME, ID) VALUES('MODERATOR', 3);";
        String roleAssignments = "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(1, 1, 1);" +
                "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(1, 2, 2);" +
                "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(2, 2, 3);" +
                "MERGE INTO USERTOROLE (USERID, ROLEID, ID) VALUES(3, 2, 4);";
        jdbcTemplate.execute(user1);
        jdbcTemplate.execute(user2);
        jdbcTemplate.execute(user3);
        jdbcTemplate.execute(roles);
        jdbcTemplate.execute(roleAssignments);
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        try {
            String query = "from User where name =:name and password =:password";
            return (User) sessionFactory.getCurrentSession().createQuery(query)
                    .setParameter("name", username)
                    .setParameter("password", password)
                    .uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("User with such login and password doesn't exist!");
        }
    }

    @Override
    public User findByToken(String token) {
        try {
            String query = "from User where token =:token";
            return (User) sessionFactory.getCurrentSession().createQuery(query)
                    .setParameter("token", token)
                    .uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("You have corrupted cookies in the browser, please clean them");
        }
    }

    @Override
    public User updateForUser(User user) {
        String query = "update User baseUser set baseUser.password =:password, baseUser.email =:email" +
                " where baseUser.name =:name";
        sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("password", user.getPassword())
                .setParameter("email", user.getEmail())
                .setParameter("name", user.getName())
                .executeUpdate();
        return user;
    }

    @Override
    public Set<User> getAll() {
        return new HashSet<User>(sessionFactory.getCurrentSession().createCriteria(User.class).list());
    }

    @Override
    public User create(User user) {
        if (doesUserExist(user.getName())) {
            throw new RuntimeException("Such user is already exists");
        }
        Set<RolesClassWrapper> roles = new HashSet<>();
        RolesClassWrapper rolesClassWrapper = new RolesClassWrapper();
        rolesClassWrapper.setName(Roles.USER);
        rolesClassWrapper.setId(2L);
        roles.add(rolesClassWrapper);
        user.setRoles(roles);
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public User delete(User user) {
        if (!doesUserExist(user.getName())) {
            throw new RuntimeException("There is no such user");
        }
        String query = "delete User where name=:name";
        sessionFactory.getCurrentSession().createQuery(query).setParameter("name", user.getName()).executeUpdate();
        return user;
    }

    @Override
    public User update(User user) {
        user.getRoles().forEach(r -> r.setId(r.getName().ordinal() + 1L));
        sessionFactory.getCurrentSession().update(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    private boolean doesUserExist(String userName) {
       return sessionFactory.getCurrentSession().createQuery("from User where name =:name")
               .setParameter("name", userName).uniqueResult() != null;
    }
}
