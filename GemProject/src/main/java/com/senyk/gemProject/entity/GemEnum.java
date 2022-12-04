package com.senyk.gemProject.entity;

public enum GemEnum {
    //Gems
    Diamond("Diamond",3.52,10.0,"white"),
    Amber("Amber",1.1,2.5,"orange"),
    Ruby("Ruby",2.05,9.0,"red"),
    Emerald("Emerald",2.76,8.0,"green"),
    Sapphire("Sapphire",4.06,9.0,"blue"),
    Topaz("Topaz",2.53,8.0,"yellow"),
    Amethyst("Amethyst",2.65,7.0,"purple"),
    Pearls("Pearls",2.85,4.5,"pink")
    //Half-gems
    ;
    private String name;
    private Double density;//щільність
    private Double hardness;//твердість
    private String color;
    GemEnum(String name, Double density, Double hardness, String color) {
        this.name = name;
        this.density = density;
        this.hardness = hardness;
        this.color=color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Double getHardness() {
        return hardness;
    }

    public void setHardness(Double hardness) {
        this.hardness = hardness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
