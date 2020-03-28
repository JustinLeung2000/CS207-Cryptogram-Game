import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        String playersFile = "resources/Players.csv";
        String cryptogramsFile = "resources/Cryptograms.csv";
        String phraseFile = "resources/Phrases.txt";
//        String playersFile = "resources/TestPlayers.csv";
//        String cryptogramsFile = "resources/TestCryptograms.csv";
//        String phraseFile = "resources/TestPhrases.txt";
        Player p = new Player("", 0, 0, 0, 0);
        Game g = new Game(p, playersFile, cryptogramsFile, phraseFile);
        g.playGame();
    }
}
