import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class NumberCryptogram extends Cryptogram {

    public NumberCryptogram(String phrase) {
        this.phrase = phrase;
        cryptogramAlphabet = generateMapping();
        encrypted = encrypt(this.phrase);
    }

    public NumberCryptogram(String phrase, String encrypted) {
        this.phrase = phrase;
        this.encrypted = encrypted;

    }

    @Override
    public void getFrequencies() {
        super.getFrequencies();
    }

    @Override
    public String getEncrypted() {
        return encrypted;
    }

    @Override
    public String getPhrase() {
        return phrase;
    }

    @Override
    public HashMap<Character, String> getCryptogramAlphabet() {
        return null;
    }

    protected HashMap<Character, String> generateMapping() {
        HashMap<Character, String> alphabetMap = new HashMap<Character, String>();
        ArrayList<Character> plainAlphabet = populateAlphabet();
        Random random = new Random();
        ArrayList<String> sacrifice = populateNumberAlphabet();
        for (int i=0; i<plainAlphabet.size(); i++) {
            Character currLetter = plainAlphabet.get(i);
            int randIndx = random.nextInt(sacrifice.size());
            alphabetMap.put(currLetter, sacrifice.get(randIndx));
            sacrifice.remove(randIndx);
        }
        return alphabetMap;
    }

    private ArrayList<String> populateNumberAlphabet() {
        ArrayList<String> numbers = new ArrayList<String>();
        for(Integer i=0; i<26; i++){
            numbers.add(i.toString());
        }
        return numbers;
    }


    public String encrypt(String inputPhrase) {
        String encrypted = "";
        for(int i = 0; i<inputPhrase.length(); i++){
            try{
                String nextLetter = cryptogramAlphabet.get(inputPhrase.charAt(i));
                encrypted = encrypted + nextLetter;
                encrypted += " ";
            }
            catch (NullPointerException n){
                encrypted = encrypted + " ";
            }
        }
        return encrypted;
    }

    public void getPlainLetter(int cryptoValue) {

    }
}
