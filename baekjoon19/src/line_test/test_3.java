package line_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class test_3 {

	static int h1, h2;
	static String[] header1;
	static String[] header2;

	static String[][] table1;
	static String[][] table2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		header1 = new String[3];
		header2 = new String[3];

		table1 = new String[3][h1];
		table2 = new String[3][h2];

		h1 = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int idx1 = 0;
		while (st.hasMoreTokens()) {
			header1[idx1] = st.nextToken();
			idx1++;
		}

		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}


	}

}



//6
//id name occupation
//5 Brown Accountant
//2 Cony Programmer
//3 Sally Doctor
//1 James Singer
//4 Moon Dancer
//7
//id city zip
//2 Seoul 10008
//7 Busan 40002
//5 Gwangju 20009
//6 Daegu 30008
//3 Seoul 40005
//1 Seoul 50006
//
//
//
//id name occupation city zip
//1 James Singer Seoul 50006
//2 Cony Programmer Seoul 10008
//3 Sally Doctor Seoul 40005
//4 Moon Dancer NULL NULL
//5 Brown Accountant Gwangju 20009
