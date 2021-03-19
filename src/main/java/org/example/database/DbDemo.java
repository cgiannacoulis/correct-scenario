package org.example.database;

import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static java.lang.System.exit;

public class DbDemo {
	private static final String DB_URL = "jdbc:h2:mem:sample";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";
	private final Properties sqlCommands = new Properties();

	private Server h2Server;

	public static void main(String[] args) throws InterruptedException {
		DbDemo demo = new DbDemo();
		// Load SQL commands
		demo.loadSqlCommands();

		// Start H2 database server
		demo.startH2Server();
		// Register JDBC driver and retrieve a connection
		Connection connection = demo.registerDatabaseDriver();
		// Create table
		demo.createTable(connection);
		// Insert Data
		// SQL Commands

		//Introduce artificial delay
		Thread.sleep(3000);

		// Stop H2 database server
		demo.stopH2Server();
	}

	private void loadSqlCommands() {
		try (InputStream inputStream = DbDemo.class.getClassLoader().getResourceAsStream("sql.properties")) {
			if (inputStream == null) {
				System.err.println("Unable to load SQL commands");
				exit(-1);
			}
			sqlCommands.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startH2Server() {
		try {
			h2Server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
			h2Server.start();
			System.out.println("H2 Database server is now accepting connections.");
		} catch (SQLException throwables) {
			System.err.println("Unable to start H2 database server.");
			throwables.printStackTrace();
			exit(-1);
		}
	}

	private Connection registerDatabaseDriver() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException throwables) {
			System.err.println("Unable to get a connection from H2 database server.");
			throwables.printStackTrace();
			exit(-1);
		}
		return connection;
	}

	private void createTable(Connection connection) {
		try (Statement statement = connection.createStatement()) {
			int resultRows = statement.executeUpdate(sqlCommands.getProperty("create.table.001"));

			System.out.println("Statement returned " + resultRows);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private void stopH2Server() {
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
