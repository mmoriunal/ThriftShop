class LinkedList <T>{
    Node<T> head;
    Node<T> tail;

    static class Node <T> {
        T data;
        Node<T> next;
        Node(T d){
            data = d;
            next = null;
        }
    }
    public void pushFront(T data){
        Node<T> newNode = new Node<T>(data);
        if (head == null){
            head = newNode;
            tail = newNode;
        } else {
            head.next = newNode;
        }
    }
    public void pushBack(T data){
        Node<T> newNode = new Node<T>(data);
        if (head == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }
    public void popFront(){
        if (head == null){
            System.out.println("La lista está vacía");
        } else {
            head = head.next;
        }
    }
    public void popBack(){
        Node<T> p =  head;
        if (head == null){ //la lista está vacía
            System.out.println("No se puede hacer");
        } else if (p == tail) { //la lista tiene un solo elemento
            head = null;
            tail = null;
        } else { //Caso general
            while (p.next != tail){
                p = p.next;
            }
            tail = p;
            p.next = null;
        }
    }
    public void delete(int index) {
        if (head == null) {
            System.out.println("La lista está vacía");
            return;
        }

        if (index == 0) {
            head = head.next;
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                System.out.println("El índice está fuera de rango");
                return;
            }
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("El índice está fuera de rango");
            return;
        }

        previous.next = current.next;
    }

    public void add(T data, int position) {

        Node<T> newNode = new Node<>(data);
        if (position == 0) { // Insertar al principio de la lista
            newNode.next = head;
            head = newNode;
            if (tail == null) { // Si la lista está vacía, también actualizamos el tail
                tail = newNode;
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) { // Si el nuevo nodo se inserta al final, actualizamos el tail
                tail = newNode;
            }
        }
    }

    public void getData(){
        Node<T> p = head;
        if (head == null){
            System.out.println("La lista se encuentra vacía");
        } else {
            System.out.print("[");
            while (p != null){
                if (p.next == null){
                    System.out.println(p.data + "]");
                } else {
                    System.out.print(p.data + ",");
                }
                p = p.next;
            }
        }
    }
    public void topFront(){
        if (head == null){
            System.out.println("La lista se encuentra vacía");
        } else {
            System.out.println(head.data);
        }
    }
    public T topBack(){
        if (tail == null)
            return null;
        else {
            System.out.println((T) tail.data);
            return (T) tail.data;
        }
    }
    public boolean find(T data){
        Node<T> p = head;
        if (head == null)
            return false;
        if (p == data)
            return true;
        while (p.next != null){
            if (p.data == data){
                System.out.print("true");
                return true;
            }
            System.out.print("false");
            return false;
        }
        return false;
    }
    public boolean isEmpty(){
        if (head==null) return true;
        else return false;
    }
    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    public void setCantidad(int index, int nuevaCantidad) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        ((ItemCarrito) current.data).setCantidad(nuevaCantidad);
    }
    public String getData(int index) {
        Node<T> current = head;
        int currentIndex = 0;
        while (current != null) {
            if (currentIndex == index) {
                return current.data.toString();
            }
            current = current.next;
            currentIndex++;
        }
        return null; // Si no se encuentra el índice
    }
}