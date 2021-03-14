package com.topolski.file_strategy;

import com.topolski.dto.SongsDTO;

import java.io.File;

public interface FileReadWrite {
    SongsDTO readFile(String filePath);

    void writeFile(String filePath, SongsDTO songsDTO);
    default void createFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
