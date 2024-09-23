class HashMapUsuario {
    private static final int INITIAL_CAPACITY = 16;
    private Node[] table;

    public HashMapUsuario() {
        table = new Node[INITIAL_CAPACITY];
    }

    private static class Node {
        final String key;
        Usuario value;
        Node next;

        Node(String key, Usuario value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int hash(String key) {
        return Math.abs(key.hashCode() % table.length);
    }

    public void put(String key, Usuario value) {
        int index = hash(key);
        Node newNode = new Node(key, value, null);
        Node current = table[index];

        if (current == null) {
            table[index] = newNode;
        } else {
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Reemplazar si la clave ya existe
                    return;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value;
            } else {
                current.next = newNode;
            }
        }
    }

    public Usuario get(String key) {
        int index = hash(key);
        Node current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }
}
