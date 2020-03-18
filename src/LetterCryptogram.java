import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class LetterCryptogram extends Cryptogram {

    public LetterCryptogram(String phrase) {
        this.phrase = phrase;
        cryptogramAlphabet = generateMapping();
        encrypted = encrypt(this.phrase);
    }

    public LetterCryptogram() {
        this.phrase = "ERROR";

    }

    public LetterCryptogram(String phrase, String encrypted) {
        this.phrase = phrase;
        this.encrypted = encrypted;

    }

    public char getPlainLetter(char cryptoLetter) {
        return '-';
    }

    public String getEncrypted() {
        return encrypted;
    }

    public String getPhrase() {
        return phrase;
    }

    public HashMap<Character, String> getCryptogramAlphabet(){
        return cryptogramAlphabet;
    }


    protected HashMap<Character, String> generateMapping() {
        HashMap<Character, String> alphabetMap = new HashMap<Character, String>();
        ArrayList<Character> plainAlphabet = populateAlphabet();
        Random random = new Random();
        ArrayList<String> sacrifice = populateMapped();
        int i=0;
        while (i<plainAlphabet.size()) {
            Character currLetter = plainAlphabet.get(i);
            int randIndx = random.nextInt(sacrifice.size());
            if(sacrifice.get(randIndx).charAt(0) != currLetter){
                alphabetMap.put(currLetter, sacrifice.get(randIndx));
                sacrifice.remove(randIndx);
                i++;
            }
        }
        return alphabetMap;
    }

    private ArrayList<String> populateMapped() {
        Character curr = 'A';
        ArrayList<String> alphabet = new ArrayList<String>();
        for(int i = 0; i<26; i++){
            alphabet.add(curr.toString());
            curr++;
        }
        return alphabet;
    }

    public String encrypt(String inputPhrase) {
        String encrypted = "";
        for(int i = 0; i<inputPhrase.length(); i++){
            try{
                String nextLetter = cryptogramAlphabet.get(inputPhrase.charAt(i));
                encrypted = encrypted + nextLetter + " ";
            }
            catch (NullPointerException n){
                encrypted = encrypted + " ";
            }
        }
        return encrypted;
    }

}
