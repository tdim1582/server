package edu.cs.ubb.dictionarylearn.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Word {

    @Id
    @SequenceGenerator(name = "seq_gen")
    @GeneratedValue(generator = "seq_gen")
    private Long wordId;

    @NotNull
    @Column(length = 100)
    private String hungarian;

    @NotNull
    @Column(length = 200)
    private String english;

    @OneToMany(mappedBy="word", cascade=CascadeType.ALL)
    private Set<Favorite> favorites = new HashSet<Favorite>();

    @OneToMany(mappedBy="word", cascade=CascadeType.ALL)
    private Set<AllowTold> allowTolds = new HashSet<AllowTold>();

    @OneToMany(mappedBy="word", cascade=CascadeType.ALL)
    private Set<Told> tolds = new HashSet<Told>();

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long id) {
        this.wordId = id;
    }

    public String getHungarian() { return hungarian; }

    public void setHungarian(String hungarian) {
        this.hungarian = hungarian;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

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

    @JsonIgnore
    public Set<Told> getTold() {
        return tolds;
    }

    public void setTold(Set<Told> tolds) {
        this.tolds = tolds;
    }

}