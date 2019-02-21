/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package live.azeem.chess;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Azeem Tariq
 */
public class King extends Piece {

    public King(boolean isWhite, ChessBoard board) {

        this.setIsWhite(isWhite);
        this.setHasPiece(true);
        this.setSelected(false);
        this.setBoard(board);
        if (isWhite) {
            this.setIcon(new ImageIcon(getClass().getResource("WhiteKing.png")));
        } else {
            this.setIcon(new ImageIcon(getClass().getResource("BlackKing.png")));
        }

    }

    @Override
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>(); // Arraylist of integers for saving possible moves
        if (this.getBoard().getAllSquares().indexOf(this) <= 7) {
            moves.add(this.getBoard().getAllSquares().indexOf(this) + 8);
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 7);
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 1);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) + 1) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 9);
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 1);
            }
        } else if (this.getBoard().getAllSquares().indexOf(this) >= 56) {
            moves.add(this.getBoard().getAllSquares().indexOf(this) - 8);
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 9);
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 1);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) + 1) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 7);
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 1);
            }
        } else {
            moves.add(this.getBoard().getAllSquares().indexOf(this) + 8);
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 7);
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 1);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) + 1) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 9);
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 1);
            }
            moves.add(this.getBoard().getAllSquares().indexOf(this) - 8);
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 9);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) + 1) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 7);
            }
        }
        return moves;
    }
}
