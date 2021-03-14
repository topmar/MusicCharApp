package com.topolski.repositories;

import com.topolski.entities.Vote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VoteRepositoryTest {
    @Test
    void saveVoteTest() {
        Vote vote = new Vote(56);
        VoteRepository.save(vote);
        Assertions.assertNotNull(vote.getId());
        Assertions.assertEquals(56, DbUtil.getSession()
                .createSQLQuery("SELECT votes FROM votes WHERE id =" + vote.getId())
                .getResultList().get(0));
    }
    @Test
    void resetAllVotesTest() {
        Vote vote1 = new Vote(23);
        Vote vote2 = new Vote(44);
        VoteRepository.save(vote1);
        VoteRepository.save(vote2);
        VoteRepository.resetAllVotes();
        Assertions.assertEquals("0", DbUtil.getSession()
                .createSQLQuery( "SELECT SUM(votes) FROM votes").getResultList().get(0).toString());
    }
    @Test
    void resetSelectedVotesTest() {
        Vote vote = new Vote(33);
        VoteRepository.save(vote);
        VoteRepository.resetSelectedVotes(vote.getId().toString());
        Assertions.assertEquals(0,
                DbUtil.getSession()
                        .createSQLQuery("SELECT votes FROM votes WHERE id ="
                                + vote.getId()).getResultList().get(0));
    }
    @Test
    void addVoteTest() {
        Vote vote = new Vote(10);
        VoteRepository.save(vote);
        VoteRepository.addVote(vote.getId().toString());
        Assertions.assertEquals(vote.getVotes() + 1,
                DbUtil.getSession()
                        .createSQLQuery("SELECT votes FROM votes WHERE id ="
                                + vote.getId()).getResultList().get(0));
    }
}