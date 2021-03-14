package com.topolski.menu;

import com.topolski.entities.Author;
import com.topolski.entities.Song;
import com.topolski.entities.Vote;
import com.topolski.repositories.AuthorRepository;
import com.topolski.repositories.SongRepository;
import com.topolski.repositories.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class AddSongOption extends Menu {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(AddSongOption.class);
    private Author author = new Author();
    private final Song song = new Song();
    private final Vote vote = new Vote(0);
    public void runMenuAddSong() {
        LOGGER.info("Add song menu is displayed");
        showHeadMessage("Adding song");
        enterSongBandName();
        enterSongTitle();
        enterSongAlbum();
        enterSongCategory();
        showMenuAddSong();
        boolean flag = true;
        while (flag) {
            showYourChooseMessage();
            String userInputOption = getUserInput();
            if (validateOptionAddSong(userInputOption)) {
                if (checkIsExitOption(userInputOption)) {
                    break;
                }
                executeUserOptionAddSong();
                flag = false;
            } else {
                showInvalidChooseMessage();
            }
        }
    }
    private void executeUserOptionAddSong() {
        Author checkedAuthor = checkAuthorIfExists();
        if (checkedAuthor != null) {
            LOGGER.info("Author {} already exists.",
                    checkedAuthor.getAuthorName());
            author = checkedAuthor;
            if (checkSongIfExists()) {
                LOGGER.info("Song {} already exists.", song.getTitle());
                System.out.println("The song already exists!");
            } else {
                song.setAuthor(author);
                VoteRepository.save(vote);
                song.setVote(vote);
                SongRepository.save(song);
            }
        } else {
            AuthorRepository.save(author);
            song.setAuthor(author);
            VoteRepository.save(vote);
            song.setVote(vote);
            SongRepository.save(song);
        }
    }
    private boolean checkSongIfExists() {
        return author.getSongsList().contains(song);
    }
    private Author checkAuthorIfExists() {
        return AuthorRepository.findByName(author.getAuthorName());
    }
    private boolean validateOptionAddSong(final String userInputOption) {
        final Set<String> validOption = Set.of("0", "1");
        return validOption.contains(userInputOption);
    }
    private void showMenuAddSong() {
        final String text = "Adding song menu:";
        printLine(text.length());
        System.out.println(text);
        System.out.println("\t1. Add song.");
        System.out.println("\t0. Cancel.");
    }
    private void enterSongBandName() {
        while (true) {
            System.out.print("Enter the name of the band: ");
            String input = getUserInput();
            if (!input.isBlank()) {
                author.setAuthorName(input);
                break;
            } else {
                showInvalidInputMessage();
            }
        }
    }
    private void enterSongTitle() {
        while (true) {
            System.out.print("Enter the song title: ");
            String input = getUserInput();
            if (!input.isBlank()) {
                song.setTitle(input);
                break;
            } else {
                showInvalidInputMessage();
            }
        }
    }
    private void enterSongAlbum() {
        while (true) {
            System.out.print("Enter a song album: ");
            String input = getUserInput();
            if (!input.isBlank()) {
                song.setAlbum(input);
                break;
            } else {
                showInvalidInputMessage();
            }
        }
    }
    private void enterSongCategory() {
        while (true) {
            System.out.print("Enter the song categories: ");
            String input = getUserInput();
            if (!input.isBlank()) {
                song.setCategory(input);
                break;
            } else {
                showInvalidInputMessage();
            }
        }
    }
    private void showInvalidInputMessage() {
        System.out.println("Text entered is invalid. Try again...");
    }
}
