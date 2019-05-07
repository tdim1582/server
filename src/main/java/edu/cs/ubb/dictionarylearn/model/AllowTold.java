
package edu.cs.ubb.dictionarylearn.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
public class AllowTold {

    @Id
    @SequenceGenerator(name = "seq_gen")
    @GeneratedValue(generator = "seq_gen")
    private Long allowToldId;

    @NotNull
    @Column(length = 256)
    private String allowTold;

    @NotNull
    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "email")
    private User user;

    @NotNull
    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "wordId")
    private Word word;

    public long getAllowToldId() {
        return allowToldId;
    }

    public void setAllowToldId(long id) {
        this.allowToldId = id;
    }

    public String getAllowTold() {
        return allowTold;
    }

    public void setAllowTold(String told) {
        this.allowTold = told;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}