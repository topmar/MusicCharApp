package com.topolski.menu;

import com.topolski.entities.Song;
import com.topolski.repositories.SongRepository;
import com.topolski.repositories.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResetOption extends Menu {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ResetOption.class);
    private List<Song> songList;
    public void runMenuReset() {
        LOGGER.info("Vote reset menu is displayed");
        boolean flag = true;
        showHeadMessage("Reset vote");
        showMenuResetOption();
        while (flag) {
            showYourChooseMessage();
            String userInput = getUserInput();
            if (validateResetChoose(userInput)) {
                if (checkIsExitOption(userInput)) {
                    break;
                }
                executeUserChooseReset(userInput);
                flag = false;
            } else {
                showInvalidChooseMessage();
            }
        }
    }
    private void executeUserChooseReset(final String userInput) {
        if ("1".equals(userInput)) {
            VoteRepository.resetAllVotes();
        } else {
            songList = SongRepository.findAll();
            showSongsList(songList);
            boolean flag = true;
            while (flag) {
                String text = "Enter song number to reset or \"0\" to back: ";
                printLine(text.length());
                System.out.print(text);
                String userResetVote = getUserInput();
                if (validateResetVote(userResetVote)) {
                    if (checkIsExitOption(userResetVote)) {
                        break;
                    }
                    VoteRepository.resetSelectedVotes(userResetVote);
                    flag = false;
                } else {
                    showInvalidChooseMessage();
                }
            }
        }
    }
    private boolean validateResetVote(final String userResetInput) {
        Set<String> validOptions = new HashSet<>();
        validOptions.add("0");
        for (Song song : songList) {
            validOptions.add(Long.toString(song.getId()));
        }
        return validOptions.contains(userResetInput);
    }
    private boolean validateResetChoose(final String userInputChoose) {
        Set<String> validChoose = Set.of("0", "1", "2");
        return validChoose.contains(userInputChoose);
    }
    private void showMenuResetOption() {
        System.out.println("Reset menu:");
        System.out.println("\t1. Reset all votes.");
        System.out.println("\t2. Reset selected song.");
        System.out.println("\t0. Back.");
    }
}
