package com.example.seabattlebacklocal.api.controller;

import com.example.seabattlebacklocal.source.*;

import com.example.seabattlebacklocal.source.FactoryPattern.FourDeckFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.OneDeckFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.ThreeDeckFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.TwoDeckFactory;
import com.example.seabattlebacklocal.source.Ships.Ship;
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
        game = new GameEngine();
        game.initGame(datamap.get("player1").toString(), (String) datamap.get("player2"), Integer.parseInt(datamap.get("size").toString()), 0);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping(path = "/loadGame", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = ORIGIN)
    public ResponseEntity<Object> loadGame(@RequestBody Map<String, Object> datamap) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Game data = new Game();
        Map<String, Object> gameMap = (Map<String, Object>) datamap.get("game");
        data.player1 = gameMap.get("player1").toString();
        data.player2 = gameMap.get("player2").toString();
        data.turn = Integer.parseInt(gameMap.get("turn").toString());
        data.sizeOfBoard = Integer.parseInt(gameMap.get("sizeOfBoard").toString());
        Map<String, Integer> ships1Input = (Map<String, Integer>) gameMap.get("ships1");
        ShipTypes ships1 = new ShipTypes();
        ships1.oneDeck = Integer.parseInt(ships1Input.get("oneDeck").toString());
        ships1.twoDeck = Integer.parseInt(ships1Input.get("twoDeck").toString());
        ships1.threeDeck = Integer.parseInt(ships1Input.get("threeDeck").toString());
        ships1.fourDeck = Integer.parseInt(ships1Input.get("fourDeck").toString());
        data.ships1 = ships1;
        ShipTypes ships2 = new ShipTypes();
        Map<String, Integer> ships2Input = (Map<String, Integer>) gameMap.get("ships2");
        ships2.oneDeck = Integer.parseInt(ships2Input.get("oneDeck").toString());
        ships2.twoDeck = Integer.parseInt(ships2Input.get("twoDeck").toString());
        ships2.threeDeck = Integer.parseInt(ships2Input.get("threeDeck").toString());
        ships2.fourDeck = Integer.parseInt(ships2Input.get("fourDeck").toString());
        data.ships2 = ships2;
        PlacedShips placedShips1 = new PlacedShips();
        Map<String, List<String>> placedShips1Input = (Map<String, List<String>>) gameMap.get("placedShips1");
        placedShips1.oneDeck = placedShips1Input.get("oneDeck");
        placedShips1.twoDeck = placedShips1Input.get("twoDeck");
        placedShips1.threeDeck = placedShips1Input.get("threeDeck");
        placedShips1.fourDeck = placedShips1Input.get("fourDeck");
        data.placedShips1 = placedShips1;
        PlacedShips placedShips2 = new PlacedShips();
        Map<String, List<String>> placedShips2Input = (Map<String, List<String>>) gameMap.get("placedShips2");
        placedShips2.oneDeck = placedShips2Input.get("oneDeck");
        placedShips2.twoDeck = placedShips2Input.get("twoDeck");
        placedShips2.threeDeck = placedShips2Input.get("threeDeck");
        placedShips2.fourDeck = placedShips2Input.get("fourDeck");
        data.placedShips2 = placedShips2;
        data.placed1 = Boolean.parseBoolean(gameMap.get("placed1").toString());
        data.placed2 = Boolean.parseBoolean(gameMap.get("placed2").toString());
        data.hit1 = Integer.parseInt(gameMap.get("hit1").toString());
        data.hit2 = Integer.parseInt(gameMap.get("hit2").toString());
        data.miss1 = Integer.parseInt(gameMap.get("miss1").toString());
        data.miss2 = Integer.parseInt(gameMap.get("miss2").toString());

        game = new GameEngine(data);

        game.initGame(data.player1, data.player2, data.sizeOfBoard, 0);

        LinkedHashMap<String, Object> player1BoardInput = (LinkedHashMap<String, Object>) datamap.get("player1_board");

        List<LinkedHashMap<String, Object>> player1BoardShipsInput = (List<LinkedHashMap<String, Object>>) player1BoardInput.get("ships");
        List<Ship> player1BoardShips = new ArrayList<>();
        for (LinkedHashMap<String, Object> shipInput : player1BoardShipsInput) {
            List<Coordinate> coordinates = new ArrayList<>();
            for (LinkedHashMap<String, Object> coordinateInput : (List<LinkedHashMap<String, Object>>) shipInput.get("coordinates")) {
                coordinates.add(new Coordinate(
                        Integer.parseInt(coordinateInput.get("row").toString()),
                        Integer.parseInt(coordinateInput.get("column").toString()),
                        game.getGame().sizeOfBoard
                ));
            }

            switch (Integer.parseInt(shipInput.get("size").toString())) {
                case 1:
                    Ship oneDeck = new OneDeckFactory().createShip(coordinates);
                    player1BoardShips.add(oneDeck);
                    break;
                case 2:
                    Ship twoDeck = new TwoDeckFactory().createShip(coordinates);
                    player1BoardShips.add(twoDeck);
                    break;
                case 3:
                    Ship threeDeck = new ThreeDeckFactory().createShip(coordinates);
                    player1BoardShips.add(threeDeck);
                    break;
                case 4:
                    Ship fourDeck = new FourDeckFactory().createShip(coordinates);
                    player1BoardShips.add(fourDeck);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid ship type");
            }
        }

        ArrayList<ArrayList<Integer>> player1BoardBoardInput = (ArrayList<ArrayList<Integer>>) player1BoardInput.get("board");
        int[][] player1BoardBoard = new int[player1BoardBoardInput.size()][player1BoardBoardInput.get(0).size()];
        for (int i = 0; i < player1BoardBoardInput.size(); i++) {
            for (int j = 0; j < player1BoardBoardInput.get(i).size(); j++) {
                player1BoardBoard[i][j] = player1BoardBoardInput.get(i).get(j);
            }
        }

        game.getPlayers().get("player1").setGameBoard(new GameBoard(
                Integer.parseInt(player1BoardInput.get("size").toString()),
                new Hashtable<>(),
                new Hashtable<>(),
                player1BoardShips,
                player1BoardBoard
        ));



        LinkedHashMap<String, Object> player2BoardInput = (LinkedHashMap<String, Object>) datamap.get("player2_board");

        List<LinkedHashMap<String, Object>> player2BoardShipsInput = (List<LinkedHashMap<String, Object>>) player2BoardInput.get("ships");
        List<Ship> player2BoardShips = new ArrayList<>();
        for (LinkedHashMap<String, Object> shipInput : player2BoardShipsInput) {
            List<Coordinate> coordinates = new ArrayList<>();
            for (LinkedHashMap<String, Object> coordinateInput : (List<LinkedHashMap<String, Object>>) shipInput.get("coordinates")) {
                coordinates.add(new Coordinate(
                        Integer.parseInt(coordinateInput.get("row").toString()),
                        Integer.parseInt(coordinateInput.get("column").toString()),
                        game.getGame().sizeOfBoard
                ));
            }

            switch (Integer.parseInt(shipInput.get("size").toString())) {
                case 1:
                    Ship oneDeck = new OneDeckFactory().createShip(coordinates);
                    player2BoardShips.add(oneDeck);
                    break;
                case 2:
                    Ship twoDeck = new TwoDeckFactory().createShip(coordinates);
                    player2BoardShips.add(twoDeck);
                    break;
                case 3:
                    Ship threeDeck = new ThreeDeckFactory().createShip(coordinates);
                    player2BoardShips.add(threeDeck);
                    break;
                case 4:
                    Ship fourDeck = new FourDeckFactory().createShip(coordinates);
                    player2BoardShips.add(fourDeck);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid ship type");
            }
        }

        ArrayList<ArrayList<Integer>> player2BoardBoardInput = (ArrayList<ArrayList<Integer>>) player2BoardInput.get("board");
        int[][] player2BoardBoard = new int[player2BoardBoardInput.size()][player2BoardBoardInput.get(0).size()];
        for (int i = 0; i < player2BoardBoardInput.size(); i++) {
            for (int j = 0; j < player2BoardBoardInput.get(i).size(); j++) {
                player2BoardBoard[i][j] = player2BoardBoardInput.get(i).get(j);
            }
        }

        game.getPlayers().get("player2").setGameBoard(new GameBoard(
                Integer.parseInt(player2BoardInput.get("size").toString()),
                new Hashtable<>(),
                new Hashtable<>(),
                player2BoardShips,
                player2BoardBoard
        ));

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping(path = "/saveGame", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = ORIGIN)
    public ResponseEntity<Object> saveGame() {
        HashMap<String, Object> map = new HashMap();
        map.put("game", game.getGame());
        map.put("player1_board", game.getPlayers().get("player1").getGameBoard());
        map.put("player2_board", game.getPlayers().get("player2").getGameBoard());

        return new ResponseEntity<Object>(new Gson().toJson(map), HttpStatus.OK);
    }

    @PostMapping(path = "/placeShips", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = ORIGIN)
    public ResponseEntity<Object> placeShips(@RequestBody Map<String, Object> datamap) {
        // {placement:custom|random, player: 1|2, shipCoordinates: [[0,0],[0,0]]|null}

        Player.Placement placement = Player.Placement.valueOf(datamap.get("placement").toString());

        if (placement.equals(Player.Placement.CUSTOM)) {
            // TODO
            List<List<List<Integer>>> shipCoordinates = (List<List<List<Integer>>>) datamap.get("shipCoordinates");

            GameBoard gameBoard = new GameBoard(game.getGame().sizeOfBoard);
            for (List<List<Integer>> ship : shipCoordinates) {
                List<Coordinate> coordinates = new ArrayList<>();
                for (List<Integer> coordinate : ship) {
                    coordinates.add(new Coordinate(coordinate.get(0), coordinate.get(1), game.getGame().sizeOfBoard));
                }

                int size = ((int) (Math.sqrt(Math.pow(ship.get(0).get(0) - ship.get(1).get(0), 2) + Math.pow(ship.get(0).get(1) - ship.get(1).get(1), 2)))) + 1;
                switch (size) {
                    case 1:
                        Ship oneDeck = new OneDeckFactory().createShip(coordinates);
                        gameBoard.placeShip(oneDeck);
                        break;
                    case 2:
                        Ship twoDeck = new TwoDeckFactory().createShip(coordinates);
                        gameBoard.placeShip(twoDeck);
                        break;
                    case 3:
                        Ship threeDeck = new ThreeDeckFactory().createShip(coordinates);
                        gameBoard.placeShip(threeDeck);
                        break;
                    case 4:
                        Ship fourDeck = new FourDeckFactory().createShip(coordinates);
                        gameBoard.placeShip(fourDeck);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid ship type");
                }
            }

            game.getPlayers().get("player" + datamap.get("player")).setGameBoard(gameBoard);
        } else {
            // game.getPlayers().get("player" + datamap.get("player")).placeShips(Player.Placement.RANDOM, Integer.parseInt(datamap.get("player").toString()));
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping(path = "/inGame", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = ORIGIN)
    public ResponseEntity<Object> inGame(@RequestBody Map<String, Object> datamap) {
        // { player: 1|2 }
        HashMap<String, Object> map = new HashMap();
        map.put("turn", game.getGame().turn);

        map.put("player1_name", game.getGame().player1);
        map.put("player2_name", game.getGame().player2);

        map.put("player1_board", game.getPlayers().get("player1").getGameBoard());
        map.put("player2_board", game.getPlayers().get("player2").getGameBoard());

        map.put("winner", game.endGame());

        return new ResponseEntity<Object>(new Gson().toJson(map), HttpStatus.OK);
    }

    @PostMapping(path = "/shoot", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = ORIGIN)
    public ResponseEntity<Object> shoot(@RequestBody Map<String, Object> datamap) {
        // { player: 1|2, x: number, y: number }

        game.updateGame(
                Integer.parseInt(datamap.get("x").toString()),
                Integer.parseInt(datamap.get("y").toString()),
                1);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
