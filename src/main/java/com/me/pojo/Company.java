package com.me.pojo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {
    private Long id;
    private String name;
    private Set<User> users;
    private User manager;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany
    @JoinTable(name ="user_company",
            joinColumns = {@JoinColumn(name ="company_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @JoinColumn( name = "managerid", unique = true)
    @OneToOne(cascade = CascadeType.MERGE)
    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
