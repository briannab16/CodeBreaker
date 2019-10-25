import java.util.ArrayList;

public class BreakShift {
	private static double[] englProbs = new double[26];
	private static double[] cipherProbs;
	
	public static int breakShift(ArrayList<Character> list) {
		int k = 0;
		
		initEnglProbs();
		initCipherProbs(list);
		
		double temp = 0;
		double maxSum = 0;
		
		for (int i = 0; i < 26; i++) {
			temp = 0;
			
			for (int j = 0; j < englProbs.length; j++) {
				temp += englProbs[j] * cipherProbs[(j + i) % 26];
			}

			if (temp > maxSum) {
				
				maxSum = temp;
				k = i;
			}
			
		}
		
		System.out.println(k + " " + maxSum);
		return k;
	}
	
	private static void initEnglProbs() {
		englProbs[0] = 8.04;
		englProbs[1] = 1.54;
		englProbs[2] = 3.06;
		englProbs[3] = 3.99;
		englProbs[4] = 12.51;
		englProbs[5] = 2.30;
		englProbs[6] = 1.96;
		englProbs[7] = 5.49;
		englProbs[8] = 7.26;
		englProbs[9] = 0.16;
		englProbs[10] = 0.67;
		englProbs[11] = 4.14;
		englProbs[12] = 2.53;
		englProbs[13] = 7.09;
		englProbs[14] = 7.60;
		englProbs[15] = 2.00;
		englProbs[16] = 0.11;
		englProbs[17] = 6.12;
		englProbs[18] = 6.54;
		englProbs[19] = 9.25;
		englProbs[20] = 2.71;
		englProbs[21] = 0.99;
		englProbs[22] = 1.92;
		englProbs[23] = 0.19;
		englProbs[24] = 1.73;
		englProbs[25] = 0.09;
	}
	
	private static void initCipherProbs(ArrayList<Character> list) {
		int size = 0;
		cipherProbs = new double[26];
		
		for (int i = 0; i < list.size(); i++) {
			cipherProbs[(int)list.get(i) - 65]++;
			size++;
		}
		
		for (int i = 0; i < cipherProbs.length; i++) {
			cipherProbs[i] = cipherProbs[i] / (double)size;
		}

	}

}
