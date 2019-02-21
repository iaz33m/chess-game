/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package live.azeem.chess;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Azeem Tariq
 */
public class Game {

    // data members

    ChessBoard myChessBoard;
    boolean isSiglePlayer;

    //constructor

    public Game(boolean isSinlgePlayer, String firstPlayerName, String secondPlayerName) {
        this.isSiglePlayer = isSinlgePlayer;
        myChessBoard = new ChessBoard(isSiglePlayer, "Chess Game", firstPlayerName, secondPlayerName);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        myChessBoard.setLocation(dim.width / 2 - myChessBoard.getSize().width / 2, dim.height / 2 - myChessBoard.getSize().height / 2);
        myChessBoard.show();
    }
}
