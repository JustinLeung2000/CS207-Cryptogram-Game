import java.util.ArrayList;
import java.util.HashMap;

public abstract class Cryptogram {

    protected String phrase; //phrase passed into cryptogram
    protected String encrypted;
    protected HashMap<Character, Character> cryptogramAlphabet;

    /*temporary*/
    public Cryptogram() {
    }

    public void getFrequencies(){

    }

    public abstract String getEncrypted();

    public abstract String getPhrase();

    public abstract HashMap<Character, Character> getCryptogramAlphabet();

//    }
//    protected abstract HashMap<Character, Character> generateMapping();
//
//    public abstract ArrayList<Character> populateAlphabet();
//
//    public String getPhrase() {
//        return phrase;
//    }
//
//    public void setPhrase(String phrase) {
//        this.phrase = phrase;
//    }

}
