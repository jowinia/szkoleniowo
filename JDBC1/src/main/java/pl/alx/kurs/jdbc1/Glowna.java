package pl.alx.kurs.jdbc1;

import java.io.Closeable;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Glowna {

    public static void main(String[] args) throws SQLException {
        File plik = new File("baza.db");
        if (!plik.exists()) {
            try (Connection polaczenie = DriverManager.getConnection("jdbc:sqlite:baza.db");
                    Statement createTable = polaczenie.createStatement()) {
                createTable.executeUpdate("CREATE TABLE `pracownicy` (\n"
                        + "	`id`	INTEGER,\n"
                        + "	`imie`	TEXT,\n"
                        + "	`waga`	INTEGER,\n"
                        + "	`wzrost`	INTEGER,\n"
                        + "	PRIMARY KEY(`id`)\n"
                        + ");");
            }
        }

        try (
                Connection polaczenie = DriverManager.getConnection("jdbc:sqlite:baza.db");
                PreparedStatement zapytanieSelect = polaczenie.prepareStatement("select * from pracownicy where imie = ?");
                PreparedStatement zapytanieInsert = polaczenie.prepareStatement("insert into pracownicy (imie, waga, wzrost) values (?, ?, ?)")) {
            String imieDoWstawienia = JOptionPane.showInputDialog("podaj imię nowego pracownika");
            String wagaDoWstawienia = JOptionPane.showInputDialog("podaj wagę nowego pracownika");
            String wzrostDoWstawienia = JOptionPane.showInputDialog("podaj wzrost nowego pracownika");
            zapytanieInsert.setString(1, imieDoWstawienia);
            zapytanieInsert.setString(2, wagaDoWstawienia);
            zapytanieInsert.setString(3, wzrostDoWstawienia);
            zapytanieInsert.executeUpdate();

            String szukaneImie = JOptionPane.showInputDialog("podaj szukane imię");
            zapytanieSelect.setString(1, szukaneImie);
            ResultSet wynik = zapytanieSelect.executeQuery();
            while (wynik.next()) {
                String imie = wynik.getString("imie");
                int waga = wynik.getInt("waga");
                int wzrost = wynik.getInt("wzrost");
                System.out.println(imie + " " + " " + waga + " " + wzrost);
            }
        }
    }
}
