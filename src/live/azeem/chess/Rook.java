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

public class Rook extends Piece {

    public Rook(boolean isWhite, ChessBoard board) {

        this.setIsWhite(isWhite);
        this.setHasPiece(true);
        this.setSelected(false);
        this.setBoard(board);
        if (isWhite) {
            this.setIcon(new ImageIcon(getClass().getResource("WhiteRook.png")));
        } else {
            this.setIcon(new ImageIcon(getClass().getResource("BlackRook.png")));
        }
    }

    @Override
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>(); // Arraylist of integers for saving possible moves
        if (this.getBoard().getAllSquares().indexOf(this) <= 7) {
            for (int i = this.getBoard().getAllSquares().indexOf(this) + 8; i <= 63; i = i + 8) {
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }
        } else if (this.getBoard().getAllSquares().indexOf(this) >= 56) {
            for (int i = this.getBoard().getAllSquares().indexOf(this) - 8; i >= 0; i = i - 8) {
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }
        } else {
            for (int i = this.getBoard().getAllSquares().indexOf(this) + 8; i <= 63; i = i + 8) {
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }
            for (int i = this.getBoard().getAllSquares().indexOf(this) - 8; i >= 0; i = i - 8) {
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }
        }
        if (this.getBoard().getAllSquares().indexOf(this) % 8 == 0 || this.getBoard().getAllSquares().indexOf(this) == 0) {
            for (int i = this.getBoard().getAllSquares().indexOf(this) + 1; (i) % 8 != 0; i++) {
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }
        } else if ((this.getBoard().getAllSquares().indexOf(this) + 1) % 8 == 0 || this.getBoard().getAllSquares().indexOf(this) == 7) {
            for (int i = this.getBoard().getAllSquares().indexOf(this) - 1; (i + 1) % 8 != 0; i--) {
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }
        } else {
            for (int i = this.getBoard().getAllSquares().indexOf(this) + 1; (i) % 8 != 0; i++) {
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }
            for (int i = this.getBoard().getAllSquares().indexOf(this) - 1; (i + 1) % 8 != 0; i--) {
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }

        }

        return moves;
    }
}
