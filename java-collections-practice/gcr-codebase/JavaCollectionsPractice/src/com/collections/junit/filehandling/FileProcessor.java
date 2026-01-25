package com.collections.junit.filehandling;

import java.io.*;

public class FileProcessor {

    public void writeToFile(String file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }

    public String readFromFile(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine();
    }
}