package com.topolski.menu;

import com.topolski.entities.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Menu {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Menu.class);
    protected static final String OUTPUT = "./output/";
    private final Scanner scanner = new Scanner(System.in);
    public void runMenu() {
        while (true) {
            showHeadMessage("Welcome to the Charts App");
            showMenu();
            showYourChooseMessage();
            String userInputOption = getUserInput();
            if (validateOption(userInputOption)) {
                if (checkIsExitOption(userInputOption)) {
                    break;
                }
                executeUserOption(userInputOption);
            } else {
                showInvalidChooseMessage();
            }
        }
    }
    protected void showInvalidChooseMessage() {
        final String text = "Option is invalid. Try again...";
        printLine(text.length());
        System.out.println(text);
        System.out.println();
    }
    private void executeUserOption(final String userInputOption) {
        switch (userInputOption) {
            case "1":
                LOGGER.debug("First option selected");
                new AddSongOption().runMenuAddSong();
                break;
            case "2":
                LOGGER.debug("Second option selected");
                new VoteOption().runMenuVote();
                break;
            case "3":
                LOGGER.debug("Third option selected");
                new ResetOption().runMenuReset();
                break;
            case "4":
                LOGGER.debug("Fourth option selected");
                new ReportOption().runMenuReport();
                break;
            default:
                LOGGER.debug("Fifth option selected");
                new ReportCategoryOption().runMenuReportCategory();
        }
    }
    protected boolean checkIsExitOption(final String userInputOption) {
        return "0".equals(userInputOption);
    }
    private boolean validateOption(final String userInputOption) {
        Set<String> validOption = Set.of("0", "1", "2", "3", "4", "5");
        return validOption.contains(userInputOption);
    }
    protected String getUserInput() {
        String input = scanner.nextLine().trim();
        LOGGER.debug("Read option: {}", input);
        return input;
    }
    protected void showYourChooseMessage() {
        System.out.print("Your choice: ");
    }
    private void showMenu() {
        System.out.println("Main menu:");
        System.out.println("\t1. Add song.");
        System.out.println("\t2. Vote for the song.");
        System.out.println("\t3. Reset voices.");
        System.out.println("\t4. Report by votes.");
        System.out.println("\t5. Report by category.");
        System.out.println("\t0. Exit.");
    }
    protected void showHeadMessage(final String text) {
        int textLength = text.length();
        System.out.println();
        printLine(textLength);
        System.out.println(text);
        printLine(textLength);
    }
    protected void printLine(final int lineLength) {
        for (int i = 0; i < lineLength; i++) {
            System.out.print("=");
        }
        System.out.println();
    }
    protected void showSongsList(final List<Song> songList) {
        if (!songList.isEmpty()) {
            System.out.println("Song list:");
            printHeader();
            printSongs(songList);
        } else {
            System.out.println("No songs found");
        }
    }
    private void printSongs(final List<Song> songList) {
        for (Song song : songList) {
            System.out.printf(
                    "[%-5s][%-40s][%-30s][%-40s][%-20s][%-5s]%n",
                    song.getId(),
                    song.getTitle(),
                    song.getAuthor().getAuthorName(),
                    song.getAlbum(),
                    song.getCategory(),
                    song.getVote().getVotes());
        }
    }
    protected void printHeader() {
        System.out.printf(
                "[%-5s][%-40s][%-30s][%-40s][%-20s][%-5s]%n",
                "id", "Title", "Band", "Album", "Category", "Votes");
    }
}
