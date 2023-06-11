package com.example.demo;

import lombok.Getter;
public class Furniture {
    @Getter private String furniture;
    @Getter private String produced;
    @Getter private String color;
    @Getter private float width;
    @Getter private float height;

    public Furniture(String furniture, String produced, String color, float width, float height) {
        this.furniture = furniture;
        this.produced = produced;
        this.color = color;
        this.width = width;
        this.height = height;
    }
    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass()!=obj.getClass()) return false;
        Furniture furnitures = (Furniture) obj;
        if(!furniture.equals(furnitures.furniture)) return false;
        if(!produced.equals(furnitures.produced)) return false;
        if(!color.equals(furnitures.color)) return false;
        if(width != furnitures.width) return false;
        return height == furnitures.height;

    }
    @Override
    public int hashCode() {
        int result = (int) ((int)height ^ ((int)height >>> 32));
        result = 31 * result + furniture.hashCode();
        result = 31 * result + produced.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + (int) width;
        return result;
    }
}