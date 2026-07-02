package controller;

import config.DatabaseInitializer;
import menu.MainMenu;
import config.DBConnection;

public class Main {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Vehicle Rental System ");
        System.out.println("=================================");

        DatabaseInitializer.initializeDatabase();

        MainMenu menu = new MainMenu();

        menu.start();
               DBConnection.closeConnection();

    }

}

       