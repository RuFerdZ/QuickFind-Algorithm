/****************************************************************************
 *  Compilation within a console or terminal:
 *  Windows version: javac -cp .;stdlib.jar QuickFindUF.java
 *  OS / Linux version: javac -cp .:stdlib.jar QuickFindUF.java
 *
 *  Execution within a console or terminal:
 *  Windows version: java -cp .;stdlib.jar QuickFindUF < tinyUF.txt (or mediumUF.txt, or largeUF.txt)
 *  OS / Linux version: javac -cp .:stdlib.jar QuickFindUF < tinyUF.txt (or mediumUF.txt, or largeUF.txt)
 *  Dependencies: StdIn.java StdOut.java
 *  Adaptation: Enable and disable statements where appropriately in the lines of code below.
 *
 *  NOTE for execution within an IDE (i.e., Eclipse or NetBeans): In Eclipse, drag the corresponding input data file
 *  over the main project folder and COPY it; In NetBeans. copy-paste the .txt files into the NetBeans project
 *  directory. Accordingly, change the file name in the statement, for instance, reading data from the
 *  tinyUF.txt file:
 *
 *  input = new Scanner(new File("tinyUF.txt"));
 *
 *  NOTE (2) for execution within an IDE: Do not forget to add the stdlib.jar to the Libraries.
 *
 *
 ****************************************************************************/

/**
 *  The QuickFindUF class represents a union-find data structure.
 *  It supports the union and find operations, along with
 *  methods for determining whether two objects are in the same component
 *  and the total number of components.
 *  <p>
 *  This implementation uses quick find.
 *
 */

import java.io.*;  // For reading the input data within an IDE - DELETE if you work within a console or terminal
import java.util.Arrays;
import java.util.Scanner; // For reading the input data within an IDE - DELETE if you work within a console or terminal

public class QuickFindUF {
    private int[] id;    // id[i] = component identifier of i
    private int count;   // number of components

    /**
     * Initialises an empty union-find data structure with N isolated components 0 through N-1.
     * @throws java.lang.IllegalArgumentException if N < 0
     * @param N the number of objects
     */

    public QuickFindUF(int N) throws java.lang.IllegalArgumentException {
        id = new int[N];
        for(int x = 0; x<N;x++ ){
            id[x] = x;
        }
    }

    /**
     * Returns the number of components.
     * @return the number of components (between 1 and N)
     */

    public int count(){
        int[]  comp = id;
        Arrays.sort(comp);

        int[] temp = new int[comp.length];
        int j = 0;
        for (int i=0; i<comp.length-1; i++){
            if (temp[i] != comp[i+1]){
                temp[j++] = comp[i];
            }
        }
        temp[j++] = comp[comp.length-1];
        // Changing original array
        for (int i=0; i<j; i++){
            comp[i] = temp[i];
        }
        return comp.length;
    }

    /**
     * Returns the component identifier for the component containing site <tt>p</tt>.
     * @param p the integer representing one site
     * @return the component identifier for the component containing site <tt>p</tt>
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= p < N
     */

    public int find(int p) throws java.lang.IndexOutOfBoundsException {
        return id[p];
    }

    /**
     * Are the two sites <tt>p</tt> and <tt>q/tt> in the same component?
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt> are in
     *    the same component, and <tt>false</tt> otherwise
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */

    public boolean connected(int p, int q) {
        return id[p]==id[q];
    }

    /**
     * Merges the component containing site<tt>p</tt> with the component
     * containing site <tt>q</tt>.
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
     */

    public void union(int p, int q) {
        int p_val = id[p];
        int q_val = id[q];

        for (int x = 0;x <id.length;x++){
            if (id[x]==p_val){
                id[x]=q_val;
            }
        }
    }

    /**
     * Reads in a sequence of pairs of integers (between 0 and N-1) from standard input,
     * where each integer represents some object;
     * if the objects are in different components, merge the two components
     * and print the pair to standard output.
     */

    public static void main(String[] args) {

        // ONLY for IDE version, remove otherwise
        Scanner input = null;
        try {
            input = new Scanner(new File("tinyUF.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // END of IDE version

        // int N = StdIn.readInt();  <- ENABLE if reading from console or terminal
        int N = input.nextInt(); // ONLY for IDE version

        System.out.println("Number of nodes is: " + N);
        QuickFindUF uf = new QuickFindUF(N);

        // while (!StdIn.isEmpty()) {   <- ENABLE ONLY if reading from console or terminal
        while (input.hasNextInt()) { // ONLY for IDE version, remove otherwise
            // int p = StdIn.readInt(); <- ENABLE ONLY if reading from console or terminal
            int p = input.nextInt(); // ONLY for IDE version, remove otherwise
            // int q = StdIn.readInt(); <- ENABLE ONLY if reading from console or terminal
            int q = input.nextInt(); // ONLY for IDE version, remove otherwise
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}



