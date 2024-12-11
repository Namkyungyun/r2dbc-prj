package com.example.yamlgeneratorservice.generator.obj;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Gateway {
    private ArrayList<Filters> filters;
    private ArrayList<Routes> routes;
}