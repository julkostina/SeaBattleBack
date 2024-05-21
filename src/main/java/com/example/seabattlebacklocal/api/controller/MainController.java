package com.example.seabattlebacklocal.api.controller;
import com.example.seabattlebacklocal.source.GameEngine;
import com.example.seabattlebacklocal.source.Serialization;
import com.example.seabattlebacklocal.source.StateGame.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class MainController {
    private final FileService fileService = new FileService();
    Serialization serialization = new Serialization();
    GameEngine game = new GameEngine();    

    private Object MediaType;

     @GetMapping("/")
     public String index() {
         return "Greetings from Spring Boot!";
     }
//    @PostMapping(path = "/", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> updateSettings(String fileName) {
//        serialization.setFileName(fileName);
//        Game game = serialization.readFile();
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return new ResponseEntity<Object>(gson.toJson(game), HttpStatus.OK);
//    }

    @PostMapping("/upload")//вантажить файли на комп'ютер

    public void uploadFile(@RequestParam("file") MultipartFile file){
         fileService.save(file);
    }
    @GetMapping("/get/{file}")
    public ResponseEntity<Resource> getFile(@PathVariable String file){
          Resource resource = (Resource) fileService.getFile(file);
         if(resource!=null){
            return ResponseEntity.ok()
                     .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\""+resource.getFilename()+"\"")
                     .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                     .body(resource);
         }
         return  ResponseEntity.internalServerError().build();
    }


//
//    @GetMapping(path = "/play", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)//    @CrossOrigin(origins = "http://localhost:3000/play")
//    @CrossOrigin(origins = "http://localhost:5173")
//    public ResponseEntity<Object> getPlaySettings() {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return new ResponseEntity<Object>(gson.toJson(GameBoard.data), HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/actual-play", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> actualPlay(@RequestBody Map<String, Object> datamap) {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return new ResponseEntity<Object>(gson.toJson(GameBoard.data), HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/results", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> getResults() {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return new ResponseEntity<Object>(gson.toJson(GameBoard.data), HttpStatus.OK);
//    }
}
