package com.github.weaxme.graph;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
public class Graph {

    public void execute() {

        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);

        addEdge(v2, v0);
        addEdge(v0, v2);

        addEdge(v0, v1);
        addEdge(v1, v2);
        addEdge(v2, v3);
        addEdge(v3, v3);
        addEdge(v0, new Vertex(4));
        addEdge(v0, new Vertex(5));
        log.info("v0 hashcode: {}", v0.hashCode());
        log.info("Graph: {}", breadthFirst(v1));
    }


    private List<Integer> breadthFirst(Vertex current) {
        List<Integer> result = new LinkedList<>();

        Set<Vertex> visited = new HashSet<>();

        LinkedList<Vertex> queue = new LinkedList<>();
        queue.add(current);

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();

            if (!visited.contains(vertex)) {
                result.add(vertex.getData());
                visited.add(vertex);
                vertex.getEdges().stream()
                        .flatMap(e -> Stream.of(e.getIn(), e.getOut()))
                        .forEach(queue::add);
            }
        }

        return result;
   }

    private Edge addEdge(Vertex out, Vertex in) {
        if (out.isConnected(in, true)) {
            return null;
        }

        Edge edge = new Edge(out, in);

        if (out.equals(in)) {
            out.addEdge(edge);
        } else {
            out.addEdge(edge);
            in.addEdge(edge);
        }
        return edge;
    }

    private void deleteEdge(Vertex out, Vertex in) {
        Edge edge = out.getConnectedEdge(in, false);
        if (edge != null) {
            out.removeEdge(edge);
            in.removeEdge(edge);
        }
    }
}
