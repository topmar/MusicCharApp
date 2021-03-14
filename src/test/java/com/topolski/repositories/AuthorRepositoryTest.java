package com.topolski.repositories;

import com.topolski.entities.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class AuthorRepositoryTest {
    @Test
    void authorSaveTest() {
        final String authorName = "Author save Test";
        Author author = new Author(authorName);
        AuthorRepository.save(author);
        Assertions.assertNotNull(author.getId());
        Assertions.assertEquals(author.getId(),
                Objects.requireNonNull(AuthorRepository
                        .findByName(authorName)).getId());
    }
    @Test
    void authorFindAllTest() {
        Author author1 = new Author("Author Test 1");
        Author author2 = new Author("Author Test 2");
        AuthorRepository.save(author1);
        AuthorRepository.save(author2);
        Assertions.assertTrue(AuthorRepository.findAll().size() >= 2);
        Assertions.assertSame(author1.getAuthorName(),
                Objects.requireNonNull(AuthorRepository
                        .findByName(author1.getAuthorName())).getAuthorName());
        Assertions.assertSame(author2.getAuthorName(),
                Objects.requireNonNull(AuthorRepository
                        .findByName(author2.getAuthorName())).getAuthorName());
    }
    @Test
    void authorFindByNameTest() {
        final String AUTHOR = "Author find by name Test";
        Author author = new Author(AUTHOR);
        AuthorRepository.save(author);
        author = AuthorRepository.findByName(AUTHOR);
        assert author != null;
        Assertions.assertEquals(AUTHOR, author.getAuthorName());
    }
}
