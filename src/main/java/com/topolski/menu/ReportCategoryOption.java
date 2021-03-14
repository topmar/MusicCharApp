package com.topolski.menu;

import com.topolski.entities.CategoryENUM;
import com.topolski.parse.ParseToCSV;
import com.topolski.parse.ParseToXML;
import com.topolski.repositories.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ReportCategoryOption extends Menu {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReportCategoryOption.class);
    private static final String REPORT_BY_CATEGORY = "Reports by category";
    private static final String DISPLAY_SONGS = "\t1. Display songs.";
    private static final String WRITE_SONG_TO_XML =
            "\t2. Write songs to XML file.";
    private static final String WRITE_SONG_TO_CSV =
            "\t3. Write songs to CSV file.";
    private static final String BACK_TEXT = "\t0. Back.";
    private String userChooseCategory;
    public void runMenuReportCategory() {
        LOGGER.info("Category song menu is displayed");
        showHeadMessage(REPORT_BY_CATEGORY);
        showMenuReportCategoryOption();
        String userInputLevelOne;
        while (true) {
            showYourChooseMessage();
            userInputLevelOne = getUserInput();
            if (validateUserChooseLevelOne(userInputLevelOne)) {
                if (checkIsExitOption(userInputLevelOne)) {
                    break;
                }
                runMenuSecondLevel(userInputLevelOne);
                showHeadMessage(REPORT_BY_CATEGORY);
                showMenuReportCategoryOption();
            } else {
                showInvalidChooseMessage();
            }
        }
    }
    private void runMenuSecondLevel(final String userInputLevelOne) {
        String userInputLevelTwo;
        boolean flag = true;
        while (flag) {
            showSecondLevelMenu(userInputLevelOne);
            showYourChooseMessage();
            userInputLevelTwo = getUserInput();
            if (validateUserChooseLevelTne(userInputLevelTwo)) {
                if (checkIsExitOption(userInputLevelTwo)) {
                    break;
                }
                executeUserChooseSecondOption(userInputLevelTwo);
                flag = false;
            } else {
                showInvalidChooseMessage();
            }
        }
    }
    private boolean validateUserChooseLevelTne(final String userInputLevelTwo) {
        Set<String> validOption = Set.of("0", "1", "2", "3");
        return validOption.contains(userInputLevelTwo);
    }
    private void executeUserChooseSecondOption(
            final String userInputChooseLevelTwo) {
        String filePath = userChooseCategory;
        switch (userInputChooseLevelTwo) {
            case "1":
                showSongsList(SongRepository
                        .findByCategory(userChooseCategory));
                LOGGER.info("Displayed song list");
                break;
            case "2":
                ParseToXML.writeToXML(OUTPUT + filePath + ".xml",
                        SongRepository.findByCategory(userChooseCategory));
                LOGGER.info("saved to xml file: {}", filePath);
                break;
            default:
                ParseToCSV.writeToCSV(OUTPUT + filePath + ".csv",
                        SongRepository.findByCategory(userChooseCategory));
                LOGGER.info("saved to csv file: {}", filePath);
        }
    }
    private void showSecondLevelMenu(final String userInputChoose) {
        int counter = 1;
        int userInput = Integer.parseInt(userInputChoose);
        for (CategoryENUM category : CategoryENUM.values()) {
            if (counter == userInput) {
                userChooseCategory = category.getText();
                showHeadMessage("Songs by category: "
                        + userChooseCategory);
                break;
            }
            counter++;
        }
        showOption();
    }
    private void showOption() {
        System.out.println(DISPLAY_SONGS);
        System.out.println(WRITE_SONG_TO_XML);
        System.out.println(WRITE_SONG_TO_CSV);
        System.out.println(BACK_TEXT);
    }
    private boolean validateUserChooseLevelOne(final String userInputChoose) {
        Set<String> validChoose = new HashSet<>();
        for (int i = 0; i <= CategoryENUM.values().length; i++) {
            validChoose.add(Integer.toString(i));
        }
        return validChoose.contains(userInputChoose);
    }
    private void showMenuReportCategoryOption() {
        System.out.println("Report by category menu:");
        int counter = 1;
        for (CategoryENUM categoryENUM: CategoryENUM.values()) {
            System.out.println("\t" + counter + ". " + categoryENUM.getText());
            counter++;
        }
        System.out.println(BACK_TEXT);
    }
}
