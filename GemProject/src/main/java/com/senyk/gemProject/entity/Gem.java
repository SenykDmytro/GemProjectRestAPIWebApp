package com.senyk.gemProject.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Gem {
    @Id
    @SequenceGenerator(
            name = "gem_sequence",
            sequenceName = "gem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gem_sequence"
    )
    private Long id;//id

    private String name;//дорогоцінне каміння
    private Double carat=0.0;//карат-міра ваги коштовних каменів
    private Double price=0.0;//ціна

    private String shape;//форма
    private String color;//забарвлення

    private Double density=0.0;//щільність
    private Double hardness=0.0;//твердість

    @ManyToMany
    private List<Sketch> sketchList;
    @ManyToOne
    private Necklace necklace;
    private String status="No used";

    public Gem(Long id, String name, Double carat, Double price, String shape, String color, Double density,
               Double hardness, List<Sketch> sketchList, Necklace necklace, String status) {
        this.id = id;
        this.name = name;
        this.carat = carat;
        this.price = price;
        this.shape = shape;
        this.color = color;
        this.density = density;
        this.hardness = hardness;
        this.sketchList = sketchList;
        this.necklace = necklace;
        this.status = status;
    }
    public Gem(String name, Double carat, Double price, String shape) {
        this.name = name;
        this.carat = carat;
        this.price = price;
        this.shape = shape;
        if(name.toLowerCase().equals(GemEnum.Diamond.getName().toLowerCase())){
            helpGem(GemEnum.Diamond);
        }else if(name.toLowerCase().equals(GemEnum.Amber.getName().toLowerCase())){
            helpGem(GemEnum.Amber);
        }else if(name.toLowerCase().equals(GemEnum.Amethyst.getName().toLowerCase())){
            helpGem(GemEnum.Amethyst);
        }else if(name.toLowerCase().equals(GemEnum.Emerald.getName().toLowerCase())){
            helpGem(GemEnum.Emerald);
        }else if(name.toLowerCase().equals(GemEnum.Pearls.getName().toLowerCase())){
            helpGem(GemEnum.Pearls);
        }else if(name.toLowerCase().equals(GemEnum.Ruby.getName().toLowerCase())){
            helpGem(GemEnum.Ruby);
        }else if(name.toLowerCase().equals(GemEnum.Sapphire.getName().toLowerCase())){
            helpGem(GemEnum.Sapphire);
        }else if(name.toLowerCase().equals(GemEnum.Topaz.getName().toLowerCase())){
            helpGem(GemEnum.Topaz);
        }else {
            this.color="without";
            this.density=0.0;
            this.hardness=0.0;
        }
    }
    private void helpGem(GemEnum gemEnum){
        this.color=gemEnum.getColor();
        this.density=gemEnum.getDensity();
        this.hardness=gemEnum.getHardness();
    }
    public Gem(String name, Double carat, Double price, String shape, String color, Double density, Double hardness) {
        this.name = name;
        this.carat = carat;
        this.price = price;
        this.shape = shape;
        this.color = color;
        this.density = density;
        this.hardness = hardness;
    }
    public Gem() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getCarat() {
        return carat;
    }
    public void setCarat(Double carat) {
        this.carat = carat;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getShape() {
        return shape;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<Sketch> getSketchList() {
        return sketchList;
    }
    public void setSketchList(List<Sketch> sketchList) {
        this.sketchList = sketchList;
    }
    public Necklace getNecklace() {
        return necklace;
    }
    public void setNecklace(Necklace necklace) {
        this.necklace = necklace;
    }

    @Override
    public String toString() {
        return "Gems{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carat=" + carat +
                ", price=" + price +
                ", shape='" + shape + '\'' +
                ", color='" + color + '\'' +
                ", density=" + density +
                ", hardness=" + hardness +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gem gem = (Gem) o;
        return name.equals(gem.name) && carat.equals(gem.carat) && price.equals(gem.price) && Objects.equals(shape, gem.shape) && Objects.equals(color, gem.color) && Objects.equals(density, gem.density) && Objects.equals(hardness, gem.hardness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, carat, price, shape, color, density, hardness);
    }
}


