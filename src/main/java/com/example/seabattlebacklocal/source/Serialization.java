package com.example.seabattlebacklocal.source;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

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


public void writeFile(Dictionary<String, String> data) {
    Gson gson = new Gson();
    try (FileWriter writer = new FileWriter(fileName)) {
        gson.toJson(data, writer);
    } catch (IOException e) {
        throw  new RuntimeException(e);
    }
}

    public Dictionary<String, String> readFile() {
        String content="";
        try{
           content = new String(Files.readAllBytes(Paths.get(fileName)));
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(content);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group()).append(" ");
        }
        String[] words = sb.toString().trim().split("\\s+");
        Dictionary<String, String> dictionary = new Hashtable<>();
            for(int i=0; i< words.length;i+=2){
                dictionary.put(words[i], words[i+1]);
            }
        return dictionary;
    }
}
