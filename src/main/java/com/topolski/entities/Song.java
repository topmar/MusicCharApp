package com.topolski.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String album;
    private String category;
    @ManyToOne
    private Author author;
    @OneToOne
    private Vote vote;

    public Song() {
    }
    public Song(final String title, final String album,
                final String category, final Author author, final Vote vote) {
        this.title = title;
        this.album = album;
        setCategory(category);
        this.author = author;
        this.vote = vote;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(final String album) {
        this.album = album;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(final String category) {
        for (CategoryENUM categoryENUM: CategoryENUM.values()) {
            if (categoryENUM.getText().equalsIgnoreCase(category)) {
                this.category = categoryENUM.getText();
                break;
            }
            this.category = categoryENUM.getText();
        }
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(final Author author) {
        this.author = author;
    }
    public Vote getVote() {
        return vote;
    }
    public void setVote(final Vote vote) {
        this.vote = vote;
    }
    @Override
    public String toString() {
        return "id: " + id
                + ", title: " + title
                + ", album: " + album
                + ", category: " + category
                + ", author: " + author.getAuthorName()
                + ", vote: " + vote.getVotes();
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Song song = (Song) o;
        return Objects.equals(title, song.title);
    }
    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}
