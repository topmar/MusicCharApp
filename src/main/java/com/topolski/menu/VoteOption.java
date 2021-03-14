package com.topolski.menu;

import com.topolski.entities.Song;
import com.topolski.repositories.SongRepository;
import com.topolski.repositories.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VoteOption extends Menu {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(VoteOption.class);
    private List<Song> songList;
    private boolean flag = true;
    public void runMenuVote() {
        LOGGER.info("Song voting menu is displayed");
        showHeadMessage("Vote for the song");
        songList = SongRepository.findAll();
        showSongsList(songList);
        while (flag) {
            showMenuVote();
            String userInput = getUserInput();
            if (validateOptionVote(userInput)) {
                if (checkIsExitOption(userInput)) {
                    break;
                }
                executeUserChoose(userInput);
                flag = false;
            } else {
                showInvalidChooseMessage();
            }
        }
    }
    private void showMenuVote() {
        String text = "Enter song number to vote or \"0\" to back: ";
        printLine(text.length());
        System.out.print(text);
    }
    private void executeUserChoose(final String userInput) {
        VoteRepository.addVote(userInput);
    }
    private boolean validateOptionVote(final String userInputOption) {
        Set<String> validOptions = new HashSet<>();
        validOptions.add("0");
        for (Song song : songList) {
            validOptions.add(Long.toString(song.getId()));
        }
        return validOptions.contains(userInputOption);
    }
}
