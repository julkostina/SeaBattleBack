package com.example.seabattlebacklocal.source;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;


import java.io.*;
import java.util.Dictionary;


public class Serialization  {
    private String fileName;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Serialization(){
        this.fileName = "Game.json";
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

public void writeFile(Dictionary<String, String> data) {
    Gson gson = new Gson();
    try (FileWriter writer = new FileWriter(fileName)) {
        gson.toJson(data, writer);
    } catch (IOException e) {
        throw  new RuntimeException(e);
    }
}

    public Game readFile() {
        Gson gson = new Gson();
    try (FileReader reader = new FileReader("Game.json")) {
        Game game = gson.fromJson(reader, Game.class);
        return game;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
    }
}
