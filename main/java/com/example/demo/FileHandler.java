package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileHandler {
    private static String pathStr = "C:\\Users\\bllnkky\\IdeaProjects\\lab3\\demo\\src\\main\\webapp\\myData.dat";
    private static Path path = Paths.get(pathStr);
    public static void writeFurnitureToFile(Furniture furnitures) throws IOException {
        String furnituresString = "";
        furnituresString+=furnitures.getFurniture()+",";
        furnituresString+=furnitures.getProduced()+",";
        furnituresString+=furnitures.getColor()+",";
        furnituresString+=furnitures.getWidth()+",";
        furnituresString+=furnitures.getHeight()+"\n";
        if(!Files.exists(path)) {
            Files.createFile(path);
        }
        Files.write(path, furnituresString.getBytes(), StandardOpenOption.APPEND);
    }
    public static ArrayList<Furniture> readFurnitureFromFile() throws IOException {
        if(Files.exists(path)) {
            BufferedReader reader = new BufferedReader(new FileReader(pathStr));
            String data = reader.readLine();
            ArrayList<Furniture> furnitures = new ArrayList<>();
            while(data != null) {
                String[] parameters = data.split(",");
                Furniture tempFurniture = new Furniture(parameters[0],parameters[1],parameters[2],Float.parseFloat(parameters[3]),Float.parseFloat(parameters[4]));
                furnitures.add(tempFurniture);
                data = reader.readLine();
            }
            reader.close();
            return furnitures;
        }
        return null;
    }
}
