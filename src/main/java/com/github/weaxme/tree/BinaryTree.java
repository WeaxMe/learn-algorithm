package com.github.weaxme.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class BinaryTree {

    private static int MAX_NUM = 50;

    public void execute() {
        Node root = new Node(1);
        root.setLeft(new Node(2, new Node(4), new Node(6)));
        root.setRight(new Node(3, null, new Node(7, new Node(9), new Node(10))));
        log.info("root: {}", root);
        log.info("data: {}", inorderTraversal(root));
    }

    private String inorderTraversal(Node node) {
        if (node.isLeaf()) {
            return node.data + "";
        }
        if (node.left == null) {
            return node.data + " " + inorderTraversal(node.right);
        }
        return inorderTraversal(node.left) + " " + node.data + " " + inorderTraversal(node.right);
    }

    private Node fillTree(Node root, int layers) {
        if (layers > 0) {
            int dataLeft = new Random().nextInt(MAX_NUM);
            int dataRight = new Random().nextInt(MAX_NUM);
            Node left = fillTree(new Node(dataLeft), layers - 1);
            Node right = fillTree(new Node(dataRight), layers - 1);
            root.setLeft(left);
            root.setRight(right);
        }
        return root;
    }

    @Data
    @AllArgsConstructor
    private static class Node {

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
}
