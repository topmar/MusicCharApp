package com.topolski;

import com.topolski.file_strategy.CheckFiles;
import com.topolski.menu.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class MusicCharApp {
/**
*
*/
    private static final Logger LOGGER =
            LoggerFactory.getLogger(MusicCharApp.class);
/**
* @param args - parameter accepting an array of files to be loaded by program.
*/
    public static void main(final String[] args) {
        new MusicCharApp().run(args);
    }
    private void run(final String... args) {
        LOGGER.info("Launching the application...");
        new CheckFiles().runCheckFilesAndReadToDB(args);
        new Menu().runMenu();
        LOGGER.info("Exiting the application... Good Bye :)");
    }
}
