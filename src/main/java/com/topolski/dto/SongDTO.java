package com.topolski.dto;

import com.opencsv.bean.CsvBindByName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "song")
@XmlAccessorType(XmlAccessType.FIELD)
public class SongDTO {
    @CsvBindByName
    private String title;
    @CsvBindByName
    private String author;
    @CsvBindByName
    private String album;
    @CsvBindByName
    private String category;
    @CsvBindByName
    private Integer votes;

    public SongDTO() {
    }
    public SongDTO(final String title, final String author,
                   final String album, final String category,
                   final Integer votes) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.category = category;
        this.votes = votes;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(final String author) {
        this.author = author;
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
        this.category = category;
    }
    public Integer getVotes() {
        return votes;
    }
    public void setVotes(final Integer votes) {
        this.votes = votes;
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SongDTO songDTO = (SongDTO) o;
        if (!Objects.equals(title, songDTO.title)) {
            return false;
        }
        return Objects.equals(author, songDTO.author);
    }
    @Override
    public int hashCode() {
        final int hashNum = 31;
        int result = title != null ? title.hashCode() : 0;
        result = hashNum * result
                + (author != null ? author.hashCode() : 0);
        return result;
    }
}
