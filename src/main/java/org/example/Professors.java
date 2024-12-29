package org.example;

import java.sql.*;
import java.util.Scanner;

public class Professors {
    public static void readProfessors(Connection con) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM преподаватель");
        ResultSet rs = preparedStatement.executeQuery();

        StringBuilder response = new StringBuilder();
        while(rs.next()) {
            response.append("имя_преподавателя: ");
            response.append(rs.getString(1));

            response.append(" фамилия_преподавателя: ");
            response.append(rs.getString(2));

            response.append(" научная_степень: ");
            response.append(rs.getString(3));

            response.append(" отчество_преподавателя: ");
            response.append(rs.getString(4));

            response.append(" кафедра: ");
            response.append(rs.getString(5));

            response.append(" есть_ли_кураторство: ");
            response.append(rs.getString(6));

            response.append(" номер_телефона: ");
            response.append(rs.getString(7));

            response.append(" электронная_почта: ");
            response.append(rs.getString(8));

            response.append("\n");
        }
        System.out.println(response);
    }

    public static void insertProfessors(Connection con) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO преподаватель (имя_преподавателя, фамилия_преподавателя, научная_степень, отчество_преподавателя," +
                " кафедра, есть_ли_кураторство, номер_телефона, электронная_почта) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        Scanner sc = new Scanner(System.in);
        String bufferString;

        System.out.print("Введите имя преподавателя: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(1, bufferString);

        System.out.print("Введите фамилию преподавателя: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(2, bufferString);

        System.out.print("Введите научную степень: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(3, bufferString);

        System.out.print("Введите отчество преподавателя: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(4, bufferString);

        System.out.print("Введите кафедру: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(5, bufferString);

        System.out.print("Введите есть ли кураторство: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(6, bufferString);

        System.out.print("Введите номер телефона: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(7, bufferString);

        System.out.print("Введите электронную почту: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(8, bufferString);


        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Добавлена " + rowsAffected + " запись");
    }

    public static void updateProfessors(Connection con) throws SQLException {

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

        PreparedStatement preparedStatement = con.prepareStatement("UPDATE преподаватель SET " + column1 + " " + operator1 + " ? WHERE " + column2 + " " + operator2 + " ?;");

        preparedStatement.setObject(1, value1);
        preparedStatement.setObject(2, value2);

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Обновлено " + rowsAffected + " строк");
    }

    public static void deleteProfessors(Connection con) throws SQLException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите атрибут: ");
        String column = sc.nextLine();

        System.out.println("Введите оператор (<, >, =, <= и т.д.):");
        String operator = sc.nextLine();

        System.out.println("Введите значение, с которым сравниваем");
        String value = sc.nextLine();

        PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM преподаватель WHERE " + column + " " + operator + " ?;");

        preparedStatement.setString(1, value);

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Удалено " + rowsAffected + " строк");
    }
}