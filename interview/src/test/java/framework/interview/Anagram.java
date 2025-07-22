package framework.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Anagram {

	String input = "hello";

	String ana = "nHelloM";

	public void anagramChecker(String entry, String tocheck) {

		entry = entry.replace("\\s", "").toLowerCase();
		tocheck = tocheck.replace("\\s", "").toLowerCase();

		if (entry.length() != tocheck.length()) {
			System.out.println(entry + " and " + tocheck + " is not an Anagram");
			return;
		}

		char[] charE = entry.toCharArray();
		char[] charT = tocheck.toCharArray();
		boolean[] match = new boolean[charE.length];

		for (int i = 0; i < charE.length; i++) {
			boolean found = false;
			// loop to check the elements
			for (int j = 0; j < charT.length; j++) {
				if (charE[i] == charT[j] && !match[j]) {
					match[j] = true;
					found = true;
					break;
				}

			}
			if (!found) {
				System.out.println(entry + " and " + tocheck + " is not an anagram");
				return;
			}

		}

	}

	public static void main(String[] args) {
		Anagram aa = new Anagram();
		aa.usingSort("o h p", "oph");
	}

	public void listCheck(String[] list) {
		String one = "hello";
		String two = "lloeh";

		one = one.replace("\\d", "").toLowerCase();
		two = two.replace("\\d", "").toLowerCase();

		if (one.equals(two)) {
			System.out.println(one + " and " + two + " are not anagrams");
			return;
		}

		char[] arr1 = one.toCharArray();
		char[] arr2 = two.toCharArray();

		boolean[] matched = new boolean[arr1.length];

		for (int i = 0; i < arr1.length; i++) {

			boolean found = false;

			for (int j = 0; j < arr2.length; j++) {

				if (arr1[i] == arr2[j] && !matched[j]) {
					matched[j] = true;
					found = true;
					break;
				}

			}
			if (!found) {
				System.out.println(one + " and " + two + " are not anagrams");
				return;
			}

		}
		System.out.println(one + " and " + two + "are  anagrams");

	}

	public void arrays(String s1, String s2) {
		String temp = s1.replaceAll("\\s+", "");
		s2 = s2.replace("//s", "");
		System.out.println(temp);
		char[] arr1 = s1.toLowerCase().toCharArray();
		char[] arr2 = s2.toLowerCase().toCharArray();

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		if (Arrays.equals(arr1, arr2)) {
			System.out.println("It is equal");
		}

	}

	public String bubbleSort(String input) {

		input = input.replaceAll("\\s", "");

		char[] arr = input.toCharArray();

		boolean swapped;

		for (int i = 0; i < input.length()-1; i++) {
			swapped = false;

			for (int j = 0; j < input.length() - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					char temp = arr[j];

					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}

			}
			if (!swapped) {
				break;
			}

		}
		return new String(arr);

	}

	public void usingSort(String one, String two) {
		String x = bubbleSort(one);
		String y = bubbleSort(two);

		if (x.equalsIgnoreCase(y)) {
			System.out.println("\""+one+"\""+" and \""+two+"\" its an anagram");
		}
	}

}
