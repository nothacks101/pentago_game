package  com.pentago;
import com.pentago.ComputerMoves.*;
import com.pentago.View.PentagoGUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ComputerMoves rotate_win = new RotateWinMove();
        ComputerMoves rotate_threat = new RotateLoseMove();
        ComputerMoves center_move = new CenterMove();
        ComputerMoves row_complete = new RowCompleterMove();
        ComputerMoves col_complete = new ColumnCompleterMove();
        ComputerMoves[] three_complete_list = {row_complete, col_complete};
        ComputerMoves three_complete = new ThreeCompleterMove(three_complete_list);
        ComputerMoves move_evaluator = new RotateEvaluatedMove();
        ComputerMoves[] ordered_computer_moves = {rotate_win, rotate_threat, center_move, three_complete, move_evaluator};

        PentagoBoard board = new PentagoBoard();
        PentagoComp computer = new PentagoComp(ordered_computer_moves);
        PentagoGameController controller =
                new PentagoGameController(true, board, computer, null);
        PentagoGUI gui = new PentagoGUI(controller);
        controller.setView(gui);
        controller.startGame();
    }
}


