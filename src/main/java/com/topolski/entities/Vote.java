package com.topolski.entities;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "votes")
    private Integer votes;
    @OneToOne(mappedBy = "vote")
    private Song song;

    public Vote() {
    }
    public Vote(final Integer vote) {
        this.votes = vote;
    }

    public Long getId() {
        return id;
    }
    public Integer getVotes() {
        return votes;
    }
    public void setVotes(final Integer votes) {
        this.votes = votes;
    }
    public Song getSong() {
        return song;
    }
    public void setSong(final Song song) {
        this.song = song;
    }

    @Override
    public String toString() {
        return "votes: " + votes;
    }
}
