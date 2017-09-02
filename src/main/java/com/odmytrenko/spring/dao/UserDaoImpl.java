package com.odmytrenko.spring.dao;

import com.odmytrenko.spring.model.Roles;
import com.odmytrenko.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jt) {
        jdbcTemplate = jt;
        String createUsersTable = "CREATE TABLE IF NOT EXISTS USERS (" +
                "   ID INT PRIMARY KEY AUTO_INCREMENT," +
                "   USERNAME VARCHAR(30)," +
                "   TOKEN VARCHAR(255)," +
                "   PASSWORD VARCHAR(255)," +
                "   EMAIL VARCHAR(30)," +
                ");";
        String createRolesTable = "CREATE TABLE IF NOT EXISTS ROLES (" +
                "   ID INT PRIMARY KEY AUTO_INCREMENT," +
                "   NAME VARCHAR(30)" +
                ");";
        String createUserToRoleTable = "CREATE TABLE IF NOT EXISTS USERTOROLE (" +
                "   ID INT PRIMARY KEY AUTO_INCREMENT," +
                "   USERID INT," +
                "   ROLEID INT," +
                "   FOREIGN KEY (ROLEID) REFERENCES ROLES(ID)," +
                "   FOREIGN KEY (USERID) REFERENCES USERS(ID)" +
                ");";
        jdbcTemplate.execute(createUsersTable);
        jdbcTemplate.execute(createRolesTable);
        jdbcTemplate.execute(createUserToRoleTable);

        String user1 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                "'User1', '123123', 'player1@gmail.com', 'token1', 1);";
        String user2 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
                "'User2', '123123', 'player2@gmail.com', 'token2', 2);";
        String user3 = "MERGE INTO USERS (USERNAME, PASSWORD, EMAIL, TOKEN, ID) VALUES(" +
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
                    "SELECT  U.ID, U.USERNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME FROM USERS AS U" +
                            " JOIN USERTOROLE AS UR ON UR.USERID=U.ID" +
                            " JOIN ROLES AS R ON R.ID = UR.ROLEID" +
                            " WHERE U.USERNAME = ? AND U.PASSWORD = ?;",
                    new Object[]{username, password},
                    (rs, rowNum) -> {
                        User result = new User();
                        result.setId(rs.getLong("ID"));
                        result.setName(rs.getString("USERNAME"));
                        result.setPassword(rs.getString("PASSWORD"));
                        result.setEmail(rs.getString("EMAIL"));
                        result.setToken(rs.getString("TOKEN"));
                        result.setRoles(getUserRolesFromQuery(rs));
                        return result;
                    }
            );
        } catch (Exception e) {
            throw new RuntimeException("User doesn't exist!");
        }
    }

    @Override
    public User findByToken(String token) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT  U.ID, U.USERNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME" +
                            " FROM USERS U" +
                            " JOIN USERTOROLE UR ON UR.USERID=U.ID" +
                            " JOIN ROLES R ON R.ID = UR.ROLEID" +
                            " WHERE U.TOKEN = ?;",
                    new Object[]{token},
                    (rs, rowNum) -> {
                        User result = new User();
                        result.setId(rs.getLong("ID"));
                        result.setName(rs.getString("USERNAME"));
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

    private Set<Roles> getUserRolesFromQuery(ResultSet rs) throws SQLException {
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.valueOf(rs.getString("NAME")));
        try {
            while (rs.next()) {
                roles.add(Roles.valueOf(rs.getString("NAME")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("There are problems with getting users" + e);
        }
        return roles;
    }

    @Override
    public User create(User user) {
        String createUserQuery = "INSERT INTO USERS (USERNAME, TOKEN, PASSWORD, EMAIL) VALUES(?, ?, ?, ?);";
        jdbcTemplate.update(createUserQuery,
                user.getName(),
                user.getToken(),
                user.getPassword(),
                user.getEmail()
        );
        String addRoleQuery = "INSERT INTO USERTOROLE (USERID, ROLEID) VALUES(" +
                "(SELECT ID FROM USERS WHERE USERNAME = ?), 2);";
        jdbcTemplate.update(addRoleQuery, user.getName());
        return user;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
