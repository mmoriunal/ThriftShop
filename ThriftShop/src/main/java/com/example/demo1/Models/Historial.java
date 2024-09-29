package com.example.demo1.Models;

import java.util.Stack;

import com.example.demo1.Services.HistorialEntry;

public class Historial {

    private static Stack<HistorialEntry> stack = new Stack<>();
    public static void agregar(String fxmlFile) {
        if (!esUltimaEntrada(fxmlFile, null)) {
            stack.push(new HistorialEntry(fxmlFile));
        }
    }
    public static void agregar(String fxmlFile, Object data) {
        if (!esUltimaEntrada(fxmlFile, null)) {
            stack.push(new HistorialEntry(fxmlFile, data));
        }
    }
    private static boolean esUltimaEntrada(String fxmlFile, Object data) {
        if (stack.isEmpty()) {
            return false;
        }
        HistorialEntry ultimaEntrada = stack.peek();
        return ultimaEntrada.getFxmlFile().equals(fxmlFile) &&
                ((ultimaEntrada.getData() == null && data == null) ||
                        (ultimaEntrada.getData() != null && ultimaEntrada.getData().equals(data)));
    }
    public static HistorialEntry atras() {
        if (!stack.isEmpty()) {
            return stack.pop();
        }
        return null;
    }

    public static boolean hayHistorial() {
        return !stack.isEmpty();
    }
}
