package com.github.weaxme.graph;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class Vertex {

    @NonNull
    private int data;

    @Setter(AccessLevel.PRIVATE)
    private List<Edge> edges = new LinkedList<>();

    public Vertex addEdge(Edge edge) {
        edges.add(edge);
        return this;
    }

    public Vertex removeEdge(Edge edge) {
        edges.remove(edge);
        return this;
    }

    public List<Edge> getOutEdges() {
        return edges.stream()
                .filter(e -> e.getOut().equals(this))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public List<Edge> getInEdges() {
        return edges.stream()
                .filter(e -> e.getIn().equals(this))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public boolean isConnected(Vertex vertex, boolean in) {
        return getConnectedEdge(vertex, in) != null;
    }

    public Edge getConnectedEdge(Vertex vertex, boolean in) {
        Predicate<Edge> filter;

        if (this.equals(vertex)) {
            filter = e -> e.getIn().equals(vertex) && e.getOut().equals(vertex);
        } else {
            filter = in ? e -> e.getIn().equals(vertex) : e -> e.getOut().equals(vertex);
        }

        return edges.stream()
                .filter(filter)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int hashCode() {
        return data * 234;
    }
}
