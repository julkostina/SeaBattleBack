package com.example.seabattlebacklocal.source;

import com.example.seabattlebacklocal.source.ObserverPattern.GameBoard;
import javafx.scene.effect.DisplacementMap;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

public class Main {
    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
//        GameEngine game = new GameEngine();
//        game.initGame("player1", "player2", 8, -5);
        Player player1  = new Player("name", new GameBoard(8));
        player1.placeShips(Player.Placement.RANDOM);

}

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            // System.out.println("Let's inspect the beans provided by Spring Boot:");
//            //
//            // String[] beanNames = ctx.getBeanDefinitionNames();
//            // Arrays.sort(beanNames);
//            // for (String beanName : beanNames) {
//            // System.out.println(beanName);
//            // }
//
//        };
//    }
}
