
package graZWynikami1;

public class Wynik {

    public static int counter = 0;
    private String imie;
    private String data;
    private int liczbaProb;
    private int ranking;

    public Wynik(String imie, String data, int liczbagadniec) {
        this.imie = imie;
        this.data = data;
        this.liczbaProb = liczbagadniec;
    }
    public Wynik(String imie, String data, int liczbagadniec, int ranking) {
        this.imie = imie;
        this.data = data;
        this.liczbaProb = liczbagadniec;
        this.ranking=ranking;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    
    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Wynik.counter = counter;
    }

    public int getLiczbaProb() {
        return liczbaProb;
    }

    public void setLiczbaProb(int liczbaProb) {
        this.liczbaProb = liczbaProb;
    }

    public Wynik() {
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getLiczbagadniec() {
        return liczbaProb;
    }

    public void setLiczbagadniec(int liczbagadniec) {
        this.liczbaProb = liczbagadniec;
    }

    @Override
    public String toString() {
        return "Wynik{" + "imie=" + imie + ", data=" + data + ", liczbagadniec=" + liczbaProb + '}';
    }
            
}
