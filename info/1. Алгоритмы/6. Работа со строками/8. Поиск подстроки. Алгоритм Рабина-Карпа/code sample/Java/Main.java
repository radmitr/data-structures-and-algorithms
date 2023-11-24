import java.util.Arrays;

public class Main {

	public final static int BASE = 31;
	public final static int q = 2147483647;

	public static void main(String[] args) {
		System.out.println(Arrays.toString(substringSearch("Awersome apple", "some","wers")));

	}

	public static int gornerScheme(char[] sym, int start, int end) {
		int result = (int) (sym[start]);
		for (int i = start; i < end - 1; i++) {
			result = result * BASE + (int) sym[i + 1];
		}
		return result;
	}

	public static int calculateHash(char[] sym, int start, int end) {
		return gornerScheme(sym, start, end) % q;
	}

	public static int[] substringSearch(String baseText, String... subStrings) {
		int[] result = new int[] { -1, -1 };
		if (!checkEqualsLength(subStrings)) {
			throw new IllegalArgumentException("substrings must be the same length");
		}
		int[] hashArray = new int[subStrings.length];
		for (int i = 0; i < hashArray.length; i++) {
			hashArray[i] = calculateHash(subStrings[i].toCharArray(), 0, subStrings[i].length());
		}
		char[] sym = baseText.toCharArray();
		int start = 0;
		int m = subStrings[0].length();
		int end = start + m;
		int textPartHash = calculateHash(sym, start, end);
		int mult = basePow(m - 1);
		for (;;) {
			int[] someHas = findSomeHash(textPartHash, hashArray);
			if (someHas.length > 0) {
				String text = new String(sym, start, m);
				for (int i = 0; i < someHas.length; i++) {
					if (text.equals(subStrings[someHas[i]])) {
						result[0] = start;
						result[1] = someHas[i];
						return result;
					}
				}
			}
			start += 1;
			end += 1;
			if (end > sym.length) {
				break;
			}
			textPartHash = ((textPartHash - mult * (int) sym[start - 1]) * BASE + sym[end - 1]) % q;
		}

		return result;
	}

	public static int basePow(int n) {
		if (n == 0) {
			return 1;
		}
		int result = 1;
		for (int i = 0; i < n; i++) {
			result *= BASE;
		}
		return result;
	}

	public static int[] findSomeHash(int hash, int[] subHashs) {
		int n = 0;
		for (int i = 0; i < subHashs.length; i++) {
			if (subHashs[i] == hash) {
				n += 1;
			}
		}
		int[] result = new int[n];
		int insertIndex = 0;
		for (int i = 0; i < subHashs.length; i++) {
			if (subHashs[i] == hash) {
				result[insertIndex++] = i;
			}
		}
		return result;
	}

	public static boolean checkEqualsLength(String[] texts) {
		int l = texts[0].length();
		for (int i = 0; i < texts.length; i++) {
			if (texts[i].length() != l) {
				return false;
			}
		}
		return true;
	}

}
