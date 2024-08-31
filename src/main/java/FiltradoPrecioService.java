import java.util.List;

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

    public void filtradoPorPrecioBST(int minPrice, int maxPrice) {
        List<Prenda> prendas = bst.filterByPrice(minPrice, maxPrice);
        //printPrendas(prendas);
    }

    public void filtradoPorPrecioAVL(int minPrice, int maxPrice) {
        List<Prenda> prendas = avl.filterByPrice(minPrice, maxPrice);
        //printPrendas(prendas);
    }

    private void printPrendas(List<Prenda> prendas) {
        if (prendas.isEmpty()) {
            System.out.println("No se encontraron prendas en el rango de precio especificado.");
        } else {
            for (Prenda prenda : prendas) {
                System.out.println(prenda);
            }
        }
    }

    public long[] testPerformance(List<Prenda> prendas, int minPrice, int maxPrice, int N) {
        int bstInsertionTimeAVG = 0, 
            bstFilteringTimeAVG = 0, 
            bstTotalTimeAVG,
            avlInsertionTimeAVG = 0,  
            avlFilteringTimeAVG = 0,
            avlTotalTimeAVG;

        for(int i = 0; i < N; i++){
            // BST
            bst = new BST();
            long startTime = System.nanoTime();
            for (Prenda prenda : prendas) {
                addPrendaBST(prenda);
            }
            long endTime = System.nanoTime();
            long bstInsertionTime = (endTime - startTime) / 1000000;

            startTime = System.nanoTime();
            filtradoPorPrecioBST(minPrice, maxPrice);
            endTime = System.nanoTime();
            long bstFilteringTime = (endTime - startTime) / 1000000;

            bstInsertionTimeAVG += bstInsertionTime;
            bstFilteringTimeAVG += bstFilteringTime;

            // AVL
            avl = new AVL();
            startTime = System.nanoTime();
            for (Prenda prenda : prendas) {
                addPrendaAVL(prenda);
            }
            endTime = System.nanoTime();
            long avlInsertionTime = (endTime - startTime) / 1000000;

            startTime = System.nanoTime();
            filtradoPorPrecioAVL(minPrice, maxPrice);
            endTime = System.nanoTime();
            long avlFilteringTime = (endTime - startTime) / 1000000;

            avlInsertionTimeAVG += avlInsertionTime;
            avlFilteringTimeAVG += avlFilteringTime;
        }

        bstInsertionTimeAVG /= N;
        bstFilteringTimeAVG /= N;
        bstTotalTimeAVG = bstInsertionTimeAVG + bstFilteringTimeAVG;
        avlInsertionTimeAVG /= N;
        avlFilteringTimeAVG /= N;
        avlTotalTimeAVG = bstInsertionTimeAVG + bstFilteringTimeAVG;

        System.out.println("BST Insertion Time: " + bstInsertionTimeAVG + " ms");
        System.out.println("BST Filtering Time: " + bstFilteringTimeAVG + " ms");
        System.out.println("BST Total Time: " + bstTotalTimeAVG + " ms");

        System.out.println("AVL Insertion Time: " + avlInsertionTimeAVG + " ms");
        System.out.println("AVL Filtering Time: " + avlFilteringTimeAVG + " ms");
        System.out.println("AVL Total Time: " + avlTotalTimeAVG + " ms");

        long [] a = new long[] {bstInsertionTimeAVG, bstFilteringTimeAVG, bstTotalTimeAVG, avlInsertionTimeAVG, avlFilteringTimeAVG, avlTotalTimeAVG};
        return a;
    }
    public long[] testPerformance(List<Prenda> prendas, int minPrice, int maxPrice) {
        return testPerformance(prendas, minPrice, maxPrice, 1);
    }
}
