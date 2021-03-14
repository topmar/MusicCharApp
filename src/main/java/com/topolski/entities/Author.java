package com.topolski.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorName;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private final List<Song> songsList = new ArrayList<>();

    public Author() {
    }
    public Author(final String author) {
        this.authorName = author;
    }

    public Long getId() {
        return id;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(final String authorName) {
        this.authorName = authorName;
    }
    public List<Song> getSongsList() {
        return songsList;
    }
    public void addSong(final Song song) {
        this.songsList.add(song);
    }

    @Override
    public String toString() {
        return "author: " + authorName;
    }
}
