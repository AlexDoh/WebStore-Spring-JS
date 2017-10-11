package com.odmytrenko.spring.dao;

import com.odmytrenko.spring.model.RolesClassWrapper;
import com.odmytrenko.spring.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
            return jdbcTemplate.queryForObject(
                    "SELECT  U.ID, U.NAME AS UNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME AS RNAME FROM USERS AS U" +
                            " JOIN USERTOROLE AS UR ON UR.USERID=U.ID" +
                            " JOIN ROLES AS R ON R.ID = UR.ROLEID" +
                            " WHERE U.NAME = ? AND U.PASSWORD = ?;",
                    new Object[]{username, password},
                    (rs, rowNum) -> {
                        User result = new User();
                        result.setId(rs.getLong("ID"));
                        result.setName(rs.getString("UNAME"));
                        result.setPassword(rs.getString("PASSWORD"));
                        result.setEmail(rs.getString("EMAIL"));
                        result.setToken(rs.getString("TOKEN"));
                        result.setRoles(getUserRolesFromQuery(rs));
                        return result;
                    }
            );
        } catch (Exception e) {
            throw new RuntimeException("User with such login and password doesn't exist!");
        }
    }

    @Override
    public User findByToken(String token) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT U.ID, U.NAME AS UNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME AS RNAME" +
                            " FROM USERS U" +
                            " JOIN USERTOROLE UR ON UR.USERID=U.ID" +
                            " JOIN ROLES R ON R.ID = UR.ROLEID" +
                            " WHERE U.TOKEN = ?;",
                    new Object[]{token},
                    (rs, rowNum) -> {
                        User result = new User();
                        result.setId(rs.getLong("ID"));
                        result.setName(rs.getString("NAME"));
                        result.setPassword(rs.getString("PASSWORD"));
                        result.setEmail(rs.getString("EMAIL"));
                        result.setToken(rs.getString("TOKEN"));
                        result.setRoles(getUserRolesFromQuery(rs));
                        return result;
                    }
            );
        } catch (Exception e) {
            throw new RuntimeException("You have corrupted cookies in the browser, please clean them");
        }
    }

    @Override
    public User updateForUser(User user) {
        String updateUserQuery = "UPDATE USERS SET PASSWORD = ?, EMAIL = ? WHERE NAME = ?;";
        jdbcTemplate.update(updateUserQuery,
                user.getPassword(),
                user.getEmail(),
                user.getName()
        );
        return user;
    }

    @Override
    public Set<User> getAll() {
        Map<String, User> userMap = new HashMap<>();
        jdbcTemplate.query("SELECT U.ID, U.NAME AS UNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME AS RNAME" +
                " FROM USERS U" +
                " JOIN USERTOROLE UR ON UR.USERID=U.ID" +
                " JOIN ROLES R ON R.ID = UR.ROLEID", (rs, rowNum) -> {
            User user = new User();
            String userName = rs.getString("UNAME");
            String roleName = rs.getString("RNAME");
            user.setId(rs.getLong("ID"));
            user.setName(userName);
            user.setPassword(rs.getString("PASSWORD"));
            user.setEmail(rs.getString("EMAIL"));
            user.setToken(rs.getString("TOKEN"));
            if (userMap.containsKey(userName)) {
                userMap.get(userName).getRoles().add(
                        RolesClassWrapper.valueOf(roleName));
            } else {
                Set<RolesClassWrapper> roles = new HashSet<>();
                roles.add(RolesClassWrapper.valueOf(roleName));
                user.setRoles(roles);
                userMap.put(userName, user);
            }
            return user;
        });
        return new HashSet<>(userMap.values());
    }

    private Set<RolesClassWrapper> getUserRolesFromQuery(ResultSet rs) throws SQLException {
        Set<RolesClassWrapper> roles = new HashSet<>();
        roles.add(RolesClassWrapper.valueOf(rs.getString("RNAME")));
        try {
            while (rs.next()) {
                roles.add(RolesClassWrapper.valueOf(rs.getString("RNAME")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("There are problems with getting users" + e);
        }
        return roles;
    }

    @Override
    public User create(User user) {
        if(doesUserExist(user.getName())) {
            throw new RuntimeException("Such user already exists");
        }
        String createUserQuery = "INSERT INTO USERS (NAME, TOKEN, PASSWORD, EMAIL) VALUES(?, ?, ?, ?);";
        String addRoleQuery = "INSERT INTO USERTOROLE (USERID, ROLEID) VALUES(" +
                "(SELECT ID FROM USERS WHERE NAME = ?), 2);";
        jdbcTemplate.update(createUserQuery,
                user.getName(),
                user.getToken(),
                user.getPassword(),
                user.getEmail()
        );
        jdbcTemplate.update(addRoleQuery, user.getName());
        return user;
    }

    @Override
    public User delete(User user) {
        if(!doesUserExist(user.getName())) {
            throw new RuntimeException("There is no such user");
        }
        String deleteUserRoleQuery = "DELETE FROM USERTOROLE WHERE USERID = (SELECT ID FROM USERS WHERE NAME = ?);";
        String deleteUserQuery = "DELETE FROM USERS WHERE NAME = ?;";
        jdbcTemplate.update(deleteUserRoleQuery, user.getName());
        jdbcTemplate.update(deleteUserQuery, user.getName());
        return user;
    }

    @Override
    public User update(User user) {
        String updateUserQuery = "UPDATE USERS SET PASSWORD = ?, EMAIL = ? WHERE NAME = ?;";
        String deleteUserRoleQuery = "DELETE FROM USERTOROLE WHERE USERID = (SELECT ID FROM USERS WHERE NAME = ?);";
        String insertUserRoleQuery = "INSERT INTO USERTOROLE (USERID, ROLEID) VALUES((SELECT ID FROM USERS" +
                " WHERE NAME = ?), (SELECT ID FROM ROLES WHERE NAME = ?));";
        jdbcTemplate.update(updateUserQuery,
                user.getPassword(),
                user.getEmail(),
                user.getName()
        );
        jdbcTemplate.update(deleteUserRoleQuery, user.getName());
        user.getRoles().stream().map(RolesClassWrapper::getName).forEach(r -> jdbcTemplate.update(insertUserRoleQuery,
                user.getName(),
                r
        ));
        return user;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    private boolean doesUserExist(String userName) {
        return jdbcTemplate.queryForRowSet("SELECT * FROM USERS WHERE NAME = ?;", userName).next();
    }
}
