package com.github.weaxme.graph;

import lombok.Data;
import lombok.NonNull;

@Data
public class Edge {


    private int data;


    @NonNull
    private Vertex out;

    @NonNull
    private Vertex in;


    @Override
    public String toString() {
        return "Edge{" +
                "data=" + data +
                ", out=" + out.getData() +
                ", in=" + in.getData() +
                '}';
    }
}
