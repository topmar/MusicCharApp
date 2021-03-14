package com.topolski.repositories;

import com.topolski.entities.Author;
import com.topolski.entities.Song;
import com.topolski.entities.SongBuilder;
import com.topolski.entities.Vote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

class SongRepositoryTest {
    @BeforeAll
    static void addSongsToDB() {
        StringBuilder sb = new StringBuilder();
        Author author;
        Vote vote;
        Song song;
        for (int i = 10; i < 20; i++) {
            author = new Author(sb.append("Autor").append(i).toString());
            vote = new Vote(i);
            AuthorRepository.save(author);
            VoteRepository.save(vote);
            song = new SongBuilder()
                    .setTitle(sb.append("Title").append(i).toString())
                    .setCategory("Rock")
                    .setAuthor(author)
                    .setVote(vote)
                    .build();
            SongRepository.save(song);
        }
    }
    @Test
    void saveSongTest() {
        Author author = new Author("Author Save");
        AuthorRepository.save(author);
        Vote vote = new Vote(1);
        VoteRepository.save(vote);
        Song song = new SongBuilder()
                .setTitle("Song Save Test")
                .setCategory("Category")
                .setAuthor(author)
                .setVote(vote)
                .build();
        SongRepository.save(song);
        Assertions.assertNotNull(song.getId());
        Assertions.assertEquals(song.getId(),
                Objects.requireNonNull(SongRepository
                        .findByName(song.getTitle())).getId());
    }

    @Test
    void findAllTest() {
        Author author1 = new Author("Author");
        AuthorRepository.save(author1);
        Vote vote1 = new Vote(1);
        Vote vote2 = new Vote(2);
        VoteRepository.save(vote1);
        VoteRepository.save(vote2);
        Song song1 = new SongBuilder()
                .setTitle("Song Test 1")
                .setCategory("Category")
                .setAuthor(author1)
                .setVote(vote1)
                .build();
        Song song2 = new SongBuilder()
                .setTitle("Song Test 2")
                .setCategory("Category")
                .setAuthor(author1)
                .setVote(vote2)
                .build();
        SongRepository.save(song1);
        SongRepository.save(song2);
        Assertions.assertTrue(SongRepository.findAll().size() >= 2);
        Assertions.assertSame(song1.getTitle(), Objects.requireNonNull(SongRepository
                .findByName(song1.getTitle())).getTitle());
        Assertions.assertSame(song2.getTitle(), Objects.requireNonNull(SongRepository
                .findByName(song2.getTitle())).getTitle());
    }

    @Test
    void findByNameTest() {
        final String songName = "Song Title";
        Author author = new Author("Author");
        AuthorRepository.save(author);
        Vote vote = new Vote(2);
        VoteRepository.save(vote);
        Song song = new SongBuilder()
                .setTitle(songName)
                .setCategory("Category")
                .setAuthor(author)
                .setVote(vote)
                .build();
        SongRepository.save(song);
        Assertions.assertEquals(song.getId(),
                Objects.requireNonNull(SongRepository.findByName(songName)).getId());
        Assertions.assertEquals(songName,
                Objects.requireNonNull(SongRepository.findByName(songName)).getTitle());
    }

    @Test
    void findTopAllTest() {
        int votes = 100;
        boolean isDescending = true;
        for (Song song : SongRepository.findTopAll()) {
            if (song.getVote().getVotes() <= votes) {
                votes = song.getVote().getVotes();
                } else {
                isDescending = false;
                }
        }
        Assertions.assertTrue(isDescending);
    }

    @Test
    void findTop10Test() {
        int votes = 100;
        boolean isDescending = true;
        List<Song> songList = SongRepository.findTop10();
        for (Song song : songList) {
            if (song.getVote().getVotes() <= votes) {
                votes = song.getVote().getVotes();
            } else {
                isDescending = false;
            }
        }
        Assertions.assertTrue(isDescending);
        Assertions.assertTrue(songList.size() <= 10);
    }

    @Test
    void findTop3Test() {
        int votes = 100;
        boolean isDescending = true;
        List<Song> songList = SongRepository.findTop3();
        for (Song song : songList) {
            if (song.getVote().getVotes() <= votes) {
                votes = song.getVote().getVotes();
            } else {
                isDescending = false;
            }
        }
        Assertions.assertTrue(isDescending);
        Assertions.assertTrue(songList.size() <= 3);
    }

    @Test
    void findByCategoryTest() {
        Assertions.assertTrue(SongRepository.findByCategory("Rock").size() >= 10);
    }
}