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

    public HashMap<Character, Character> getCryptogramAlphabet(){
        return cryptogramAlphabet;
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
        Character curr = 'A';
        ArrayList<Character> alphabet = new ArrayList<Character>();
        for(int i = 0; i<26; i++){
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

}
