package  com.pentago;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pentago.ComputerMoves.AdvancingColumnMove;
import com.pentago.ComputerMoves.AdvancingDiagnalMove;
import com.pentago.ComputerMoves.AdvancingMove;
import com.pentago.ComputerMoves.AdvancingRowMove;
import com.pentago.ComputerMoves.CenterMove;
import com.pentago.ComputerMoves.ColumnCompliterMove;
import com.pentago.ComputerMoves.ComputerMoves;
import com.pentago.ComputerMoves.ImmediateThreatMove;
import com.pentago.ComputerMoves.ImmediateWinMove;
import com.pentago.ComputerMoves.RotateLoseMove;
import com.pentago.ComputerMoves.RotateWinMove;
import com.pentago.ComputerMoves.RowCompliterMove;
import com.pentago.ComputerMoves.ThreeCompliterMove;
import com.pentago.ComputerRotations.BeneficialColumnRotation;
import com.pentago.ComputerRotations.BeneficialDiagnaRotation;
import com.pentago.ComputerRotations.BeneficialRotation;
import com.pentago.ComputerRotations.BeneficialRowRotation;
import com.pentago.ComputerRotations.ComputerRotations;
import com.pentago.ComputerRotations.LosingInNextMoveRotation;
import com.pentago.ComputerRotations.LosingInNextTurnRotation;
import com.pentago.ComputerRotations.RandomRotation;
import com.pentago.ComputerRotations.ThreatRotation;
import com.pentago.ComputerRotations.WinningRotation;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ComputerMoves imm_win = new ImmediateWinMove();
        ComputerMoves rotate_win = new RotateWinMove();
        ComputerMoves imm_threat = new ImmediateThreatMove();
        ComputerMoves rotate_threat = new RotateLoseMove();
        ComputerMoves center_move = new CenterMove();
        ComputerMoves row_complite = new RowCompliterMove();
        ComputerMoves col_complite = new ColumnCompliterMove();
        ComputerMoves[] three_complite_list = {row_complite, col_complite};
        ComputerMoves three_complite = new ThreeCompliterMove(three_complite_list);
        ComputerMoves row_adv = new AdvancingRowMove();
        ComputerMoves col_adv = new AdvancingColumnMove();
        ComputerMoves diag_adv = new AdvancingDiagnalMove();
        ComputerMoves[] adv_move_list = {row_adv, col_adv, diag_adv};
        ComputerMoves adv_move = new AdvancingMove(adv_move_list);
        ComputerMoves[] ordered_computer_moves = {imm_win, rotate_win, imm_threat, rotate_threat, center_move, three_complite, adv_move};

        ComputerRotations win_rot = new WinningRotation();
        ComputerRotations threat_rot = new ThreatRotation();
        ComputerRotations threat_after_move = new LosingInNextMoveRotation();
        ComputerRotations threat_after_turn = new LosingInNextTurnRotation();
        ComputerRotations ben_row_rot = new BeneficialRowRotation();
        ComputerRotations ben_col_rot = new BeneficialColumnRotation();
        ComputerRotations ben_diag_rot = new BeneficialDiagnaRotation();
        ComputerRotations[] ben_rot_list = {ben_row_rot, ben_col_rot, ben_diag_rot};
        ComputerRotations ben_rot = new BeneficialRotation(ben_rot_list);
        ComputerRotations rand_rot = new RandomRotation();
        ComputerRotations[] ordered_computer_rotations = {win_rot, threat_rot, threat_after_move, threat_after_turn, ben_rot, rand_rot};

        PentagoBoard board = new PentagoBoard();
        PentagoComp computre = new PentagoComp(board, ordered_computer_rotations, ordered_computer_moves);
        PentagoGameController game = new PentagoGameController(false, board, computre);
        PentagoGUI gui = new PentagoGUI(game);

    }
}


