package com.topolski.repositories;

import com.topolski.entities.Song;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class SongRepository {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(SongRepository.class);
    public static final String QUERY =
            "SELECT s FROM Song s LEFT JOIN FETCH"
                    + " s.vote v ORDER BY v.votes DESC";
    private SongRepository() { }
    public static Song save(final Song song) {
        try (Session session = DbUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(song);
            LOGGER.debug("Song saved under id = {}", song.getId());
            transaction.commit();
            LOGGER.debug("Entry has been approved.");
            return song;
        }
    }
    public static List<Song> findAll() {
        try (Session session = DbUtil.getSession()) {
            return session.createQuery(
                    "SELECT s FROM Song s",
                    Song.class)
                    .getResultList();
        }
    }
    public static Song findByName(final String name) {
        try (Session session = DbUtil.getSession()) {
            List<Song> songList = session
                    .createQuery(
                            "SELECT s FROM Song s WHERE s.title = :name",
                            Song.class)
                    .setParameter("name", name)
                    .getResultList();
            if (songList.isEmpty()) {
                return null;
            } else {
                return songList.get(0);
            }
        }
    }
    public static List<Song> findTopAll() {
        try (Session session = DbUtil.getSession()) {
            return session.createQuery(
                    QUERY,
                    Song.class)
                    .getResultList();
        }
    }
    public static List<Song> findTop10() {
        try (Session session = DbUtil.getSession()) {
            return session.createQuery(
                    QUERY,
                    Song.class).setMaxResults(10)
                    .getResultList();
        }
    }
    public static List<Song> findTop3() {
        try (Session session = DbUtil.getSession()) {
            return session.createQuery(
                    QUERY,
                    Song.class).setMaxResults(3)
                    .getResultList();
        }
    }
    public static List<Song> findByCategory(final String category) {
        try (Session session = DbUtil.getSession()) {
            return session.createQuery(
                    "SELECT s FROM Song s WHERE s.category = :category",
                    Song.class)
                    .setParameter("category", category)
                    .getResultList();
        }
    }
}
