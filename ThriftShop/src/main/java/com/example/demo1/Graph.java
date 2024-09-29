package com.example.demo1;

import java.util.*;

public class Graph {

    private Map<Prenda, Set<Prenda>> adjList;  // Lista de adyacencia

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addPrenda(Prenda prenda) {
        adjList.putIfAbsent(prenda, new HashSet<>());
    }

    public void connectPrenda(Prenda prenda1, Prenda prenda2) {
        adjList.get(prenda1).add(prenda2);  // Conectar prenda1 a prenda2
        adjList.get(prenda2).add(prenda1);  // Conectar prenda2 a prenda1
    }

    public Set<Prenda> getConnections(Prenda prenda) {
        return adjList.getOrDefault(prenda, new HashSet<>());
    }

    public List<Prenda> getSampleConnections(Prenda prenda, int count) {
        Set<Prenda> conexiones = getConnections(prenda);
        return new ArrayList<>(conexiones).subList(0, Math.min(count, conexiones.size()));
    }

    public void buildGraphbySize(List<Prenda> prendasFiltradas) {
        for (Prenda prenda : prendasFiltradas) {
            addPrenda(prenda);
        }

        for (int i = 0; i < prendasFiltradas.size(); i++) {
            for (int j = i + 1; j < prendasFiltradas.size(); j++) {
                Prenda prenda1 = prendasFiltradas.get(i);
                Prenda prenda2 = prendasFiltradas.get(j);

                if (prenda1.getTalla().compareTo(prenda2.getTalla()) == 0 ) {
                    connectPrenda(prenda1, prenda2);
                }
            }
        }
    }

    public Map<Prenda, Set<Prenda>> getAdjacencyList() {
        return adjList;
    }

    public void showGraph() {
        for (Prenda prenda : adjList.keySet()) {
            System.out.print("Prenda " + prenda.getNombre() + " está conectada con: ");
            for (Prenda conectada : adjList.get(prenda)) {
                System.out.print(conectada.getNombre() + ", ");
            }
            System.out.println();
        }
    }
}
