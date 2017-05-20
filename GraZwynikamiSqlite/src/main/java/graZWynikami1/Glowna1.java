
package graZWynikami1;

import static graZWynikami1.Bazodanowa.zapiszLogBledow;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jowik
 */
public class Glowna1 {
    public static List<Wynik> zwrocWynikiDoListy(ResultSet wyniki, String sciezkaDoKatalogu){
        List<Wynik> listaWynikow = new ArrayList<>();
        try {
            while(wyniki.next()){
                     String imie = wyniki.getString("Imię");
                     String data = wyniki.getString("Data");
                     int liczbaProb= wyniki.getInt("Liczba prób");
                     int ranking = wyniki.getInt("Ranking");                    
                     listaWynikow.add(new Wynik(imie, data, liczbaProb, ranking));
                 }
        } catch (SQLException e) {
            zapiszLogBledow(sciezkaDoKatalogu);
        }
        return listaWynikow;
    }

    public static void main(String[] args) {
        
    SwingUtilities.invokeLater(
            new Thread(() -> {
                JFrame ramka = new JFrame();
                ramka.setSize(400,300);
                ramka. setDefaultCloseOperation(3);
                JPanel panel = new GraPanelGlowny();
                ramka.setContentPane(panel);
                ramka.setVisible(true);
            }));
    /**/
    
    //i tak trzeba osbłużyć :/ - czy może napisać swój wyjątek?
    String sciezkaDoErrorLogow = "D:\\java\\kurs\\3zjazd\\repo\\JAXB\\GraZwynikamiSqlite\\Error Logs";
    try (Connection con = DriverManager.getConnection("jdbc:sqlite:baza.db")){
            ResultSet rsWyniki = Bazodanowa.pobierzWynikizBazy("bazaWynikow.db","select * from Wyniki","CREATE TABLE `Wyniki` (`Ranking` INTEGER,`Imię` TEXT,`Data` TEXT,`Liczba prób`	INTEGER,PRIMARY KEY(`Ranking`));", sciezkaDoErrorLogow, con);
    } catch (SQLException e){
        Bazodanowa.zapiszLogBledow(sciezkaDoErrorLogow);
        
    }
    }  
}
