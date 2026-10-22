import java.util.ArrayList;
import java.util.List;

public class DnaSequenceTSP {

    private static double minWeight = Double.MAX_VALUE;
    private static List<Integer> bestPath = new ArrayList<>();

    public static void main(String[] args) {
        // Sample short DNA reads/fragments
        String[] fragments = {"ATGC", "GCAT", "TGCA", "GATC"};
        int n = fragments.length;

        // Step 1: Precompute the asymmetric distance matrix based on non-overlapping characters
        int[][] distanceMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                } else {
                    distanceMatrix[i][j] = calculateCost(fragments[i], fragments[j]);
                }
            }
        }

        // Step 2: Initialize variables for permutations
        List<Integer> currentPath = new ArrayList<>();
        boolean[] visited = new boolean[n];

        // Step 3: Run Brute Force search starting from index 0
        currentPath.add(0);
        visited[0] = true;
        bruteForceTSP(distanceMatrix, 0, n, 1, 0, currentPath, visited);

        // Step 4: Reconstruct the final master DNA sequence from the optimal path
        StringBuilder reconstructedDNA = new StringBuilder(fragments[bestPath.get(0)]);
        for (int i = 1; i < bestPath.size(); i++) {
            String prev = fragments[bestPath.get(i - 1)];
            String curr = fragments[bestPath.get(i)];
            int overlap = prev.length() - distanceMatrix[bestPath.get(i - 1)][bestPath.get(i)];
            reconstructedDNA.append(curr.substring(overlap));
        }

        // Output results
        System.out.println("Optimal Path Order of Fragment Indices: " + bestPath);
        System.out.print("Ordered Fragments: ");
        for (int idx : bestPath) {
            System.out.print(fragments[idx] + " -> ");
        }
        System.out.println("DONE");
        System.out.println("Reconstructed Master DNA Sequence: " + reconstructedDNA.toString());
    }

    /**
     * Calculates the "cost" of appending fragment 'b' after fragment 'a'.
     * Cost = number of unique characters added (Total length of 'b' minus the max overlap).
     */
    private static int calculateCost(String a, String b) {
        int maxOverlap = 0;
        int minLen = Math.min(a.length(), b.length());

        for (int i = 1; i <= minLen; i++) {
            if (a.endsWith(b.substring(0, i))) {
                maxOverlap = i;
            }
        }
        return b.length() - maxOverlap; 
    }

    /**
     * Brute Force recursive permutation function evaluating every possible sequence order.
     */
    private static void bruteForceTSP(int[][] matrix, int currPos, int n, int count, 
                                      int cost, List<Integer> currentPath, boolean[] visited) {
        
        // Base case: All fragments visited
        if (count == n) {
            if (cost < minWeight) {
                minWeight = cost;
                bestPath = new ArrayList<>(currentPath);
            }
            return;
        }

        // Try moving to every unvisited fragment
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                currentPath.add(i);

                bruteForceTSP(matrix, i, n, count + 1, cost + matrix[currPos][i], currentPath, visited);

                // Backtracking steps
                currentPath.remove(currentPath.size() - 1);
                visited[i] = false;
            }
        }
    }
}