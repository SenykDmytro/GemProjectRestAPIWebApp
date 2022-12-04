package com.senyk.gemProject.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Sketch {

    @Id
    @SequenceGenerator(
            name = "sketch_sequence",
            sequenceName = "sketch_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sketch_sequence"
    )
    private Long id;
    private String name;
    @ManyToMany
    private List<Gem> gems;

    public Sketch(Long id, String name, List<Gem> gems) {
        this.id = id;
        this.name = name;
        this.gems = gems;
    }
    public Sketch(String name, List<Gem> gems) {
        this.name = name;
        this.gems = gems;
    }

    public Sketch(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sketch(String name) {
        this.name = name;
    }
    public Sketch() {
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
    public List<Gem> getGems() {
        return gems;
    }
    public void setGems(List<Gem> gems) {
        this.gems = gems;
    }

    @Override
    public String toString() {
        return "Sketch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sketch sketch = (Sketch) o;
        return Objects.equals(name, sketch.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
