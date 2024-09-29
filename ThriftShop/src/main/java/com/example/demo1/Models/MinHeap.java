package com.example.demo1.models;

public class MinHeap {
    Prenda[] heapArray;
    int capacity;
    int current_heap_size;

    public MinHeap() {
        capacity = 100;
        heapArray = new Prenda[capacity];
        current_heap_size = 0;
    }

    protected void swap(Prenda[] arr, int a, int b) {
        Prenda temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    protected int parent(int key) {
        return (key - 1) / 2;
    }

    protected int left(int key) {
        return 2 * key + 1;
    }

    protected int right(int key) {
        return 2 * key + 2;
    }

    public boolean insertKey(Prenda key) {
        if (current_heap_size == capacity) {
            capacity *= 2;
            Prenda[] newHeapArray = new Prenda[capacity];
            System.arraycopy(heapArray, 0, newHeapArray, 0, current_heap_size);
            heapArray = newHeapArray;
        }

        int i = current_heap_size;
        heapArray[i] = key;
        current_heap_size++;
        SiftUp(i);

        return true;
    }
    public void SiftUp(int i){
        while (i != 0 && heapArray[i].precio < heapArray[parent(i)].precio) {
            swap(heapArray, i, parent(i));
            i = parent(i);
        }
    }
    private void SiftDown(int key) {
        int l = left(key);
        int r = right(key);
        int minindex = key;

        if (l < current_heap_size && heapArray[l].precio < heapArray[minindex].precio) {
            minindex = l;
        }
        if (r < current_heap_size && heapArray[r].precio < heapArray[minindex].precio) {
            minindex = r;
        }

        if (minindex != key) {
            swap(heapArray, key, minindex);
            SiftDown(minindex);
        }
    }
    public void decreaseKey(int key, Prenda new_val) {
        heapArray[key] = new_val;

        while (key != 0 && heapArray[key].precio < heapArray[parent(key)].precio) {
            swap(heapArray, key, parent(key));
            key = parent(key);
        }
    }

    public Prenda getMin() {
        return heapArray[0];
    }

    public Prenda extractMin() {
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

    public void deleteKey(int key) {
        decreaseKey(key, null);
        extractMin();
    }


    public boolean isEmpty() {
        return current_heap_size == 0;
    }
}

