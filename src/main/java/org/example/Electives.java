package org.example;

import java.sql.*;
import java.util.Scanner;

public class Electives {
    public static void readElectives(Connection con) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Факультатив");
        ResultSet rs = preparedStatement.executeQuery();

        StringBuilder response = new StringBuilder();
        while(rs.next()) {
            response.append("имя_преподавателя: ");
            response.append(rs.getString(1));

            response.append(" название_факультатива: ");
            response.append(rs.getString(2));

            response.append(" научная_степень: ");
            response.append(rs.getString(3));

            response.append(" фамилия_преподавателя: ");
            response.append(rs.getString(4));

            response.append(" вид_факультатива: ");
            response.append(rs.getString(5));

            response.append(" количество_часов: ");
            response.append(rs.getInt(6));

            response.append("\n");
        }
        System.out.println(response);
    }

    public static void insertElectives(Connection con) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO факультатив (имя_преподавателя, название_факультатива, научная_степень, фамилия_преподавателя, вид_факультатива, количество_часов) VALUES (?, ?, ?, ?, ?, ?)");
        Scanner sc = new Scanner(System.in);
        String bufferString;
        int bufferInt;

        System.out.print("Введите имя_преподавателя: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(1, bufferString);

        System.out.print("Введите название факультатива: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(2, bufferString);

        System.out.print("Введите научную степень: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(3, bufferString);

        System.out.print("Введите фамилию преподавателя: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(4, bufferString);

        System.out.print("Введите вид факультатива: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(5, bufferString);

        System.out.print("Введите количество часов: ");
        bufferInt = sc.nextInt();
        preparedStatement.setInt(6, bufferInt);

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Добавлена " + rowsAffected + " запись");
    }

    public static void updateElectives(Connection con) throws SQLException {

        Scanner sc = new Scanner(System.in);

        System.out.println("SET");

        System.out.println("Введите атрибут: ");
        String column1 = sc.nextLine();

        System.out.println("Введите оператор (<, >, =, <= и т.д.):");
        String operator1 = sc.nextLine();

        System.out.println("Введите значение, которое присваиваем");
        String value1 = sc.nextLine();

        System.out.println("WHERE");

        System.out.println("Введите сравниваемый атрибут: ");
        String column2 = sc.nextLine();

        System.out.println("Введите оператор (<, >, =, <= и т.д.):");
        String operator2 = sc.nextLine();

        System.out.println("Введите значение, с которым сравниваем:");
        String value2 = sc.nextLine();

        PreparedStatement preparedStatement = con.prepareStatement("UPDATE факультатив SET " + column1 + operator1 + " ? WHERE " + column2 + " " +operator2 + " ?;");

        preparedStatement.setString(1, value1);
        preparedStatement.setString(2, value2);

        if(column1.equals("количество_часов"))
            preparedStatement.setInt(1, Integer.parseInt(value1));
        if(column2.equals("количество_часов"))
            preparedStatement.setInt(2, Integer.parseInt(value2));

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Обновлено " + rowsAffected + " строк");
    }

    public static void deleteElectives(Connection con) throws SQLException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите атрибут: ");
        String column = sc.nextLine();

        System.out.println("Введите оператор (<, >, =, <= и т.д.):");
        String operator = sc.nextLine();

        System.out.println("Введите значение, с которым сравниваем");
        String value = sc.nextLine();

        PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM факультатив WHERE " + column + " " + operator + " ?;");

        preparedStatement.setString(1, value);

        if(column.equals("количество_часов"))
            preparedStatement.setInt(1, Integer.parseInt(value));

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Удалено " + rowsAffected + " строк");
    }
}