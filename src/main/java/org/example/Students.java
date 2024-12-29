package org.example;

import java.sql.*;
import java.util.Scanner;

public class Students {
    public static void readStudents(Connection con) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM студент");
        ResultSet rs = preparedStatement.executeQuery();

        StringBuilder response = new StringBuilder();
        while(rs.next()) {
            response.append("номер_зачётки: ");
            response.append(rs.getString(1));

            response.append(" имя_студента: ");
            response.append(rs.getString(2));

            response.append(" фамилия_студента: ");
            response.append(rs.getString(3));

            response.append(" группа: ");
            response.append(rs.getString(4));

            response.append(" курс: ");
            response.append(rs.getInt(5));

            response.append(" номер_телефона: ");
            response.append(rs.getString(6));

            response.append("\n");
        }
        System.out.println(response);
    }

    public static void insertStudents(Connection con) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO студент (номер_зачётки, имя_студента, фамилия_студента, группа, курс, номер_телефона) VALUES (?, ?, ?, ?, ?, ?)");
        Scanner sc = new Scanner(System.in);
        String bufferString;
        int bufferInt;

        System.out.print("Введите номер зачётки: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(1, bufferString);

        System.out.print("Введите имя студента: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(2, bufferString);

        System.out.print("Введите фамилию студента: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(3, bufferString);

        System.out.print("Введите группу: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(4, bufferString);

        System.out.print("Введите номер телефона: ");
        bufferString = sc.nextLine();
        preparedStatement.setString(6, bufferString);

        System.out.print("Введите курс: ");
        bufferInt = sc.nextInt();
        preparedStatement.setInt(5, bufferInt);

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Добавлена " + rowsAffected + " запись");
    }

    public static void updateStudents(Connection con) throws SQLException {

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

        PreparedStatement preparedStatement = con.prepareStatement("UPDATE студент SET " + column1 + " " + operator1 + " ? WHERE " + column2 + " " + operator2 + " ?;");

        preparedStatement.setString(1, value1);
        preparedStatement.setString(2, value2);

        if(column1.equals("курс"))
            preparedStatement.setInt(1, Integer.parseInt(value1));
        if(column2.equals("курс"))
            preparedStatement.setInt(1, Integer.parseInt(value2));

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Обновлено " + rowsAffected + " строк");
    }

    public static void deleteStudents(Connection con) throws SQLException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите атрибут: ");
        String column = sc.nextLine();

        System.out.println("Введите оператор (<, >, =, <= и т.д.):");
        String operator = sc.nextLine();

        System.out.println("Введите значение, с которым сравниваем");
        String value = sc.nextLine();

        PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM студент WHERE " + column + " " + operator + " ?;");
        preparedStatement.setString(1, value);

        if(column.equals("курс"))
            preparedStatement.setInt(1, Integer.parseInt(value));

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("Удалено " + rowsAffected + " строк");
    }
}
