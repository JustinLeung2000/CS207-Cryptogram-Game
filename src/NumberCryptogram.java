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
        cryptogramAlphabet = generateMappingFromSave(phrase, encrypted);
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

    private HashMap<Character, String> generateMappingFromSave(String phrase, String encrypted) {
        HashMap<Character, String> alphabetMap = new HashMap<Character, String>();
        ArrayList<Character> plainAlphabet = populateAlphabet();
        Random random = new Random();
        ArrayList<String> sacrifice = populateNumberAlphabet();
        int i=0;
        String[] encryptedChars = encrypted.split("\\s+");
        while (i<plainAlphabet.size()) {
            Character currLetter = plainAlphabet.get(i);
            if(phrase.contains(currLetter.toString())){
                alphabetMap.put(currLetter, encryptedChars[phrase.indexOf(currLetter)]);
                sacrifice.remove(encryptedChars[phrase.indexOf(currLetter)]);
                i++;
            }
            else {
                int randIndx = random.nextInt(sacrifice.size());
                if (sacrifice.get(randIndx).charAt(0) != currLetter) {
                    alphabetMap.put(currLetter, sacrifice.get(randIndx));
                    sacrifice.remove(randIndx);
                    i++;
                }
            }
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
                nextLetter.equals(nextLetter);
                encrypted = encrypted + nextLetter+" ";
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
