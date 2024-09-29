package com.example.demo1.services;

import java.util.List;

import com.example.demo1.models.AVL;
import com.example.demo1.models.BST;
import com.example.demo1.models.Prenda;

public class FiltradoPrecioService {
    private BST bst;
    private AVL avl;

    public FiltradoPrecioService() {
        bst = new BST();
        avl = new AVL();
    }

    public void addPrendaBST(Prenda prenda) {
        bst.insert(prenda);
    }

    public void addPrendaAVL(Prenda prenda) {
        avl.insert(prenda);
    }


    public List<Prenda> filtradoPorPrecioAVL(List<Prenda> resultados,int minPrice, int maxPrice) {
        List<Prenda> prendas = avl.filterByPrice(resultados,minPrice, maxPrice);
        return prendas;
    }

    private void printPrendas(List<Prenda> prendas) {
        int n = prendas.size();
        System.out.println(n+" Resultados encontrados");
        if (prendas.isEmpty()) {
            System.out.println("No se encontraron prendas en el rango de precio especificado.");
        } else {
            for (Prenda prenda : prendas) {
                System.out.println(prenda);
            }
        }
    }

}
