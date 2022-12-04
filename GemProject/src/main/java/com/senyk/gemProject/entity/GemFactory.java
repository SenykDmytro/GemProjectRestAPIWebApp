package com.senyk.gemProject.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class GemFactory {

    private List<Gem> list;

    public GemFactory(List<Gem> list) {
        this.list = list;
    }

    public List<Gem> getList() {
        return list;
    }

    public void setList(List<Gem> list) {
        this.list = list;
    }
}


