package com.company;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    public Node left;
    public Node right;
    public int val;

    public Node(int val) {
        this(val, null, null);
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Main {
    public static void main(String[] args) {
        Node tree = new Node(
                5,
                new Node(
                        7,
                        new Node(4),
                        new Node(3)
                ),
                new Node(
                        10,
                        new Node(2),
                        new Node(13)

                )
        );
        System.out.println(max(tree));
    }

    public static int max(Node tree) {
        int max = Integer.MIN_VALUE;

        Queue<Node> queue = new LinkedList<>();
        queue.add(tree);

        while (!queue.isEmpty()){
            Node remove = queue.remove();

            if(remove.left != null) {
                queue.add(remove.left);
                if(max < remove.left.val){
                    max =  remove.left.val;
                }
            }

            if(remove.right != null) {
                queue.add(remove.right);
                if(max < remove.right.val){
                    max =  remove.right.val;
                }
            }
        }

        return max;

    }
}