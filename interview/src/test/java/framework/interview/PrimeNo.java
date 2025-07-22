package framework.interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PrimeNo {
	LinkedList<String> yes = new LinkedList<>();
	LinkedList<String> no = new LinkedList<>();

	public static void main(String[] args) {
		PrimeNo p = new PrimeNo();
		p.limit(2000);
		p.yes.stream()

				.forEach(f -> System.out.println(f));
		p.no.stream()

				.forEach(f -> System.out.println(f));

	}

	public void isPrime(int num) {
		String one;

		boolean isPrime = true;

		if (num <= 1) {
			one = "\"" + num + "\"is not a prime number";
			no.add(one);
			return;
		}

		else {
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if (num % i == 0) {
					one = "\"" + num + "\"is not a prime number";
					no.add(one);
					isPrime = false;
					break;
				}

			}
		}

		if (isPrime) {
			one = "\"" + num + "\"is a prime number";
			yes.add(one);
		}

	}

	public void limit(int limit) {
		for (int i = 2; i <= limit; i++) {
			isPrime(i);
		}
	}
}
