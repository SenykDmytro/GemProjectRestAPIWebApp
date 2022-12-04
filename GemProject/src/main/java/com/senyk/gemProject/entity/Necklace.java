package com.senyk.gemProject.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Necklace {
    @Id
    @SequenceGenerator(
            name = "necklace_sequence",
            sequenceName = "necklace_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "necklace_sequence"
    )
    private Long id;
    private String name;

    @OneToMany
    private List<Gem> gems;

    public Necklace(Long id, String name, List<Gem> gems) {
        this.id = id;
        this.name = name;
        this.gems = gems;
    }
    public Necklace(String name, List<Gem> gems) {
        this.name = name;
        this.gems = gems;
    }

    public Necklace(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Necklace(String name) {
        this.name = name;
    }
    public Necklace() {
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
        return "Necklace{" +
                "id=" + id +
                "name=" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Necklace necklace = (Necklace) o;
        return Objects.equals(id, necklace.id) && Objects.equals(name, necklace.name) && Objects.equals(gems, necklace.gems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gems);
    }
}
