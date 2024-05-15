 package com.example.seabattlebacklocal.source;
// import com.example.seabattlebacklocal.source.Serialization;

import java.io.IOException;
import java.util.Dictionary;

public class Main {
    public static void main(String[] args) throws IOException {
        Serialization serialization = new Serialization();
        Dictionary<Character,Character > dictionary  = serialization.readFile();
        System.out.println("Dictionary"+ dictionary);
    }
}