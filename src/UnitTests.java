import org.junit.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;

public class UnitTests {

    String playersFile = "resources/TestPlayers.csv";
    String cryptogramsFile = "resources/TestCryptograms.csv";
    String phraseFile = "resources/TestPhrases.txt";

    @Before
    public void  beforeEach(){
    }

    @Test
    public void testLetterEntry() throws IOException {
        Player p = new Player("Bob", 0, 0, 0, 0);
        Game g = new Game(p, playersFile, cryptogramsFile, phraseFile);
        Cryptogram c = new LetterCryptogram("ABCCBA");
        String currentAnswer  = "- - - - - - ";
        char selectedChar = c.getEncrypted().charAt(0);
        String s = "" + selectedChar;
        Character changeToChar = 'Z';

        String result = g.enterLetter(c, currentAnswer, s, changeToChar);
        Assert.assertThat(result, containsString("Z - - - - Z "));
    }

    @Test
    public void testCheckAnswer() {
        Player p = new Player("Bob", 0, 0, 0, 0);
        Game g = new Game(p, playersFile, cryptogramsFile, phraseFile);
        Cryptogram c = new LetterCryptogram("ABCCBA");
        String currentAnswer  = "A B C C B A ";

        Assert.assertTrue(g.checkAnswer(currentAnswer, c));
        Assert.assertEquals(1, p.getTotalGuesses());
    }

}
