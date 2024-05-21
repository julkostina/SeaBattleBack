package com.example.seabattlebacklocal.api.controller;
import com.example.seabattlebacklocal.source.GameEngine;
import com.example.seabattlebacklocal.source.Serialization;
import com.example.seabattlebacklocal.source.FileService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MainController {
    private final String ORIGIN = "http://localhost:3000";
    private final FileService fileService = new FileService();
    Serialization serialization =  Serialization.getInstance();
    GameEngine game = new GameEngine();    


     @GetMapping("/")
     public String index() {
         return "Greetings from Spring Boot!";
     }

    @PostMapping(path = "/initGame", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = ORIGIN)
    public ResponseEntity<Object> addProduct(@RequestBody Map<String, Object> datamap) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        GameEngine gameEngine = new GameEngine();
        gameEngine.initGame(datamap.get("firstPlayerName").toString(), (String) datamap.get("secondPlayerName"), Integer.parseInt(datamap.get("size").toString()), 0);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
