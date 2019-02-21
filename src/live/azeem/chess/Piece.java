/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package live.azeem.chess;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Azeem Tariq
 */
public class Piece extends JButton {

    private boolean isWhite;
    private boolean hasPiece;
    private boolean Selected;
    private ChessBoard board;
    private Color oldBackgroundColor;

    public Piece() {
    }

    public Piece(ChessBoard board) {
        this.isWhite = false;
        hasPiece = false;

        Selected = false;
        this.board = board;

    }

    public boolean HasPiece() {
        return hasPiece;
    }

    public void setHasPiece(boolean hasPiece) {
        this.hasPiece = hasPiece;
    }

    public boolean isSelected() {
        return this.Selected;
    }

    public void setSelected(boolean Selected) {
        this.Selected = Selected;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setIsWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public Color getOldBackgroundColor() {
        return oldBackgroundColor;
    }

    public void setOldBackgroundColor(Color oldBackgroundColor) {
        this.oldBackgroundColor = oldBackgroundColor;
    }

    public ArrayList<Integer> getPossibleMoves() {
        return null;
    }

}
