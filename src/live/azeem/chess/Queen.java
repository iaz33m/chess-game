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
public class Queen extends Piece {

    public Queen(boolean isWhite, ChessBoard board) {

        this.setIsWhite(isWhite);
        this.setHasPiece(true);
        this.setSelected(false);
        this.setBoard(board);
        if (isWhite) {
            this.setIcon(new ImageIcon(getClass().getResource("WhiteQueen.png")));
        } else {
            this.setIcon(new ImageIcon(getClass().getResource("BlackQueen.png")));
        }

    }

    @Override
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>(); // Arraylist of integers for saving possible moves

        // Rook
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

        // Bishop
        if (this.getBoard().getAllSquares().indexOf(this) <= 7) {
            for (int i = this.getBoard().getAllSquares().indexOf(this) + 9; i <= 63; i = i + 9) {
                if (i % 8 == 0) {
                    break;
                }
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                for (int i = this.getBoard().getAllSquares().indexOf(this) + 7; i <= 63; i = i + 7) {

                    moves.add(i);
                    if (this.getBoard().getAllSquares().get(i).HasPiece() || i % 8 == 0) {
                        break;
                    }
                }
            }
        } else if (this.getBoard().getAllSquares().indexOf(this) >= 56) {
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                for (int i = this.getBoard().getAllSquares().indexOf(this) - 9; i >= 0; i = i - 9) {
                    moves.add(i);
                    if (this.getBoard().getAllSquares().get(i).HasPiece() || i % 8 == 0) {
                        break;
                    }
                }
            }
            for (int i = this.getBoard().getAllSquares().indexOf(this) - 7; i >= 0; i = i - 7) {
                if (i % 8 == 0) {
                    break;
                }
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }
        } else {
            for (int i = this.getBoard().getAllSquares().indexOf(this) + 9; i <= 63; i = i + 9) {
                if (i % 8 == 0) {
                    break;
                }
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }
            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                for (int i = this.getBoard().getAllSquares().indexOf(this) + 7; i <= 63; i = i + 7) {

                    moves.add(i);
                    if (this.getBoard().getAllSquares().get(i).HasPiece() || i % 8 == 0) {
                        break;
                    }
                }
            }

            if (this.getBoard().getAllSquares().indexOf(this) % 8 != 0) {
                for (int i = this.getBoard().getAllSquares().indexOf(this) - 9; i >= 0; i = i - 9) {
                    moves.add(i);
                    if (this.getBoard().getAllSquares().get(i).HasPiece() || i % 8 == 0) {
                        break;
                    }
                }
            }
            for (int i = this.getBoard().getAllSquares().indexOf(this) - 7; i >= 0; i = i - 7) {
                if (i % 8 == 0) {
                    break;
                }
                moves.add(i);
                if (this.getBoard().getAllSquares().get(i).HasPiece()) {
                    break;
                }
            }

        }

        return moves;
    }
}
