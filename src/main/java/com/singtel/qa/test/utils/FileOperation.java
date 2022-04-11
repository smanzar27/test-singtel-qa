package com.singtel.qa.test.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileOperation {

    public static Path getFilePath(String fileName){
        String filePath = System.getProperty("user.dir") + File.separator + fileName;
        return Paths.get(filePath);
    }

    public static void createFile(String fileName){
        try {
            Path path = getFilePath(fileName);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String fileName, String lineString){
        try {
            Path path = getFilePath(fileName);
            lineString = lineString +"\n";
            Files.write(path, lineString.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFile(String fileName){
        List<String> listStringLine = null;
        try {
            Path path = getFilePath(fileName);
            Stream<String> streamLines = Files.lines(path);
            listStringLine = streamLines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listStringLine;
    }

    public static void deleteFile(String fileName){
        try {
            Path path = getFilePath(fileName);
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
