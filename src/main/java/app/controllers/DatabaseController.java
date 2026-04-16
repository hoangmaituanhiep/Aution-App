package app.controllers;

import app.functions.*;

import java.sql.*;

public class DatabaseController {
    private final String itemsPath = "jdbc:sqlite:database/items.db";

    private String createTable = "CREATE TABLE IF NOT EXIST items (name, startingPrice REAL NOT NULL, detail)";
    private String insertSql = "INSERT INTO items(name, startingPrice, detail) VALUES(?, ?, ?)";

    DatabaseController() {
        try (Connection connection = DriverManager.getConnection(itemsPath); 
            Statement status = connection.createStatement()) {
                status.execute(createTable);
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertDB(Item item) {
        try (Connection connection = DriverManager.getConnection(itemsPath);
            PreparedStatement add = connection.prepareStatement(insertSql)) {
                String itemName = item.getName();
                double itemStartingPrice = item.getStartingPrice();
                String itemDetail = item.getDetail();

                add.setString(1, itemName);
                add.setDouble(2, itemStartingPrice);
                add.setString(3, itemDetail);

                return true;
            }
        catch (SQLException e) {
            if (e.getMessage() != null && e.getMessage().contains("NULL")) {
                System.out.println("Please add starting value");
            }
            else {
                e.printStackTrace();
            }
            return false;
        }
    }
}
