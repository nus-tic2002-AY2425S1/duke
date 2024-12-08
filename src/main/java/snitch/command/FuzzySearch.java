package snitch.command;

/**
 * Provides fuzzy search capabilities, including a method to calculate
 * the Levenshtein Distance between two strings.
 */

// code reuse: https://www.geeksforgeeks.org/introduction-to-levenshtein-distance/
public class FuzzySearch {

    /**
     * Calculates the Levenshtein Distance between two strings recursively.
     *
     * @param str1 The first string.
     * @param str2 The second string.
     * @param m    The length of the first string.
     * @param n    The length of the second string.
     * @return The Levenshtein Distance between the two strings.
     */
    public static int levenshteinRecursive(String str1, String str2, int m, int n) {
        if (m == 0) {
            return n; // Base case: str1 is empty
        }
        if (n == 0) {
            return m; // Base case: str2 is empty
        }

        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return levenshteinRecursive(str1, str2, m - 1, n - 1); // Characters match
        }

        return 1 + Math.min(
                levenshteinRecursive(str1, str2, m, n - 1), // Insert
                Math.min(
                        levenshteinRecursive(str1, str2, m - 1, n),    // Remove
                        levenshteinRecursive(str1, str2, m - 1, n - 1) // Replace
                )
        );
    }
}