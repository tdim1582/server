package edu.cs.ubb.dictionarylearn.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
public class Told {

    @Id
    @SequenceGenerator(name = "seq_gen")
    @GeneratedValue(generator = "seq_gen")
    private long toldId;

    @NotNull
    @Column(length = 256)
    private String told;

    @NotNull
    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "wordId")
    private Word word;

    public long getToldId() {
        return toldId;
    }

    public void setToldId(long id) {
        this.toldId = id;
    }

    public String getTold() {
        return told;
    }

    public void setTold(String told) {
        this.told = told;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }


}
