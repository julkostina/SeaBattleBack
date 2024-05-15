package com.example.seabattlebacklocal.api.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class MainController {


//    @GetMapping("/")
//    public String index() {
//        return "Greetings from Spring Boot!";
//    }
//
//
//    @GetMapping(path = "/play", produces = MediaType.APPLICATION_JSON_VALUE)
////    @CrossOrigin(origins = "http://localhost:3000/play")
//    public ResponseEntity<Object> getPlaySettings() {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return new ResponseEntity<Object>(gson.toJson(GameBoard.data), HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/actual-play", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> actualPlay(@RequestBody Map<String, Object> datamap) {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return new ResponseEntity<Object>(gson.toJson(GameBoard.data), HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/results", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> getResults() {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return new ResponseEntity<Object>(gson.toJson(GameBoard.data), HttpStatus.OK);
//    }
}
