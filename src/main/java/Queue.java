public class Queue <T> {
    private T[] elements;
    private int size;
    private int front;
    private int rear;
  
    public Queue(int maxsize) {
      @SuppressWarnings("unchecked") T[] elements = (T[]) new Object[maxsize];
      this.elements = elements;
      size = 0;
      front = 0;
      rear = 0;
    }
  
    public void enqueue(T item) {
      if (isFull()) {
        System.out.println("La cola está llena");
        return;
      }
      elements[rear] = item;
      rear = (rear + 1) % elements.length;
      size++;
    }
  
    public T dequeue() {
      if (isEmpty()) {
        System.out.println("La cola está vacía");
        return null;
      }
      T item = elements[front];
      front = (front + 1) % elements.length;
      size--;
      return item;
    }
  
    public T peek() {
      if (isEmpty()) {
        System.out.println("La cola está vacía");
        return null;
      }
      return elements[front];
    }
  
    public T peekAt(int index){
      if (index < 0 || index >= size) {
        System.out.println("Indice fuera de limites");
        return null;
      }
      return elements[(front + index) % elements.length];
    }
  
    public void printQueue(){
      for (int i = 0; i < size; i++) {
        System.out.println(peekAt(i));
      }
    }
  
    public boolean isFull(){
      return size == elements.length;
    }
  
    public boolean isEmpty() {
      return size == 0;
    }
  
    public int getSize() {
      return size;
    }
  }
