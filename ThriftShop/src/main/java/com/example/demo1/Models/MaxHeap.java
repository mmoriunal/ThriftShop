package com.example.demo1.Models;

class MaxHeap extends MinHeap {
    public MaxHeap() {
        super();
    }

    public void SiftUp(int i){
        while (i > 0 && heapArray[i].precio > heapArray[parent(i)].precio) {
            swap(heapArray,parent(i) ,i );
            i = parent(i);
        }
    }

    public Prenda getMax() {
        return getMin();
    }

    public Prenda extractMax() {

        if (current_heap_size <= 0) {
            return null;
        }

        if (current_heap_size == 1) {
            current_heap_size--;
            return heapArray[0];
        }

        Prenda root = heapArray[0];
        heapArray[0] = heapArray[current_heap_size - 1];
        current_heap_size--;
        SiftDown(0);

        return root;
    }
    private void SiftDown(int key) {
        int l = left(key);
        int r = right(key);
        int maxIndex = key;

        if (l < current_heap_size && heapArray[l].precio > heapArray[maxIndex].precio) {
            maxIndex = l;
        }
        if (r < current_heap_size && heapArray[r].precio > heapArray[maxIndex].precio) {
            maxIndex = r;
        }

        if (maxIndex != key) {
            swap(heapArray, key, maxIndex);
            SiftDown(maxIndex);
        }
}}


