package com.github.weaxme.tree;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinarySearchTree {

    public void execute() {
        Node root = new Node(8);
        insert(root, 3);
        insert(root, 1);
        insert(root, 6);
        insert(root, 4);
        insert(root, 7);
        insert(root, 10);
        insert(root, 14);
        insert(root, 13);


        log.info("search(6): {}", search(root, 6));
    }

    private Node insert(Node root, int key) {
        if (key < root.getData()) {
            Node node = root.getLeft();
            if (node == null) {
                node = new Node(key);
                root.setLeft(node);
                return node;
            }
            return insert(node, key);
        }

        if (key > root.getData()) {
            Node node = root.getRight();
            if (node == null) {
                node = new Node(key);
                root.setRight(node);
                return node;
            }
            return insert(node, key);
        }
        return root;
    }

    private Node search(Node root, int key) {
        if (key < root.getData()) {
            Node node = root.getLeft();
            return node != null ? search(node, key) : null;
        } else if (key > root.getData()) {
            Node node = root.getRight();
            return node != null ? search(node, key) : null;
        }
        return root;
    }
}