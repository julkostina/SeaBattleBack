package com.example.seabattlebacklocal.source;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;


import java.io.*;
import java.util.Dictionary;


public class Serialization {
    private static Serialization instance;
    private String fileName;

    public static Serialization getInstance() {
        if (instance == null) {
            instance = new Serialization();
        }
        return instance;
    }

    private Serialization() {
        this.fileName = "Game.json";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeFile(Game data) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Game readFile() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("Game.json")) {
            Game game = gson.fromJson(reader, Game.class);
            return game;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
