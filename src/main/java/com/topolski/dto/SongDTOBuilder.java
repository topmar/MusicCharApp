package com.topolski.dto;

public class SongDTOBuilder {
    private String title;
    private String author;
    private String album;
    private String category;
    private Integer votes;

    public SongDTOBuilder setTitle(final String title) {
        this.title = title;
        return this;
    }
    public SongDTOBuilder setAuthor(final String author) {
        this.author = author;
        return this;
    }
    public SongDTOBuilder setAlbum(final String album) {
        this.album = album;
        return this;
    }
    public SongDTOBuilder setCategory(final String category) {
        this.category = category;
        return this;
    }
    public SongDTOBuilder setVotes(final Integer votes) {
        this.votes = votes;
        return this;
    }
    public SongDTO build() {
        return new SongDTO(title, author, album, category, votes);
    }
}
