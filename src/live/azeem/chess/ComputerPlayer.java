/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package live.azeem.chess;

import java.util.Objects;

/**
 *
 * @author Azeem Tariq
 */
public class ComputerPlayer {

    private String name;
    private ChessBoard chessBoard;

    public ComputerPlayer(ChessBoard chessBoard) {

        this.chessBoard = chessBoard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void makeMove() {
        boolean moved = false;

        moved = killKing(); // attacking King To Finsh Game 
        // defence State 
        if (!moved && isIndanger(getKing())) {
            moved = kill(getkiller(getKing()));
            if (!moved) {
                moved = isSecureKingOrQueen(getKing());
            }
        }
        if (!moved && isIndanger(getQueen())) {
            moved = kill(getkiller(getQueen()));
            if (!moved) {
                moved = isSecureKingOrQueen(getQueen());
            }
        }
        if (!moved && isIndanger(getRook())) {
            moved = kill(getkiller(getRook()));
            if (!moved) {
                moved = isSecure(getRook());
            }
        }
        if (!moved && isIndanger(getBishop())) {
            moved = kill(getkiller(getBishop()));
            if (!moved) {
                moved = isSecure(getBishop());
            }
        }
        if (!moved && isIndanger(getKnight())) {
            moved = kill(getkiller(getKnight()));
            if (!moved) {
                moved = isSecure(getKnight());
            }
        }
        if (!moved && isIndanger(getPawn())) {
            moved = kill(getkiller(getQueen()));
            if (!moved) {
                moved = isSecure(getPawn());
            }
        }

        // attacking state 
        if (!moved) {
            moved = killQueen();
        }
        if (!moved) {
            moved = killRook();
        }
        if (!moved) {
            moved = killKnight();
        }
        if (!moved) {
            moved = killBishop();
        }
        if (!moved) {
            moved = killPawn();
        }
        if (!moved) {
            moved = moveAnyPiece();
        }
        if (!moved) {
            System.out.println("Unable to move");
        } else {
            System.out.println("Piece Moved By Azeem");
        }
    }

    public boolean kill(Piece victum) {
        for (Piece Spiece : getChessBoard().getAllSquares()) {
            if (Spiece.isWhite() && Spiece.HasPiece()) {
                for (int move : Spiece.getPossibleMoves()) {
                    if (isSecureMove(move) && getChessBoard().getAllSquares().indexOf(victum) == move) {
                        getChessBoard().kill(victum, Spiece, getChessBoard().getAllSquares());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean moveAnyPiece() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (piece.isWhite() && piece.HasPiece()) {
                for (Integer move : piece.getPossibleMoves()) {
                    if (isSecureMove(move) && !getChessBoard().getAllSquares().get(move).isWhite()) {
                        if (getChessBoard().getAllSquares().get(move).HasPiece()) {
                            getChessBoard().kill(getChessBoard().getAllSquares().get(move), piece, getChessBoard().getAllSquares());
                            return true;
                        } else {
                            getChessBoard().move(getChessBoard().getAllSquares().get(move), piece, getChessBoard().getAllSquares());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // fucntions for target killing :D 
    private boolean killKing() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (!piece.isWhite() && piece instanceof King) {
                for (Piece SPiece : getChessBoard().getAllSquares()) {
                    if (SPiece.isWhite() && SPiece.HasPiece()) {
                        for (Integer move : SPiece.getPossibleMoves()) {

                            if (getChessBoard().getAllSquares().indexOf(piece) == move) {
                                getChessBoard().kill(piece, SPiece, getChessBoard().getAllSquares());
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean killQueen() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (!piece.isWhite() && piece instanceof Queen) {
                for (Piece SPiece : getChessBoard().getAllSquares()) {
                    if (SPiece.isWhite() && SPiece.HasPiece()) {
                        for (Integer move : SPiece.getPossibleMoves()) {
                            if (isSecureMove(move) || SPiece instanceof Pawn || SPiece instanceof Knight || SPiece instanceof Bishop) {
                                if (getChessBoard().getAllSquares().indexOf(piece) == move) {
                                    getChessBoard().kill(piece, SPiece, getChessBoard().getAllSquares());
                                    return true;
                                }
                            }

                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean killBishop() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (!piece.isWhite() && piece instanceof Bishop) {
                for (Piece SPiece : getChessBoard().getAllSquares()) {
                    if (SPiece.isWhite() && SPiece.HasPiece()) {
                        for (Integer move : SPiece.getPossibleMoves()) {
                            if (isSecureMove(move) || SPiece instanceof Pawn) {
                                if (getChessBoard().getAllSquares().indexOf(piece) == move) {
                                    getChessBoard().kill(piece, SPiece, getChessBoard().getAllSquares());
                                    return true;
                                }
                            }

                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean killPawn() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (!piece.isWhite() && piece instanceof Pawn) {
                for (Piece SPiece : getChessBoard().getAllSquares()) {
                    if (SPiece.isWhite() && SPiece.HasPiece()) {
                        for (Integer move : SPiece.getPossibleMoves()) {
                            if (isSecureMove(move)) {
                                if (getChessBoard().getAllSquares().indexOf(piece) == move) {
                                    getChessBoard().kill(piece, SPiece, getChessBoard().getAllSquares());
                                    return true;
                                }
                            }

                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean killRook() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (!piece.isWhite() && piece instanceof Rook) {
                for (Piece SPiece : getChessBoard().getAllSquares()) {
                    if (SPiece.isWhite() && SPiece.HasPiece()) {
                        for (Integer move : SPiece.getPossibleMoves()) {
                            if (isSecureMove(move) || SPiece instanceof Pawn) {
                                if (getChessBoard().getAllSquares().indexOf(piece) == move) {
                                    getChessBoard().kill(piece, SPiece, getChessBoard().getAllSquares());
                                    return true;
                                }
                            }

                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean killKnight() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (!piece.isWhite() && piece instanceof Knight) {
                for (Piece SPiece : getChessBoard().getAllSquares()) {
                    if (SPiece.isWhite() && SPiece.HasPiece()) {
                        for (Integer move : SPiece.getPossibleMoves()) {
                            if (isSecureMove(move) || SPiece instanceof Pawn) {
                                if (getChessBoard().getAllSquares().indexOf(piece) == move) {
                                    getChessBoard().kill(piece, SPiece, getChessBoard().getAllSquares());
                                    return true;
                                }
                            }

                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isSecure(Piece impPiece) {
        if (getSecureMove(impPiece) != 1337) {
            if (getChessBoard().getAllSquares().get(getSecureMove(impPiece)).HasPiece()) {
                getChessBoard().kill(getChessBoard().getAllSquares().get(getSecureMove(impPiece)), impPiece, getChessBoard().getAllSquares());
                return true;
            } else {
                getChessBoard().move(getChessBoard().getAllSquares().get(getSecureMove(impPiece)), impPiece, getChessBoard().getAllSquares());
                return true;
            }
        }
        return false;
    }

    private boolean isSecureKingOrQueen(Piece impPiece) {
        if (getSecureMove(impPiece) != 1337) {
            if (getChessBoard().getAllSquares().get(getSecureMove(impPiece)).HasPiece()) {
                getChessBoard().kill(getChessBoard().getAllSquares().get(getSecureMove(impPiece)), impPiece, getChessBoard().getAllSquares());
                return true;
            } else {
                getChessBoard().move(getChessBoard().getAllSquares().get(getSecureMove(impPiece)), impPiece, getChessBoard().getAllSquares());
                return true;
            }
        } else {
            for (Piece piece : getChessBoard().getAllSquares()) {
                if (piece instanceof Pawn && piece.isWhite()) {
                    for (Integer pmove : piece.getPossibleMoves()) {
                        for (Integer Kmove : getkiller(impPiece).getPossibleMoves()) {
                            if (pmove == Kmove) {
                                if (getChessBoard().getAllSquares().get(pmove).HasPiece()) {
                                    getChessBoard().kill(getChessBoard().getAllSquares().get(pmove), piece, getChessBoard().getAllSquares());
                                    return true;
                                } else {
                                    getChessBoard().move(getChessBoard().getAllSquares().get(pmove), piece, getChessBoard().getAllSquares());
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            for (Piece piece : getChessBoard().getAllSquares()) {
                if (piece.isWhite() && piece instanceof Knight || piece instanceof Bishop) {
                    for (Integer pmove : piece.getPossibleMoves()) {
                        for (Integer Kmove : getkiller(impPiece).getPossibleMoves()) {
                            if (pmove == Kmove) {
                                if (getChessBoard().getAllSquares().get(pmove).HasPiece()) {
                                    getChessBoard().kill(getChessBoard().getAllSquares().get(pmove), piece, getChessBoard().getAllSquares());
                                    return true;
                                } else {
                                    getChessBoard().move(getChessBoard().getAllSquares().get(pmove), piece, getChessBoard().getAllSquares());
                                    return true;
                                }
                            }
                        }
                    }
                }
            }

        }
        return false;
    }

    private Piece getKing() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (piece.isWhite() && piece instanceof King) {
                return piece;
            }
        }
        return null;
    }

    private Piece getQueen() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (piece.isWhite() && piece instanceof Queen) {
                return piece;
            }
        }
        return null;
    }

    private boolean isIndanger(Piece impPiece) {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (!piece.isWhite() && piece.HasPiece()) {
                for (Integer move : piece.getPossibleMoves()) {
                    if (getChessBoard().getAllSquares().indexOf(impPiece) == move) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Piece getkiller(Piece impPiece) {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (!piece.isWhite() && piece.HasPiece()) {
                for (Integer move : piece.getPossibleMoves()) {
                    if (getChessBoard().getAllSquares().indexOf(impPiece) == move) {
                        return piece;
                    }
                }
            }
        }
        return null;
    }

    private Integer getSecureMove(Piece impPiece) {
        for (Integer secureMove : impPiece.getPossibleMoves()) {
            if (!getChessBoard().getAllSquares().get(secureMove).isWhite()) {
                if (isSecureMove(secureMove)) {
                    return secureMove;
                }
            }
        }
        return 1337;
    }

    private boolean isSecureMove(Integer move) {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (!piece.isWhite() && piece.HasPiece()) {
                for (Integer bmove : piece.getPossibleMoves()) {
                    if (Objects.equals(move, bmove)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Piece getRook() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (piece.isWhite() && piece instanceof Rook) {
                for (Piece kPiece : getChessBoard().getAllSquares()) {
                    if (!kPiece.isWhite() && kPiece.HasPiece()) {
                        for (Integer move : kPiece.getPossibleMoves()) {
                            if (getChessBoard().getAllSquares().indexOf(piece) == move) {
                                return piece;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private Piece getBishop() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (piece.isWhite() && piece instanceof Bishop) {
                for (Piece kPiece : getChessBoard().getAllSquares()) {
                    if (!kPiece.isWhite() && kPiece.HasPiece()) {
                        for (Integer move : kPiece.getPossibleMoves()) {
                            if (getChessBoard().getAllSquares().indexOf(piece) == move) {
                                return piece;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private Piece getKnight() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (piece.isWhite() && piece instanceof Knight) {
                for (Piece kPiece : getChessBoard().getAllSquares()) {
                    if (!kPiece.isWhite() && kPiece.HasPiece()) {
                        for (Integer move : kPiece.getPossibleMoves()) {
                            if (getChessBoard().getAllSquares().indexOf(piece) == move) {
                                return piece;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private Piece getPawn() {
        for (Piece piece : getChessBoard().getAllSquares()) {
            if (piece.isWhite() && piece instanceof Pawn) {
                for (Piece kPiece : getChessBoard().getAllSquares()) {
                    if (!kPiece.isWhite() && kPiece.HasPiece()) {
                        for (Integer move : kPiece.getPossibleMoves()) {
                            if (getChessBoard().getAllSquares().indexOf(piece) == move) {
                                return piece;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
