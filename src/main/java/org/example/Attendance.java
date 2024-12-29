package org.example;

import java.sql.*;
import java.util.Scanner;

public class Attendance {
    public static void readAttendance(Connection con) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM посещаемость");
        ResultSet rs = preparedStatement.executeQuery();

        StringBuilder response = new StringBuilder();
        while(rs.next()) {
            response.append("название_факультатива: ");
            response.append(rs.getString(1));

            response.append(" имя_преподавателя: ");
            response.append(rs.getString(2));

            response.append(" номер_зачётки: ");
            response.append(rs.getString(3));

            response.append("\n");
        }
        System.out.println(response);
    }

    public static void insertAttendance(Connection con) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO посещаемость (название_факультатива, имя_преподавателя, номер_зачётки) VALUES (?, ?, ?)");
        Scanner sc = new Scanner(System.in);
        String bufferString;

        System.out.print("Введите название факультатива: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(1, bufferString);

        System.out.print("Введите имя преподавателя: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(2, bufferString);

        System.out.print("Введите номер зачётки: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(3, bufferString);

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Добавлена " + rowsAffected + " запись");
    }

    public static void updateAttendance(Connection con) throws SQLException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите атрибут: ");
        String column1 = sc.nextLine();

        System.out.println("Введите оператор (<, >, =, <= и т.д.):");
        String operator1 = sc.nextLine();

        System.out.println("Введите значение, которое присваиваем");
        String value1 = sc.nextLine();

        System.out.println("Введите сравниваемый атрибут: ");
        String column2 = sc.nextLine();

        System.out.println("Введите оператор (<, >, =, <= и т.д.):");
        String operator2 = sc.nextLine();

        System.out.println("Введите значение, с которым сравниваем:");
        String value2 = sc.nextLine();

        PreparedStatement preparedStatement = con.prepareStatement("UPDATE посещаемость SET " + column1 + " " + operator1 + " ? WHERE " + column2 + " " + operator2 + " ?;");

        preparedStatement.setString(1, value1);
        preparedStatement.setString(2, value2);

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Обновлено " + rowsAffected + " строк");
    }

    public static void deleteAttendance(Connection con) throws SQLException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите атрибут: ");
        String column = sc.nextLine();

        System.out.println("Введите оператор (<, >, =, <= и т.д.):");
        String operator = sc.nextLine();

        System.out.println("Введите значение, с которым сравниваем");
        String value = sc.nextLine();

        PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM посещаемость WHERE " + column + " " + operator + " ?;");

        preparedStatement.setString(1, value);

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Удалено " + rowsAffected + " строк");
    }
}