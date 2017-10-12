package com.odmytrenko.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import java.util.Set;

@Entity
@Table(name = "ROLES")
public class RolesClassWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME")
    @Enumerated(EnumType.STRING)
    private Roles name;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> users;

    public RolesClassWrapper() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getName() {
        return name;
    }

    public void setName(Roles name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public static RolesClassWrapper wrapRole(Roles role) {
        RolesClassWrapper rolesClassWrapper = new RolesClassWrapper();
        rolesClassWrapper.setName(role);
        return rolesClassWrapper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesClassWrapper that = (RolesClassWrapper) o;

        return getName() == that.getName();
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
