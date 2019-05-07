package edu.cs.ubb.dictionarylearn.model;

import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
public class User {

    @Id
    @Column(length = 100)
    private String email;

    @NotNull
    @Column(length = 100)
    private String password;

    @NotNull
    @Column(length = 100)
    private String address;

    @NotNull
    @Column(length = 100)
    private String town;

    @NotNull
    @Column(length = 100)
    private String state;

    private boolean admin;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private Set<Favorite> favorites = new HashSet<Favorite>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private Set<AllowTold> allowTolds = new HashSet<AllowTold>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getAdmin() {return admin;}

    public void setAdmin(Boolean admin) { this.admin = admin;}

    @JsonIgnore
    public Set<Favorite> getFavorite() {
        return favorites;
    }

    public void setFavirite(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    @JsonIgnore
    public Set<AllowTold> getAllowTold() {
        return allowTolds;
    }

    public void setAllowTold(Set<AllowTold> allowTolds) {
        this.allowTolds = allowTolds;
    }
}