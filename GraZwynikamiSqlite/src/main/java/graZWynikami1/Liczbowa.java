
package graZWynikami1;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


public class Liczbowa {
    
    public static boolean isInt(String liczba) {
        Integer intLiczba=null;
        try {
            intLiczba=Integer.parseInt(liczba);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static String poprosOLiczbe(String pytanie){
        return JOptionPane.showInputDialog(null,pytanie);
    }
        
    public static String sprawdzLiczbe(int random, int liczba){
        String wiadomosc;
        if (liczba<random){
            wiadomosc = "Za mało.";
        } else if (liczba>random) {
            wiadomosc = "Za dużo.";
        } else {
            wiadomosc = "Brawo, to prawidłowa liczba!";
        }
        return wiadomosc;
}
    public static String zwrocSformatowanaDate(String format) {
        SimpleDateFormat formaterDaty = new SimpleDateFormat(format);
        return formaterDaty.format(new Date());
    }
}
