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
public class Knight extends Piece {

    public Knight(boolean isWhite, ChessBoard board) {

        this.setIsWhite(isWhite);
        this.setHasPiece(true);
        this.setSelected(false);
        this.setBoard(board);
        if (isWhite) {
            this.setIcon(new ImageIcon(getClass().getResource("WhiteKnight.png")));
        } else {
            this.setIcon(new ImageIcon(getClass().getResource("BlackKnight.png")));
        }

    }

    @Override
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>(); // Arraylist of integers for saving possible moves
        if (this.getBoard().getAllSquares().indexOf(this) <= 7) {
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) + 8) < 56) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 15);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) - 1) % 8 != 0 && this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 6);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) + 1) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) + 8) < 56) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 17);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) + 2) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) - 7) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 10);
            }
        } else if (this.getBoard().getAllSquares().indexOf(this) >= 56) {
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) - 8) > 7) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 17);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) - 1) % 8 != 0 && this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 10);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) - 7) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) - 8) > 7) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 15);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) - 6) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) - 7) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 6);
            }
        } else {
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) + 8) < 56) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 15);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) - 1) % 8 != 0 && this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 6);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) + 1) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) + 8) < 56) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 17);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) + 2) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) - 7) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + 10);
            }
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) - 8) > 7) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 17);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) - 1) % 8 != 0 && this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 10);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) - 7) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) - 8) > 7) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 15);
            }
            if ((this.getBoard().getAllSquares().indexOf(this) - 6) % 8 != 0 && (this.getBoard().getAllSquares().indexOf(this) - 7) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) - 6);
            }

        }
        return moves;
    }
}
