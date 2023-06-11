package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet("/write")
public class WriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        sc.getRequestDispatcher("/jsp/write.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathStr = "C:\\Users\\bllnkky\\IdeaProjects\\lab3\\demo\\src\\main\\webapp\\myData.dat";
        Path path = Paths.get(pathStr);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Furniture> FurnitureList = new ArrayList<>();
        String jsonArrayString="";
        if(!Files.exists(path)) {
            Files.createFile(path);
        }
        jsonArrayString = Files.readString(path);
        if(jsonArrayString!="") {
            Type listOfFurniture = new TypeToken<ArrayList<Furniture>>() {}.getType();
            FurnitureList = gson.fromJson(jsonArrayString, listOfFurniture);
        }
        String data = request.getReader().readLine();
        Furniture furnitures = gson.fromJson(data, Furniture.class);
        FurnitureList.add(furnitures);
        FileHandler.writeFurnitureToFile(furnitures);
    }

}