package edu.cs.ubb.dictionarylearn.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
public class Favorite {

    @Id
    @SequenceGenerator(name = "seq_gen")
    @GeneratedValue(generator = "seq_gen")
    private Long favoriteId;

    @NotNull
    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "email")
    private User user;

    @NotNull
    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "wordId")
    private Word word;

    public long getfavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(long id) {
        this.favoriteId = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

}