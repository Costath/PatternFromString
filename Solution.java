public class Solution {

	public static void main(String[] args) {

		System.out.println("Result: " + TestSolution("aaaa") + " | " + "Expected: 4 | aaaa");
		System.out.println("Result: " + TestSolution("abcdeab") + " | " + "Expected: 1 | abcdeab");
		System.out.println("Result: " + TestSolution("abcabcabcabc") + " | " + "Expected: 4 | abcabcabcabc");
		System.out.println("Result: " + TestSolution("abccbaabccba") + " | " + "Expected: 2 | abccbaabccba");
		System.out.println("Result: " + TestSolution("a") + " | " + "Expected: 1 | a");
		System.out.println("Result: " + TestSolution("aaaabbbaaaa") + " | " + "Expected: 1 | aaaabbbaaaa");
	}

	private static int TestSolution(String source) {
		int maxParts = 1;
		int sourceLength = source.length();
		boolean isPrime = true;
		boolean exit = false;

		for (int i = 2; i <= sourceLength/2; i++) {
			if (sourceLength % i == 0) {
				isPrime = false;
			}
		}
		if (isPrime) {
			for (int i = 0; i < sourceLength; i++) {
				if (source.charAt(0) != source.charAt(i)) {
					return 1;
				}
			}
			return sourceLength;
		}else {
			String sequence = source.substring(0, 1);
			String subStr;
			int seqIndex = 0;
			int take = 0;
			boolean sequenceCorrect = false;
			char nextOfSeq = source.charAt(0);

			exit = false;

			for (int i = 1; i < sourceLength; i++) {
					if (source.charAt(i) == nextOfSeq) { //if the char being analyzed is equal to the next char form the sequence
						seqIndex ++; // set the index to the next char of the sequence
						try {
							nextOfSeq = sequence.charAt(seqIndex); // update the next char to be analyzed
						} catch (StringIndexOutOfBoundsException e) { // raised an exception when the sequence end is reached
							maxParts ++;
							nextOfSeq = sequence.charAt(0); // reset the char being analyzed to the first of the sequence
							seqIndex = 0;
						}
					}else {
						do { // checking the sequence against the source. Flag if the sequence is correct
							try {
								subStr = source.substring(take, sequence.length() + take);
								take += sequence.length();

								if (!subStr.equals(sequence) ) {
									exit = true;
								}else if (take == sourceLength) { // flag if it reaches the final of the source and there was no incorrect subStr
									sequenceCorrect = true;
								}
							} catch (StringIndexOutOfBoundsException e) {
								exit = true; //exception is raised if the source length is not multiple of the sequence, meaning the sequence is wrong
							}
						} while (!exit);

						if (!sequenceCorrect) {
							sequence = source.substring(0, i + 1 - seqIndex); // update the sequence in case it's wrong
							maxParts = 1; // reset the value of maximum parts to 1
						}
					}
			}
		}
		return maxParts;
	}
}
