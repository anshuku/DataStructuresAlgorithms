package DynamicProgramming;

/**
 * @author anshukumar
 *
 * Uses Dynamic Programming
 */
public class MultiStageGraph {

	public static void main(String[] args) {
		
		int stages = 4, n = 8;
		int C[][] = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 2, 1, 3, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 2, 3, 0, 0},
				{0, 0, 0, 0, 0, 6, 7, 0, 0},
				{0, 0, 0, 0, 0, 6, 8, 9, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 6},
				{0, 0, 0, 0, 0, 0, 0, 0, 4},
				{0, 0, 0, 0, 0, 0, 0, 0, 5},
				{0, 0, 0, 0, 0, 0, 0, 0, 0}
		};//cost adjacency matrix representing the cost of edges/graph
		
		int cost[] = new int[9];
		int d[] = new int[9];//from which vertex we'll get the shortest path
		int path[] = new int[9];
		
		minimumDistanceMultiStage(C, cost, d, n);
		path = pathMultiStage(d, path, stages);
		System.out.println("The optimal cost is ");
		for(int i: cost) {
			System.out.print(i + " -> ");
		}
		System.out.println();
		
		System.out.println("The optimal path is ");
		for(int i: d) {
			System.out.print(i + " -> ");
		}
		System.out.println();
		
		System.out.println("The optimal path taken is ");
		for(int i: path) {
			System.out.print(i + " -> ");
		}
	} 


	//solve it from last vertex
	private static void minimumDistanceMultiStage(int[][] C, int[] cost, int[] d, int N) {
		cost[N] = 0;
		for(int i=N-1; i>=1; i--) {
			int min = Integer.MAX_VALUE;
			for(int j=i+1; j<=N; j++) {
				if(C[i][j]!=0 && (cost[j] + C[i][j]) < min) {
					min = cost[j] + C[i][j];
					d[i] = j;
				}
			}
			cost[i] = min;
		}
	}

	private static int[] pathMultiStage(int[] d, int[] path, int stages) {
		
		path[stages] = 8;
		path[1] = 1;
		for(int i = 2; i<stages; i++) {
			path[i] = d[path[i-1]];
		}
		
		return path;
		
	}

}
