import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class NumberCryptogram extends Cryptogram {

    public NumberCryptogram(String phrase) {
        this.phrase = phrase;
        cryptogramAlphabet = generateMapping();
        encrypted = encrypt(this.phrase);
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
    public HashMap<Character, Character> getCryptogramAlphabet() {
        return null;
    }

    protected HashMap<Character, Character> generateMapping() {
        HashMap<Character, Character> alphabetMap = new HashMap<Character, Character>();
        ArrayList<Character> plainAlphabet = populateAlphabet();
        Random random = new Random();
        ArrayList<Character> sacrifice = populateAlphabet();
        for (int i=0; i<plainAlphabet.size(); i++) {
            Character currLetter = plainAlphabet.get(i);
            int randIndx = random.nextInt(sacrifice.size());
            alphabetMap.put(currLetter, sacrifice.get(randIndx));
            sacrifice.remove(randIndx);
        }
        return alphabetMap;
    }

    public ArrayList<Character> populateAlphabet(){
        Character curr = '0';
        ArrayList<Character> alphabet = new ArrayList<Character>();
        for(int i = 0; i<10; i++){
            alphabet.add(curr);
            curr++;
        }
        return alphabet;
    }

    public String encrypt(String inputPhrase) {
        String encrypted = "";
        for(int i = 0; i<inputPhrase.length(); i++){
            encrypted = encrypted + cryptogramAlphabet.get(inputPhrase.charAt(i));
        }
        return encrypted;
    }

    public void getPlainLetter(int cryptoValue) {

    }
}
