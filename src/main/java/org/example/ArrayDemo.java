package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayDemo {
	public static void main(String[] args) {
		// Initialising an array the class way
		int[] integerArrayOldWay = new int[]{1, 2, 3, 4, 5};
		// Initialising an array the modern way
		int[] integerArrayNewWay = {10, 20, 30, 40, 50};

		// Classic for loop
		for (int i = 0; i < integerArrayNewWay.length; i++) {
			System.out.println(i + 1 + ". " + integerArrayNewWay[i]);
		}

		// Enhanced for loop
		for (int intValue : integerArrayNewWay) {
			System.out.println(intValue);
		}

		System.out.println("-----------------");

		// 2D array initialisation
		int[][] twoDimensionalArray = {{1, 3, 5, 7, 9}, {2, 4, 6, 8, 10}, {10, 100, 1000}};

		for (int i = 0; i < twoDimensionalArray.length; i++) {
			for (int j = 0; j < twoDimensionalArray[i].length; j++) {
				System.out.println(twoDimensionalArray[i][j]);
			}
		}

		int[] integerArrayNewWayCopy = integerArrayNewWay.clone();
		int[] integerArrayNewWayCopy2 = integerArrayNewWay;

		for (int i : integerArrayNewWayCopy) {
			System.out.println(i);
		}

		System.out.println("Arrays are " + (integerArrayNewWay == integerArrayNewWayCopy ? "equal." : "not equal."));
		System.out.println("Arrays are " + (integerArrayNewWay == integerArrayNewWayCopy2 ? "equal." : "not equal."));

		System.out.println("");
		System.out.println("------------------------------------------");
		System.out.println("");

		String[] cities = generateCities();
		printStringArray(cities);

		var myCities = Arrays.copyOf(cities, cities.length);
		//var myCities = Arrays.copyOfRange(cities, 1, 4);
		printStringArray(myCities);

		Arrays.sort(myCities);
		printStringArray(myCities);

		Arrays.sort(myCities, new Comparator<>() {
			@Override
			public int compare(String o1, String o2) {
				int result = o1.compareTo(o2);
				System.out.printf("Array comparison between %s and %s resulted into: %d", o1, o2, result);
				System.out.println();
				return result;
			}
		});

		printStringArray(myCities);

		var profiles = generateContent();
		for (Profile p: profiles) {
			System.out.println(p);
		}
	}

	private static void printStringArray(String[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%d.%s, ", i + 1, array[i]);
		}
		System.out.println();
	}

	static String[] generateCities() {
		String[] cities = {"Athens", "Thessaloniki", "Patra", "Iraklio", "Ioannina", "Larisa", "Volos" };
		return cities;
	}

	static Profile[] generateContent() {
		var profile1 = new Profile();
		profile1.setUsername("user1");
		profile1.setUsername("user1@nbg.gr");

		var profile2 = new Profile();
		profile2.setUsername("user2");
		profile2.setUsername("user2@nbg.gr");

		Profile[] profiles = {profile1, profile2 };
		return profiles;
	}

}

class Profile {
	private static boolean flag;
	private String username;
	private String email;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		Profile.flag = flag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Account{" + "username='" + username + '\'' + ", email='" + email + '\'' + '}';
	}
}
