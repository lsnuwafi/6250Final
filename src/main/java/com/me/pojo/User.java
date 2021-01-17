/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.pojo;




import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author 18572
 */
@Entity
@Table(name = "user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private Company company;
    private Set<Role> roles;
    private String first_name;
    private String last_name;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

//    @OneToOne(mappedBy = "manager", cascade = CascadeType.REMOVE)
//    @Transient
//    private Company managedCompany;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name ="user_company",
            joinColumns = {@JoinColumn(name ="user_id", insertable = false, updatable = false, referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id", insertable = false, updatable = false, referencedColumnName = "id")})
    public Company getCompany(){return company; };

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if(o==null) return false;

        else if(o==this) return true;

        else if(o instanceof User && ((User)o).getId()==this.id) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }




}
