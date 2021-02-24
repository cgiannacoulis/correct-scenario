package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.sort;

public class MyApp {
	public static void main(String[] args) {
		ArrayList<String> myList = new ArrayList<>();
		String[] myStrings = {"mary", "costas", "john", "mark"};

		sort(myStrings);

		int myInt = 10;

		System.out.println("My int value is " + myInt);
		messWithInt(myInt);
		System.out.println("My int value is " + myInt);
		System.out.println("--------------------------------------");
		String tempString = "Costas";//['C','o','s','t','a','s']
		tempString = "Constantinos";

		System.out.println("My string value is " + tempString);
		messWithString(tempString);
		System.out.println("My string value is " + tempString);
		System.out.println("--------------------------------------");

		Account firstAccount = generateAccount("costas", "c.giannacoulis@codehub.gr");
		Account secondAccount = generateAccount("yiannis", "i.klian@codehub.gr");

		System.out.println("--------------------------------------");
		System.out.println("My account is " + firstAccount);
		messWithAccount(firstAccount);
		System.out.println("My account is " + firstAccount);
		System.out.println("--------------------------------------");

		System.out.println(firstAccount.isFlag());
		System.out.println(secondAccount.isFlag());

		secondAccount.setFlag(true);

		System.out.println(firstAccount.isFlag());
		System.out.println(secondAccount.isFlag());

		// Use of ternary operator
		String username = firstAccount.getUsername() != null ? firstAccount.getUsername() : "user131";
		if (firstAccount.getUsername() != null) {
			username = firstAccount.getUsername();
		} else {
			username = "user131";
		}

		List<String> myNewList = new ArrayList<>();
		List<String> mynewListBackup = new LinkedList<>();

		manipulateList(myNewList);
		manipulateList(mynewListBackup);

		System.out.println("-------------------------------");

		Car myCar = new Car();
		myCar.setupEngine(1600, "Gauloises", Tyre.MEDIUM);
		myCar.unPark();
		myCar.gotoStartingLine();
		myCar.race();
		myCar.gotoPit();
		myCar.leavePit();
		myCar.finish();
		myCar.park();
	}

	private static void manipulateList(List<String> targetList) {
		targetList.add("Attiki");
	}

	private static void messWithInt(int myInteger) {
		myInteger += 10; // myInt = myInt + 10
		System.out.println("My int value within the method is " + myInteger);
	}

	private static void messWithString(String myString) {
		myString = myString + " Giannacoulis";
		System.out.println("My string value within the method is " + myString);
	}

	private static void messWithAccount(Account myAccount) {
		myAccount.setUsername("costas.giannacoulis");
		System.out.println("My account within the method is " + myAccount);
	}

	private static Account generateAccount(String username, String email) {
		Account newAccount = new Account();
		newAccount.setUsername(username);
		newAccount.setEmail(email);

		return newAccount;
	}
}

class Engine {
	void start() {
		System.out.println("Starting engine.");
	}

	void stop() {
		System.out.println("Stopping engine.");
	}
}

abstract class Vehicle {
	Engine carEngine = new Engine();

	abstract void park();

	abstract void unPark();

	final void startEngine() {
		carEngine.start();
	}

	final void stopEngine() {
		carEngine.stop();
	}
}

enum Tyre {
	SOFT, MEDIUM, HARD;
}

interface Racing {
	void gotoPit();

	void leavePit();

	void gotoStartingLine();

	void race();

	void finish();

	void setupEngine(int horsePower, String sponsor, Tyre typeOfTyre);
}

class Car extends Vehicle implements Racing {
	@Override
	void park() {
		System.out.println("I'm parking.");
		stopEngine();
	}

	@Override
	void unPark() {
		System.out.println("I'm un-parking.");
		startEngine();
	}

	@Override
	public void gotoPit() {
		System.out.println("Entering pit.");
	}

	@Override
	public void leavePit() {
		System.out.println("Leaving pit.");
	}

	@Override
	public void gotoStartingLine() {
		System.out.println("Heading to starting line.");
	}

	@Override
	public void race() {
		System.out.println("Race has just begun.");
	}

	@Override
	public void finish() {
		System.out.println("Race has finished.");
	}

	@Override
	public void setupEngine(int horsePower, String sponsor, Tyre typeOfTyres) {
		System.out.println("Horsepower: " + horsePower + ", Sponsor: " + sponsor + ", Type of Tyre is: " + typeOfTyres);
	}
}

class AdminAccount extends Account {
	private String rootUsername;

	public String getRootUsername() {
		return rootUsername;
	}

	public void setRootUsername(String rootUsername) {
		this.rootUsername = rootUsername;
	}
}

class Account {
	private static boolean flag;
	private String username;
	private String email;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		Account.flag = flag;
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
