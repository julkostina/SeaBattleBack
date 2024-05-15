package com.example.seabattlebacklocal.source;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.nio.file.Files;
import java.io.*;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Serialization {
    private String fileName;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Serialization(){
        this.fileName = "C:\\Users\\User\\IdeaProjects\\SeaBattleBackLocal\\Game.json";
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

public void writeFile(Object data, String searchString) throws IOException {
    // Convert the data to a JSON string
    String json = objectMapper.writeValueAsString(data);

    // Append the JSON string to the file
    try (FileWriter writer = new FileWriter(fileName, true)) {
        writer.write(json);
    }

    // Read the file line by line and check if each line contains the searchString
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(searchString)) {
                System.out.println("Found the string: " + searchString);
                break;
            }
        }
    }
}

    public void writeFile(Dictionary<Character, Character> data) throws IOException {
        // Convert the data to a JSON string
        String json = objectMapper.writeValueAsString(data);

        // Append the JSON string to the file
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(json);
        }
}
    public Dictionary<Character,Character> readFile() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(content);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group()).append(" ");
        }
        String[] words = sb.toString().trim().split("\\s+");
        Dictionary<Character, Character> dictionary = new Hashtable<>();
        for(String word : words){
            for(int i=0; i< word.length()-1;i++){
                dictionary.put(word.charAt(i), word.charAt(i+1));
            }
        }
        return dictionary;
    }
}
