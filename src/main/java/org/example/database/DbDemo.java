package org.example.database;

import org.h2.tools.Server;

import java.sql.SQLException;

import static java.lang.System.exit;

public class DbDemo {
	private static final String DB_URL = "jdbc:h2:mem:sample";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";

	private Server h2Server;

	public static void main(String[] args) throws InterruptedException {
		DbDemo demo = new DbDemo();
		demo.startH2Server();
		// Create table
		// Insert Data
		// SQL Commands
		Thread.sleep(3000);

		demo.stopH2Server();
	}

	public void startH2Server() {
		try {
			h2Server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
			h2Server.start();
			System.out.println("H2 Database server is now accepting connections.");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			exit(-1);
		}
	}

	public void stopH2Server() {
		if (h2Server == null) {
			return;
		}

		if (h2Server.isRunning(true)) {
			h2Server.stop();
			h2Server.shutdown();
			System.out.println("H2 Database server has been shutdown.");
		}
	}
}
