package com.topolski.entities;

public class SongBuilder {
    private String title;
    private String album;
    private String category;
    private Author author;
    private Vote vote;

    public SongBuilder setTitle(final String title) {
        this.title = title;
        return this;
    }
    public SongBuilder setAlbum(final String album) {
        this.album = album;
        return this;
    }
    public SongBuilder setCategory(final String category) {
        this.category = category;
        return this;
    }
    public SongBuilder setAuthor(final Author author) {
        this.author = author;
        return this;
    }
    public SongBuilder setVote(final Vote vote) {
        this.vote = vote;
        return this;
    }
    public Song build() {
        return new Song(title, album, category, author, vote);
    }
}
