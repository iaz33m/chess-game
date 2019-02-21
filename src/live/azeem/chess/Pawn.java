
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package live.azeem.chess;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Azeem Tariq
 */
public class Pawn extends Piece {

    final JFrame pawnPromotion;

    public Pawn(boolean isWhite, ChessBoard board) {

        this.setIsWhite(isWhite);
        this.setHasPiece(true);
        this.setSelected(false);
        this.setBoard(board);
        if (isWhite) {
            this.setIcon(new ImageIcon(getClass().getResource("WhitePawn.png")));
        } else {
            this.setIcon(new ImageIcon(getClass().getResource("BlackPawn.png")));
        }
        pawnPromotion = new JFrame();
    }

    @Override
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>(); // Arraylist of integers for saving possible moves
        int int8;
        int int16;
        if (!this.isWhite()) // for black Pieces
        {
            int8 = 8;
            int16 = 16;
        } else // for black pieces 
        {
            int8 = -8;
            int16 = -16;
        }
        if (this.isWhite() && this.getBoard().getAllSquares().indexOf(this) >= 48 && this.getBoard().getAllSquares().indexOf(this) <= 55 || !this.isWhite() && this.getBoard().getAllSquares().indexOf(this) >= 8 && this.getBoard().getAllSquares().indexOf(this) <= 15) // first movement of Pawn
        {
            // update 
            if (this.getBoard().getAllSquares().get(this.getBoard().getAllSquares().indexOf(this) + int8 + 1).HasPiece() && (this.getBoard().getAllSquares().indexOf(this) + 1) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + int8 + 1);
            }
            if (this.getBoard().getAllSquares().get(this.getBoard().getAllSquares().indexOf(this) + int8 - 1).HasPiece() && this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + int8 - 1);
            }
            if (!this.getBoard().getAllSquares().get(this.getBoard().getAllSquares().indexOf(this) + int8).HasPiece()) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + int8);
            }
            if (!this.getBoard().getAllSquares().get(this.getBoard().getAllSquares().indexOf(this) + int16).HasPiece() && !this.getBoard().getAllSquares().get(this.getBoard().getAllSquares().indexOf(this) + int8).HasPiece()) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + int16);
            }
        } else {
            // update
            if (this.getBoard().getAllSquares().get(this.getBoard().getAllSquares().indexOf(this) + int8 + 1).HasPiece() && (this.getBoard().getAllSquares().indexOf(this) + 1) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + int8 + 1);
            }
            if (this.getBoard().getAllSquares().get(this.getBoard().getAllSquares().indexOf(this) + int8 - 1).HasPiece() && this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + int8 - 1);
            }
            if (!this.getBoard().getAllSquares().get(this.getBoard().getAllSquares().indexOf(this) + int8).HasPiece()) {
                moves.add(this.getBoard().getAllSquares().indexOf(this) + int8);
            }
        }
        return moves;
    }

}
