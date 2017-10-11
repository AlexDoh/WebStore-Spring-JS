package com.odmytrenko.spring.model;

import javax.persistence.*;
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
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users;

    RolesClassWrapper() {}

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

    public static RolesClassWrapper valueOf(String role) {
        RolesClassWrapper rolesClassWrapper = new RolesClassWrapper();
        rolesClassWrapper.setName(Roles.valueOf(role));
        return rolesClassWrapper;
    }
}
