package com.topolski.repositories;

import com.topolski.entities.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class AuthorRepository {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(AuthorRepository.class);
    private AuthorRepository() { }
    public static Author save(final Author author) {
        try (Session session = DbUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(author);
            LOGGER.debug("Author saved under id = {}", author.getId());
            transaction.commit();
            LOGGER.debug("Entry has been approved.");
            return author;
        }
    }
    public static List<Author> findAll() {
        try (Session session = DbUtil.getSession()) {
            return session.createQuery(
                    "SELECT a FROM Author a",
                    Author.class)
                    .getResultList();
        }
    }
    public static Author findByName(final String name) {
        try (Session session = DbUtil.getSession()) {
            List<Author> authorList = session
                    .createQuery(
                            "SELECT a FROM Author a WHERE a.authorName = :name",
                            Author.class)
                    .setParameter("name", name)
                    .getResultList();
            if (authorList.isEmpty()) {
                return null;
            } else {
                return authorList.get(0);
            }
        }
    }
}
