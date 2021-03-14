package com.topolski.file_strategy;

import com.topolski.dto.SongsDTO;

public abstract class File {
    private final String filePath;
    private final FileReadWrite fileReadWrite;

    protected File(final String filePath, final FileReadWrite fileReadWrite) {
        this.filePath = filePath;
        this.fileReadWrite = fileReadWrite;
    }
    SongsDTO read() {
        return fileReadWrite.readFile(filePath);
    }
    void write() {
        SongsDTO songsDTO = null;
        fileReadWrite.writeFile(filePath, songsDTO);
    }
}
