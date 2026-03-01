package  com.pentago;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("check");
        logger.debug("check");
        PentagoGameController game = new PentagoGameController(false);
        PentagoGUI gui = new PentagoGUI(game);

    }
}

