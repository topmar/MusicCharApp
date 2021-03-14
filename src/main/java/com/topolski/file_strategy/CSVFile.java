package com.topolski.file_strategy;

public class CSVFile extends File {
    public CSVFile(final String filePath) {
        super(filePath, new CSVReadWrite());
    }
}
