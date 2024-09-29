package com.example.demo1;

import java.util.ArrayList;
import java.util.List;

public class AVL {

    public AVL() {
        this.root = null;
    }

    private class Node {
        Prenda prenda;
        Node left, right;
        int height;

        Node(Prenda prenda) {
            this.prenda = prenda;
            height = 1;
        }
    }

    private Node root;

    public void insert(Prenda prenda) {
        root = insertRec(root, prenda);
    }

    private Node insertRec(Node node, Prenda prenda) {
        if (node == null)
            return new Node(prenda);

        if (prenda.precio < node.prenda.precio)
            node.left = insertRec(node.left, prenda);
        else if (prenda.precio > node.prenda.precio)
            node.right = insertRec(node.right, prenda);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && prenda.precio < node.left.prenda.precio)
            return rightRotate(node);

        if (balance < -1 && prenda.precio > node.right.prenda.precio)
            return leftRotate(node);

        if (balance > 1 && prenda.precio > node.left.prenda.precio) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && prenda.precio < node.right.prenda.precio) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    private int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    

    public List<Prenda> filterByPrice(List<Prenda> resultados,int minPrice, int maxPrice) {
        resultstree(resultados);
        List<Prenda> result = new ArrayList<>();
        filterByPriceRec(root, minPrice, maxPrice, result);
        return result;
    }
    public void resultstree(List<Prenda> resultados){
        root=null;
        for(int i=0;i<resultados.size();i++){
            insert(resultados.get(i));
        }
    }
    private void filterByPriceRec(Node root, int minPrice, int maxPrice, List<Prenda> result) {
        if (root == null)
            return;

        if (minPrice < root.prenda.precio)
            filterByPriceRec(root.left, minPrice, maxPrice, result);

        if (minPrice <= root.prenda.precio && maxPrice >= root.prenda.precio)
            result.add(root.prenda);

        if (maxPrice > root.prenda.precio)
            filterByPriceRec(root.right, minPrice, maxPrice, result);
    }
}