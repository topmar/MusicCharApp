package com.topolski.file_strategy;

import com.topolski.dto.SongDTO;
import com.topolski.entities.Author;
import com.topolski.entities.Song;
import com.topolski.entities.SongBuilder;
import com.topolski.entities.Vote;
import com.topolski.repositories.AuthorRepository;
import com.topolski.repositories.SongRepository;
import com.topolski.repositories.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public final class CheckFiles {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(CheckFiles.class);
    private final Set<String> filePaths = new HashSet<>();
    private final Set<File> fileToReadSet = new HashSet<>();
    private static final String XML_EXTEND = ".xml";
    private static final String CSV_EXTEND = ".csv";
    private static final int MIN_LENGTH_OF_FILE_NAME = 4;
    public void runCheckFilesAndReadToDB(final String... pathArray) {
        checkPathList(pathArray);
        List<SongDTO> songList = getSongDTOS();
        readSongListToDB(songList);
    }
    private void readSongListToDB(final List<SongDTO> songList) {
        Author author;
        Song song;
        for (SongDTO songDTO : songList) {
            if (checkAllSongDTOElementExists(songDTO)
            && checkStringSongDTOElementNotBlank(songDTO)) {
                String name = songDTO.getTitle();
                song = SongRepository.findByName(name);
                if (song == null) {
                    name = songDTO.getAuthor();
                    author = AuthorRepository.findByName(name);
                    if (author == null) {
                        author = AuthorRepository.save(new Author(name));
                    }
                    SongRepository.save(
                            new SongBuilder()
                                    .setTitle(songDTO.getTitle())
                                    .setAlbum(songDTO.getAlbum())
                                    .setCategory(songDTO.getCategory())
                                    .setAuthor(author)
                                    .setVote(VoteRepository
                                            .save(new Vote(songDTO.getVotes())))
                                    .build());
                }
            }
        }
    }
    private boolean checkStringSongDTOElementNotBlank(final SongDTO songDTO) {
        return !songDTO.getAuthor().isBlank()
                && !songDTO.getTitle().isBlank()
                && !songDTO.getAlbum().isBlank()
                && !songDTO.getCategory().isBlank();
    }
    private boolean checkAllSongDTOElementExists(final SongDTO songDTO) {
        return  songDTO.getAuthor() != null
                && songDTO.getTitle() != null
                && songDTO.getAlbum() != null
                && songDTO.getCategory() != null
                && songDTO.getVotes() != null;
    }
    private List<SongDTO> getSongDTOS() {
        List<SongDTO> songList = new ArrayList<>();
        try {
            for (File file : fileToReadSet) {
                for (SongDTO song : file.read().getSongList()) {
                    if (songList.contains(song)) {
                        songList.get(songList.indexOf(song))
                                .setVotes(songList.get(songList.indexOf(song))
                                        .getVotes() + song.getVotes());
                    } else {
                        songList.add(song);
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println("blad exception");
        }
        return songList;
    }
    private void checkPathList(final String[] pathArray) {
        if (pathArray.length > 0) {
            removeDuplicatePath(pathArray);
            removeUnavailableFiles();
            checkFileExtensionAndCreateFileType();
        } else {
            LOGGER.info("No files loaded.");
            System.out.println("No files loaded.");
        }
    }
    private void checkFileExtensionAndCreateFileType() {
        for (String filePath : filePaths) {
            if (filePath.toLowerCase().endsWith(XML_EXTEND)) {
                fileToReadSet.add(new XMLFile(filePath));
            } else if (filePath.toLowerCase().endsWith(CSV_EXTEND)) {
                fileToReadSet.add(new CSVFile(filePath));
            } else {
                LOGGER.info("The extension of file \"{}\" is not supported",
                        filePath);
            }
        }
    }
    private void removeUnavailableFiles() {
        for (Iterator<String> iterator = filePaths.iterator();
             iterator.hasNext();) {
            String filePath = iterator.next();
            if (filePath.length() < MIN_LENGTH_OF_FILE_NAME
                    || !checkFileExists(filePath)) {
                iterator.remove();
                LOGGER.info("The file \"{}\" has been removed from the list",
                        filePath);
            }
        }
    }
    private void removeDuplicatePath(final String... pathArray) {
        filePaths.addAll(Arrays.asList(pathArray));
        if (pathArray.length > filePaths.size()) {
            LOGGER.info("Duplicate files will not be taken into account");
        }
    }
    private boolean checkFileExists(final String file) {
        return Files.isReadable(Paths.get(file));
    }
}
