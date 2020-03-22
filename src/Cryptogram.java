import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Cryptogram {

    protected String phrase; //phrase passed into cryptogram
    protected String encrypted;
    protected HashMap<Character, String> cryptogramAlphabet;

    /*temporary*/
    public Cryptogram() {
    }

    public void getFrequencies(){
    }

    public abstract String getEncrypted();

    public abstract String getPhrase();

    public abstract HashMap<Character, String> getCryptogramAlphabet();

    public ArrayList<Character> populateAlphabet(){
        Character curr = 'A';
        ArrayList<Character> alphabet = new ArrayList<Character>();
        for(int i = 0; i<26; i++){
            alphabet.add(curr);
            curr++;
        }
        return alphabet;
    }

    public void saveCryptogram(String playerName, String currentAnswer, char selection) throws IOException{
        File csvName = new File("src/Cryptograms.csv");
        String type;
        String line;
        int lineNum = 0;
        if(selection == '1'){
            type = "Letter";
        }
        else type = "Number";
        if(!csvName.exists()){
            csvName.createNewFile();
        }
        FileReader fr = new FileReader(csvName);
        LineNumberReader csvReader = new LineNumberReader(fr);
        while((line = csvReader.readLine()) != null) {
            String[] cryptoData = line.split(",");
            if (cryptoData[0].compareTo(playerName) == 0) {
                lineNum = csvReader.getLineNumber() - 1;
                List<String> lines = Files.readAllLines(csvName.toPath());
                lines.set(lineNum, (playerName + ',' + type + ',' + phrase + ',' + encrypted + ',' + currentAnswer));
                Files.write(csvName.toPath(), lines);
                return;
            }
        }

        FileWriter csv = new FileWriter(csvName, true);

        csv.append(playerName);
        csv.append(',');
        csv.append(type);
        csv.append(',');
        csv.append(phrase);
        csv.append(',');
        csv.append(encrypted);
        csv.append(',');
        csv.append(currentAnswer);
        csv.append('\n');

        csv.flush();
        csv.close();
    }

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
