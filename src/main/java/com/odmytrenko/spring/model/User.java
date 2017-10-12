package com.odmytrenko.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User extends Model {

    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "TOKEN")
    private String token;
    @Column(name = "EMAIL")
    private String email;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "USERTOROLE",
            joinColumns = {@JoinColumn(name = "USERID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "ROLEID", nullable = false, updatable = false) })
    private Set<RolesClassWrapper> roles = new HashSet<>();

    public User() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RolesClassWrapper> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolesClassWrapper> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        return getName() != null ? getName().equals(user.getName()) : user.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getPassword() != null ? getPassword().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}