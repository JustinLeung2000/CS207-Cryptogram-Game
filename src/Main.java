import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Player p = new Player("Bob", 0, 0, 0, 0);
        GameOld g = new GameOld(p);
        g.playGame();
    }
}
