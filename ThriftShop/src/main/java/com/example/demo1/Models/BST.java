package com.example.demo1.Models;

import java.util.ArrayList;
import java.util.List;

public class BST {

    public BST() {
        this.root = null;
    }

    private class Node {
        Prenda prenda;
        Node left, right;

        Node(Prenda prenda) {
            this.prenda = prenda;
        }
    }

    private Node root;

    public void insert(Prenda prenda) {
        root = insertRec(root, prenda);
    }

    private Node insertRec(Node root, Prenda prenda) {
        if (root == null) {
            root = new Node(prenda);
            return root;
        }
        if (prenda.precio <= root.prenda.precio)
            root.left = insertRec(root.left, prenda);
        else if (prenda.precio > root.prenda.precio)
            root.right = insertRec(root.right, prenda);

        return root;
    }

    public List<Prenda> filterByPrice(List<Prenda> resultados ,int  minPrice, int maxPrice) {
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