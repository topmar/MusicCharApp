package com.topolski.file_strategy;

import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.topolski.dto.SongDTO;
import com.topolski.dto.SongsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class CSVReadWrite implements FileReadWrite {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(CSVReadWrite.class);
    @Override
    public SongsDTO readFile(final String filePath) {
        List<SongDTO> songDTO = Collections.emptyList();
        SongsDTO songsDTO = new SongsDTO();
        try {
            songDTO = new CsvToBeanBuilder<SongDTO>(new FileReader(filePath))
                    .withType(SongDTO.class)
                    .build()
                    .parse();
            songsDTO.setSongList(songDTO);
        } catch (FileNotFoundException
                | IllegalStateException
                | NullPointerException e) {
            System.out.println(e.getMessage() + " in file: " + filePath);
            LOGGER.error("{} in file {}", e.getMessage(), filePath);
        } catch (RuntimeException e) {
            System.out.println("runtime exception");
        }
        return songsDTO;
    }
    @Override
    public void writeFile(final String filePath, final SongsDTO songsDTO) {
            try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
                createFolder("output");
                StatefulBeanToCsv<SongDTO> beanToCsv =
                        new StatefulBeanToCsvBuilder<SongDTO>(writer)
                        .withSeparator(ICSVWriter.DEFAULT_SEPARATOR)
                        .withQuotechar(ICSVWriter.NO_QUOTE_CHARACTER)
                        .build();
                beanToCsv.write(songsDTO.getSongList());
            } catch (IOException
                    | CsvDataTypeMismatchException
                    | CsvRequiredFieldEmptyException e) {
                LOGGER.error("error writing to CSV file {}", e.getMessage());
            }
        }
}
