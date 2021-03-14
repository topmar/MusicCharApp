package com.topolski.file_strategy;

public class XMLFile extends File {
    public XMLFile(final String filePath) {
        super(filePath, new XMLReadWrite());
    }
}
