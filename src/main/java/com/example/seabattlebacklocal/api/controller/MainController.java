package com.example.seabattlebacklocal.api.controller;

import com.example.seabattlebacklocal.source.*;

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
    Serialization serialization = Serialization.getInstance();
    GameEngine game = new GameEngine();


    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(path = "/initGame", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = ORIGIN)
    public ResponseEntity<Object> initGame(@RequestBody Map<String, Object> datamap) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        GameEngine gameEngine = new GameEngine();
        gameEngine.initGame(datamap.get("firstPlayerName").toString(), (String) datamap.get("secondPlayerName"), Integer.parseInt(datamap.get("size").toString()), 0);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping(path = "/loadGame", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = ORIGIN)
    public ResponseEntity<Object> loadGame(@RequestBody Map<String, Object> datamap) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Game data = new Game();
        data.player1 = datamap.get("player1").toString();
        data.player2 = datamap.get("player2").toString();
        data.turn = Integer.parseInt(datamap.get("turn").toString());
        data.sizeOfBoard = Integer.parseInt(datamap.get("sizeOfBoard").toString());
        Map<String, Integer> ships1Input = (Map<String, Integer>) datamap.get("ships1");
        ShipTypes ships1 = new ShipTypes();
        ships1.oneDeck = Integer.parseInt(ships1Input.get("oneDeck").toString());
        ships1.twoDeck = Integer.parseInt(ships1Input.get("twoDeck").toString());
        ships1.threeDeck = Integer.parseInt(ships1Input.get("threeDeck").toString());
        ships1.fourDeck = Integer.parseInt(ships1Input.get("fourDeck").toString());
        data.ships1 = ships1;
        ShipTypes ships2 = new ShipTypes();
        Map<String, Integer> ships2Input = (Map<String, Integer>) datamap.get("ships2");
        ships2.oneDeck = Integer.parseInt(ships2Input.get("oneDeck").toString());
        ships2.twoDeck = Integer.parseInt(ships2Input.get("twoDeck").toString());
        ships2.threeDeck = Integer.parseInt(ships2Input.get("threeDeck").toString());
        ships2.fourDeck = Integer.parseInt(ships2Input.get("fourDeck").toString());
        data.ships2 = ships2;
        PlacedShips placedShips1 = new PlacedShips();
        Map<String, List<String>> placedShips1Input = (Map<String, List<String>>) datamap.get("placedShips1");
        placedShips1.oneDeck = placedShips1Input.get("oneDeck");
        placedShips1.twoDeck = placedShips1Input.get("twoDeck");
        placedShips1.threeDeck = placedShips1Input.get("threeDeck");
        placedShips1.fourDeck = placedShips1Input.get("fourDeck");
        data.placedShips1 = placedShips1;
        PlacedShips placedShips2 = new PlacedShips();
        Map<String, List<String>> placedShips2Input = (Map<String, List<String>>) datamap.get("placedShips2");
        placedShips2.oneDeck = placedShips2Input.get("oneDeck");
        placedShips2.twoDeck = placedShips2Input.get("twoDeck");
        placedShips2.threeDeck = placedShips2Input.get("threeDeck");
        placedShips2.fourDeck = placedShips2Input.get("fourDeck");
        data.placedShips2 = placedShips2;
        data.placed1 = Boolean.parseBoolean(datamap.get("placed1").toString());
        data.placed2 = Boolean.parseBoolean(datamap.get("placed2").toString());
        data.hit1 = Integer.parseInt(datamap.get("hit1").toString());
        data.hit2 = Integer.parseInt(datamap.get("hit2").toString());
        data.miss1 = Integer.parseInt(datamap.get("miss1").toString());
        data.miss2 = Integer.parseInt(datamap.get("miss2").toString());

        game = new GameEngine(data);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping(path = "/saveGame", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = ORIGIN)
    public ResponseEntity<Object> saveGame() {
        return new ResponseEntity<Object>(game.getGame(), HttpStatus.OK);
    }

    @PostMapping(path = "/placeShips", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = ORIGIN)
    public ResponseEntity<Object> placeShips(@RequestBody Map<String, Object> datamap) {
        // {placement:custom|random, player: 1|2, shipCoordinates: [[0,0],[0,0]]|null}

        Player.Placement placement = Player.Placement.valueOf(datamap.get("placement").toString());

        // Player player = new Player(game.getGame().player1);

        if (placement.equals(Player.Placement.CUSTOM)) {
           // TODO
        } else {
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
