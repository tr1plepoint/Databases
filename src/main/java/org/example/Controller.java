package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.Electives.*;
import static org.example.Professors.*;
import static org.example.Students.*;
import static org.example.Attendance.*;


public class Controller {


    public Controller() throws SQLException {
        Properties prop = readProperties();

        String url = prop.getProperty("db.url");
        String user = prop.getProperty("db.user");
        String password = prop.getProperty("db.password");

        Connection connection = DriverManager.getConnection(url, user, password);
        Scanner sc = new Scanner(System.in);

        String response;

        while(true) {
            System.out.println("С какой таблицей будем работать?\n\t1. Факультатив\n\t2. Преподаватель\n\t3. Студент\n\t4. Посещаемость");
            response = sc.nextLine();
            System.out.println("Что будем делать?\n\t1. Считаем таблицу\n\t2. Добавим записи в таблицу\n\t3. Обновим записи в таблице\n\t4. Удалим записи из таблицы\n\t5. Назад");
            response = response + sc.nextLine();

            switch (response) {
                case ("11"):
                    readElectives(connection);
                    break;
                case ("12"):
                    insertElectives(connection);
                    break;
                case ("13"):
                    updateElectives(connection);
                    break;
                case ("14"):
                    deleteElectives(connection);
                    break;
                case ("21"):
                    readProfessors(connection);
                    break;
                case ("22"):
                    insertProfessors(connection);
                    break;
                case ("23"):
                    updateProfessors(connection);
                    break;
                case ("24"):
                    deleteProfessors(connection);
                    break;
                case ("31"):
                    readStudents(connection);
                    break;
                case ("32"):
                    insertStudents(connection);
                    break;
                case ("33"):
                    updateStudents(connection);
                    break;
                case ("34"):
                    deleteStudents(connection);
                    break;
                case ("41"):
                    readAttendance(connection);
                    break;
                case ("42"):
                    insertAttendance(connection);
                    break;
                case ("43"):
                    updateAttendance(connection);
                    break;
                case ("44"):
                    deleteAttendance(connection);
                    break;
                default:
                    break;
            }
        }
    }

    public static Properties readProperties() {

        Properties props = new Properties();
        Path myPath = Paths.get("src/main/resources/database.properties");

        try {
            BufferedReader bf = Files.newBufferedReader(myPath,
                    StandardCharsets.UTF_8);

            props.load(bf);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return props;
    }
}
