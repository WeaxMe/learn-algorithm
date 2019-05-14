package com.github.weaxme.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node {

    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
