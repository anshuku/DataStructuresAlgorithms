package DynamicProgramming;


/**
 * @author anshukumar
 *
 * Uses Dynamic Programming as there are sequence of decisions to reach the most optimal solutions(shortest path between each vertex)
 * Floyd and Warshall Algorithm is used to find All Pairs Shortest Path between vertices.
 * 
 * The non connected vertices are given by Integer.MAX_VALUE-100 because the a[i][k] + a[k][j] will result into backward(nagative int) addition
 */
public class AllPairsShortestPathFloydWarshall {

	public static void main(String[] args) {
		
		int A[][] = {
				{0, 3, Integer.MAX_VALUE-100, 7},
				{8, 0 ,2, Integer.MAX_VALUE-100},
				{5, Integer.MAX_VALUE-100, 0, 1},
				{2, Integer.MAX_VALUE-100, Integer.MAX_VALUE, 0}
		};
		System.out.println("Original Array is");
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<A[0].length; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}
		
		allPairsShortestPath(A);
		
		System.out.println("Array with All Pairs shortest path is");
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<A[0].length; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void allPairsShortestPath(int[][] a) {
		//K is the current matrix being generated
		for(int k = 0; k<a.length; k++) {
			for(int i = 0; i<a.length; i++) {
				for(int j = 0; j<a.length; j++) {
					a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
				}
			}
		}
		
	}

}
