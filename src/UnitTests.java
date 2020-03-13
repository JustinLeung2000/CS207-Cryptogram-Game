import org.junit.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;

public class UnitTests {

    @Before
    public void  beforeEach(){

    }

//    @Test
//    public void testLeterEntry() throws IOException {
//        Player p = new Player("Bob", 0, 0, 0, 0);
//        Game g = new Game(p);
//        Cryptogram c = new LetterCryptogram("ABCCBA");
//        String currentAnswer  = "------";
//        Character slectedChar = c.getEncrypted().charAt(0);
//        Character changeToChar = 'Z';
//
//        String result = g.enterLetter(c, currentAnswer, slectedChar, changeToChar);
//        Assert.assertThat(result, containsString("Z----Z"));
//    }

//    @Test
//    public void testCheckLetter() throws IOException {
//        Player p = new Player("Bob", 0, 0, 0, 0);
//        Game g = new Game(p);
//        Cryptogram c = new LetterCryptogram("ABCCBA");
//        String currentAnswer  = "ABCCBA";
//
//        Assert.assertThat(true, g.checkAnswer(currentAnswer, c));
//
//    }


}
