package framework.interview;

import java.util.HashMap;
import java.util.Map;

public class Duplicates {

	public void check(String input) {

		Map<Character, Integer> map = new HashMap<>();

		char[] arr = input.toCharArray();

		for (char c : arr) {

			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		
		
		for (Map.Entry<Character, Integer> x : map.entrySet()) {

			System.out.println(x.getKey() + " : " + x.getValue());

		}

	}

	public static void main(String[] args) {
		Duplicates d = new Duplicates();

		d.check("hello");
	}
}
