package line_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class test_2 {

	static String input;

	static ArrayList<String> chemical_elem;
	static ArrayList<Integer> chemic_num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();

		input = br.readLine();

		chemical_elem = new ArrayList<>();
		chemic_num = new ArrayList<>();

		for (int i = 0; i < input.length(); i++) {
			int tmp = input.charAt(i);
			// 대문자일때
			if (tmp - 'A' >= 0 && tmp - 'Z' <= 0) {
				if (i < input.length() - 1) {
					int tmp2 = input.charAt(i + 1);
					if (tmp2 - 'a' >= 0 && tmp2 - 'z' <= 0) {
						chemical_elem.add(input.substring(i, i + 2));
						i++;
					} else {
						chemical_elem.add(input.substring(i, i + 1));
					}
				} else {
					chemical_elem.add(input.substring(i, i + 1));
				}
			}
			// 숫자일때
			else {
				chemic_num.add(Character.getNumericValue(input.charAt(i)));
			}
		}

		if (chemical_elem.size() != chemic_num.size()) {
			System.out.println("error");
		} else {
			for (int i = 0; i < chemical_elem.size(); i++) {
				result.append(chemical_elem.get(i));
				if (chemic_num.get(i) > 1) {
					result.append(chemic_num.get(i));
				}
			}
		}

		System.out.println(result);
		br.close();
	}

}
