
package graZWynikami1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Bazodanowa {
    public void zapiszDoBazy(Connection con) {try {
        //dokonczyc
        
        Statement insert = con.createStatement();      
        } catch (SQLException ex) {
            
        }   
    }
    public static void createFile(String sciezka) throws IOException {
        
        Path path = Paths.get(sciezka);//tutaj ma być też nazwa pliku!!
        Files.createDirectories(path.getParent());
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            JOptionPane.showMessageDialog(null, "Nie mozna zapisać pliku. Plik juz istnieje");
        } catch (IOException f) {
            JOptionPane.showMessageDialog(null, "Nie mozna zapisać pliku z logami");
        }
    }
    
    public static Wynik stworzNowyWynik(String imie, int liczbaKlikniec ) {
        String data = Liczbowa.zwrocSformatowanaDate("yy-MM-dd");
        return new Wynik(imie, data, liczbaKlikniec);
    }
            
    public static void stworzBazeDanychSQLite(String baza, String statement, String sciezkaDoErrorLogow, Connection con){
        try (Statement createTable = con.createStatement()) {
             createTable.executeUpdate(statement);
        } catch (SQLException sql1) {
            JOptionPane.showMessageDialog(null, "UPs, coś się zjebawszy");
            try {
                String now = Liczbowa.zwrocSformatowanaDate("yy-MM-dd hh:mm");
                String sciezka = sciezkaDoErrorLogow+now;
                createFile(sciezka);
                try (PrintStream ps = new PrintStream(new FileOutputStream(sciezka, true))){}
            } catch (IOException b) {
                JOptionPane.showMessageDialog(null, "Nie mozna zapisać pliku z logami");
            }
        }
    }
    
    public static void zapiszLogBledow(String sciezkaDoKatalogu){
        JOptionPane.showMessageDialog(null, "UPs, coś się zjebawszy");
            try {
                String now = Liczbowa.zwrocSformatowanaDate("yy-MM-dd hh:mm");
                String sciezka = sciezkaDoKatalogu+"\\Log "+now;
                Bazodanowa.createFile(sciezka);
                try (PrintStream ps = new PrintStream(new FileOutputStream(sciezka, true))){}
            } catch (IOException b) {
                JOptionPane.showMessageDialog(null, "Nie mozna zapisać pliku z logami");
            }
    }
    //czy to zadziała, jak baza będzie pusta?? bo jak wywali nulla, to słabo.
    public static ResultSet pobierzWynikizBazy(String nazwaPliku, String statement,Statement create,String sciezkaDoErrorLogow,Connection con) {
        File baza = new File(nazwaPliku);
        Statement st;
        ResultSet rs=null;
        if (!baza.exists()) { 
            stworzBazeDanychSQLite(nazwaPliku, create, sciezkaDoErrorLogow, con);
        }
        //czy to nie wywali się, jeśli baza będzie pusta? 
         try {
             st = con.createStatement();
             rs = st.executeQuery(statement);
                 
         } catch (SQLException e) {
               zapiszLogBledow(sciezkaDoErrorLogow);
           }
          return rs;
     
         }
    //ta metoda tak narawde powinna byc w interfejsie chyba
    
}
