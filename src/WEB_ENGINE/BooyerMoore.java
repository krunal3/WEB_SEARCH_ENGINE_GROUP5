package WEB_ENGINE;

public class BooyerMoore {
	private final int R; // the radix
	private static int[] right; // the bad-character skip array

	private char[] pattern; // store the pattern as a character array
	private String pat; // or as a string

	// pattern provided as a string
	public BooyerMoore(String pat) {
		this.R = 10000;
		this.pat = pat;

		// position of rightmost occurrence of c in the pattern
		right = new int[R];
		for (int c = 0; c < R; c++)
			right[c] = -1;
		for (int j = 0; j < pat.length(); j++)
			right[pat.charAt(j)] = j;
	}

	// return offset of first match; N if no match
	public static int search(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();
		int skip;
		try {
		for (int i = 0; i <= N - M; i += skip) {
			skip = 0;
			for (int j = M - 1; j >= 0; j--) {
				if (pat.charAt(j) != txt.charAt(i + j)) {
					skip = Math.max(1, j - right[txt.charAt(i + j)]);
					break;
				}
			}
			if (skip == 0)
				return i; // found
		}
		}
		catch(Exception e) {}	
		return N; // not found

		
		}

}
