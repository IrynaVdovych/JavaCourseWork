package com.example.db;
import com.example.auto.BaseAuto;
import com.example.taxopark.Taxopark;

import java.sql.*;
import java.util.ArrayList;

public class SqLite {

    private Connection connect() {
        String dbPath = "/Users/irynavdovych/Desktop/taxopark.db";
        //String dbPath = "src/main/resources/taxopark.db";
        // SQLite connection string
        String url = "jdbc:sqlite:" + dbPath;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public Taxopark readTaxopark() {
        Taxopark tp = null;

        String sql = "SELECT name FROM taxopark";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                String name = rs.getString("name");
                tp = new Taxopark(name);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tp;
    }

    public void readAuto(Taxopark tp) {

        String sql = "SELECT type, brand, model,yearOfRelease, regNumber, initialPrice, uof, avgVelocity FROM auto";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                String carType = rs.getString("type").toLowerCase();
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                String regNumber = rs.getString("regNumber");
                int year = rs.getInt("yearOfRelease");
                int price = rs.getInt("initialPrice");
                double uof = rs.getDouble("uof");
                int avgVel = rs.getInt("avgVelocity");

                tp.addAuto(carType, brand, model, regNumber, year, price, uof, avgVel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveAll(Taxopark tp) {
        String sql = "DELETE FROM auto";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        sql = "DELETE FROM taxopark";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        sql = "INSERT INTO taxopark(name) VALUES(?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tp.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        ArrayList<BaseAuto> autoList = tp.autoArrayList();
        for (BaseAuto auto : autoList) {
            sql = "INSERT INTO auto(type, brand, model,yearOfRelease, regNumber, initialPrice, uof, avgVelocity) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, auto.getType());
                pstmt.setString(2, auto.getBrand());
                pstmt.setString(3, auto.getModel());
                pstmt.setInt(4, auto.getYearOfRelease());
                pstmt.setString(5, auto.getRegNumber());
                pstmt.setInt(6, (int) auto.getInitialPrice());
                pstmt.setDouble(7, auto.getUof());
                pstmt.setInt(8, auto.getAvgVelocity());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
