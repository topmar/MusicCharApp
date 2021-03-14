package com.topolski.menu;

import com.topolski.entities.Song;
import com.topolski.parse.ParseToCSV;
import com.topolski.parse.ParseToXML;
import com.topolski.repositories.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class ReportOption extends Menu {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReportOption.class);
    private static final String REPORT_BY_VOTES = "Reports by votes";
    private static final String BACK_TEXT = "\t0. Back.";
    private static final String DISPLAY_SONGS = "\t1. Display songs.";
    private static final String WRITE_SONG_TO_XML =
            "\t2. Write songs to XML file.";
    private static final String WRITE_SONG_TO_CSV =
            "\t3. Write songs to CSV file.";
    public void runMenuReport() {
        LOGGER.info("Top song menu is displayed");
        showHeadMessage(REPORT_BY_VOTES);
        showMenuReportOption();
        String userInputChooseLevelOne;
        while (true) {
            showYourChooseMessage();
            userInputChooseLevelOne = getUserInput();
            if (validateOptionReport(userInputChooseLevelOne)) {
                if (checkIsExitOption(userInputChooseLevelOne)) {
                    break;
                }
                runMenuSecondLevel(userInputChooseLevelOne);
                showHeadMessage(REPORT_BY_VOTES);
                showMenuReportOption();
            } else {
                showInvalidChooseMessage();
            }
        }
    }
    private void runMenuSecondLevel(final String userInputLevelOne) {
        String userInputChooseLevelTwo;
        boolean flag = true;
        while (flag) {
            showSecondLevelMenu(userInputLevelOne);
            showYourChooseMessage();
            userInputChooseLevelTwo = getUserInput();
            if (validateOptionReport(userInputChooseLevelTwo)) {
                if (checkIsExitOption(userInputChooseLevelTwo)) {
                    break;
                }
                executeUserChooseSecondOption(userInputLevelOne,
                        userInputChooseLevelTwo);
                flag = false;
            } else {
                showInvalidChooseMessage();
            }
        }
    }
    private void executeUserChooseSecondOption(final String userInputLevelOne,
                                        final String userInputChooseLevelTwo) {
        switch (userInputChooseLevelTwo) {
            case "1":
                displaySongs(userInputLevelOne);
                break;
            case "2":
                writeSongsToXML(userInputLevelOne);
                break;
            default:
                writeSongToCSV(userInputLevelOne);
        }
    }
    private void writeSongToCSV(final String userInputLevelOne) {
        switch (userInputLevelOne) {
            case "1":
                ParseToCSV.writeToCSV(OUTPUT + "AllSongs.csv",
                        SongRepository.findTopAll());
                LOGGER.info("Write all songs to csv file");
                break;
            case "2":
                ParseToCSV.writeToCSV(OUTPUT + "Top10songs.csv",
                        SongRepository.findTop10());
                LOGGER.info("Write Top10 songs to csv file");
                break;
            default:
                ParseToCSV.writeToCSV(OUTPUT + "Top3songs.csv",
                        SongRepository.findTop3());
                LOGGER.info("Write Top3 songs to csv file");
        }
    }
    private void writeSongsToXML(final String userInputLevelOne) {
        switch (userInputLevelOne) {
            case "1":
                ParseToXML.writeToXML(OUTPUT + "AllSongs.xml",
                        SongRepository.findTopAll());
                LOGGER.info("Write all songs to mxl file");
                break;
            case "2":
                ParseToXML.writeToXML(OUTPUT + "Top10songs.xml",
                        SongRepository.findTop10());
                LOGGER.info("Write Top10 songs to xml file");
                break;
            default:
                ParseToXML.writeToXML(OUTPUT + "Top3songs.xml",
                        SongRepository.findTop3());
                LOGGER.info("Write Top3 songs to xml file");
        }
    }
    private void displaySongs(final String userInputLevelOne) {
        switch (userInputLevelOne) {
            case "1":
                showSongsList(getAllTopSongs());
                break;
            case "2":
                showSongsList(getTop10Song());
                break;
            default:
                showSongsList(getTop3Song());
        }
    }
    private List<Song> getTop3Song() {
        return SongRepository.findTop3();
    }
    private List<Song> getTop10Song() {
        return SongRepository.findTop10();
    }
    private List<Song> getAllTopSongs() {
        return SongRepository.findTopAll();
    }
    private void showSecondLevelMenu(final String userInputChoose) {
        switch (userInputChoose) {
            case "1":
                showHeadMessage("Report \"All songs\" menu:");
                showOption();
                break;
            case "2":
                showHeadMessage("Report \"Top10\" menu:");
                showOption();
                break;
            default:
                showHeadMessage("Report \"Top3\" menu:");
                showOption();
        }
    }
    private void showOption() {
        System.out.println(DISPLAY_SONGS);
        System.out.println(WRITE_SONG_TO_XML);
        System.out.println(WRITE_SONG_TO_CSV);
        System.out.println(BACK_TEXT);
    }
    private boolean validateOptionReport(final String userInputChoose) {
        Set<String> validChoose = Set.of("0", "1", "2", "3");
        return validChoose.contains(userInputChoose);
    }
    private void showMenuReportOption() {
        System.out.println("Report menu:");
        System.out.println("\t1. All songs.");
        System.out.println("\t2. Top10 songs.");
        System.out.println("\t3. Top3 songs.");
        System.out.println(BACK_TEXT);
    }
}
