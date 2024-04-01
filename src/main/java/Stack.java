public class Stack <T>{
    private T[] pila;
    private int top;
    public int length;

    @SuppressWarnings("unchecked")
    public Stack(int length){
        this.length = length;
        pila = (T[]) new Object[length];
        top = 0;
    }
    public void push(T nuevo){
        if (isFull())
            throw new IllegalStateException("La pila está llena");
        else {
            pila[top] = nuevo;
            top++;
        }
    }
    public T pop(){
        if (isEmpty())
            return null;
        else {
            top--;
            return pila[top];
        }
    }

    public boolean isEmpty(){
        return top==0;
    }
    public boolean isFull(){
        return top==length;
    }
    public T top(){
        if (isEmpty())
            return null;
        else {
            return pila[top-1];
        }
    }
    public void verStack() {
        for (int i = top - 1; i >= 0; i--) {
            System.out.println(pila[i]);
        }
    }
}
