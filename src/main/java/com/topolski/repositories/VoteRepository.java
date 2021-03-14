package com.topolski.repositories;

import com.topolski.entities.Vote;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VoteRepository {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(VoteRepository.class);
    private VoteRepository() { }
    public static Vote save(final Vote votes) {
        try (Session session = DbUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(votes);
            LOGGER.debug("Vote saved under id = {}", votes.getId());
            transaction.commit();
            LOGGER.debug("Entry has been approved.");
            return votes;
        }
    }
    public static void resetAllVotes() {
        try (Session session = DbUtil.getSession()) {
            session.beginTransaction();
            session.createSQLQuery(
                    "UPDATE votes SET votes = 0;")
                    .executeUpdate();
        }
    }
    public static void resetSelectedVotes(final String idSong) {
        try (Session session = DbUtil.getSession()) {
            session.beginTransaction();
            session.createSQLQuery(
                    "UPDATE votes SET votes = 0 WHERE id = "
                            + idSong + ";")
                    .executeUpdate();
        }
    }
    public static void addVote(final String idSong) {
        int votes = 1;
        try (Session session = DbUtil.getSession()) {
            session.beginTransaction();
            votes += (int) session.createSQLQuery(
                    "SELECT votes FROM votes WHERE id = "
                            + idSong + ";")
                    .getResultList().get(0);
            session.createSQLQuery(
                    "UPDATE votes SET votes = "
                            + votes
                            + " WHERE id = " + idSong + ";")
                    .executeUpdate();
        }
    }
}
