/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package live.azeem.chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

/**
 *
 * @author Azeem Tariq
 */
public class ChessBoard extends JFrame {

    private ArrayList<Piece> allSquares;
    private JPanel bPannel; // big pannel
    private Piece pieceToMove;
    private boolean Selected;
    private boolean whiteplayerturn;
    private boolean Singleplayer;     // tells that game is single player or Two PLayer , 
    private ComputerPlayer azeem;
    JFrame pawnPromotion;

    // Variables declaration - do not modify
    private JPanel MainPanel;
    private JButton btnNewGame;
    private JLabel player1icon;
    private JLabel player1name;
    private JLabel player2icon;
    private JLabel player2name;

    // End of variables declaration 
    public ChessBoard(boolean isSiglePlayer, String title, String firstPlayerName, String secondPlayerName) {
        this.Singleplayer = isSiglePlayer;
        this.setTitle(title);
        initComponents();
        makegui(firstPlayerName, secondPlayerName);
        pawnPromotion = new JFrame();
    }

    private void initComponents() {
        this.setSize(new Dimension(700, 700));
        this.setDefaultCloseOperation(ChessBoard.EXIT_ON_CLOSE);
        this.setResizable(true);
        bPannel = new JPanel(new GridLayout(8, 8));  // setting layout to GridLayout with 8 rows and 8 columns

        azeem = new ComputerPlayer(this);
        allSquares = new ArrayList<>();
        pieceToMove = null;
        whiteplayerturn = false;
        Selected = false;
        int count = 1; // counter that helps in assigning colors to Boxes , ie White , Black
        for (int i = 1; i <= 64; i++) {
            Piece temp = new Piece(this);
            // adding pawn in chessboard
            if (i >= 9 && i <= 16) {
                temp = new Pawn(false, this); // false means Pawn is not White
            }
            if (i >= 49 && i <= 56) {
                temp = new Pawn(true, this); // true means Pawn is White
            }        // adding Back and White Rook in ChessBoard
            if (i == 1) {
                temp = new Rook(false, this);  // false means Rook is not White
            }
            if (i == 8) {
                temp = new Rook(false, this);
            }
            if (i == 57) {
                temp = new Rook(true, this);
            }
            if (i == 64) {
                temp = new Rook(true, this);    // true means Rook is White
            }        // adding Back and White Knight in ChessBoard
            if (i == 2) {
                temp = new Knight(false, this);
            }
            if (i == 7) {
                temp = new Knight(false, this);
            }
            if (i == 58) {
                temp = new Knight(true, this);
            }
            if (i == 63) {
                temp = new Knight(true, this);
            }

            // adding Back and White Bishop in ChessBoard
            if (i == 3) {
                temp = new Bishop(false, this);
            }
            if (i == 6) {
                temp = new Bishop(false, this);
            }
            if (i == 59) {
                temp = new Bishop(true, this);
            }
            if (i == 62) {
                temp = new Bishop(true, this);
            }
            //Queen and king
            if (i == 4) {
                temp = new Queen(false, this);
            }
            if (i == 5) {
                temp = new King(false, this);
            }
            if (i == 60) {
                temp = new Queen(true, this);
            }
            if (i == 61) {
                temp = new King(true, this);
            }
            // code for assigning colors to Boxes , ie White , Black
            if (count % 2 == 0) {
                temp.setBackground(Color.darkGray);
                temp.setOldBackgroundColor(Color.darkGray);
            } else {
                temp.setBackground(Color.blue);
                temp.setOldBackgroundColor(Color.blue);
            }
            count++;
            if (i % 8 == 0) {
                count--;
            }
            // Mouse Click Event
            addMouseListener(temp);
            this.bPannel.add(temp); // adding Box in Pannel

            this.allSquares.add(temp); // adding Box arraylist
        }
        this.add(bPannel); // adding panel in frame

    }

    public boolean isWhiteplayerturn() {
        return whiteplayerturn;
    }

    public void setWhiteplayerturn(boolean whiteplayerturn) {
        this.whiteplayerturn = whiteplayerturn;
    }

    public ArrayList<Piece> getAllSquares() {
        return allSquares;
    }

    public JPanel getbPannel() {
        return bPannel;
    }

    public Piece getPieceToMove() {
        return pieceToMove;
    }

    public boolean isSelected() {
        return Selected;
    }

    public void setAllSquares(ArrayList<Piece> allSquares) {
        this.allSquares = allSquares;
    }

    public void setbPannel(JPanel bPannel) {
        this.bPannel = bPannel;
    }

    public void setPieceToMove(Piece pieceToMove) {
        this.pieceToMove = pieceToMove;
    }

    public void setSelected(boolean Selected) {
        this.Selected = Selected;
    }

    public boolean isSingleplayer() {
        return Singleplayer;
    }

    public void setSingleplayer(boolean Singleplayer) {
        this.Singleplayer = Singleplayer;
    }

    public void showMoves(Piece piece, ArrayList<Integer> moves) {

        for (Integer move : moves) {
            if (!getAllSquares().get(move).HasPiece()) {
                getAllSquares().get(move).setBackground(Color.GREEN);
                getAllSquares().get(move).setSelected(true);
                setSelected(true);
            } else {
                if (getAllSquares().get(move).isWhite() && !piece.isWhite() || !getAllSquares().get(move).isWhite() && piece.isWhite()) {
                    getAllSquares().get(move).setBackground(Color.RED);
                    getAllSquares().get(move).setSelected(true);
                    setSelected(true);
                }
            }
        }

    }

    public void move(Piece victum, Piece replacer, ArrayList<Piece> allpieces) {
        int victumIndex = allpieces.indexOf(victum);
        int replacerIndex = allpieces.indexOf(replacer);
        Collections.swap(allpieces, victumIndex, replacerIndex);
        Color victumBackGroundColor = victum.getBackground();
        victum.setBackground(replacer.getBackground());
        victum.setOldBackgroundColor(replacer.getBackground());
        replacer.setBackground(victumBackGroundColor);
        replacer.setOldBackgroundColor(victumBackGroundColor);
    }

    public void kill(Piece victum, Piece killer, ArrayList<Piece> allpieces) {
        int victumIndex = allpieces.indexOf(victum);
        int killerIndex = allpieces.indexOf(killer);
        allpieces.remove(victum);
        Piece temp = new Piece(this);
        allpieces.add(killerIndex, temp);
        allpieces.remove(killer);
        allpieces.add(victumIndex, killer);
        Color victumBackGroundColor = victum.getBackground();
        temp.setBackground(killer.getBackground());
        temp.setOldBackgroundColor(killer.getBackground());
        killer.setBackground(victumBackGroundColor);
        killer.setOldBackgroundColor(victumBackGroundColor);
        addMouseListener(temp);
    }

    public void resetColors() {
        for (Piece piece : getAllSquares()) {
            piece.setBackground(piece.getOldBackgroundColor());
        }
    }

    public void updateGui() {
        bPannel.removeAll();
        for (Piece piece : getAllSquares()) {
            bPannel.add(piece);
        }
        bPannel.repaint();
        bPannel.validate();
    }

    public void deSelect() {
        for (Piece piece : getAllSquares()) {
            piece.setSelected(false);
        }
    }

    private void addMouseListener(Piece piece) {

        piece.addMouseListener(new MouseAdapter() {

            //  @Override
            public void mousePressed(MouseEvent e) {

                final Piece p = (Piece) e.getSource();
                boolean hasturn = isWhiteplayerturn() && p.isWhite() || !isWhiteplayerturn() && !p.isWhite();
                if (!isSelected() && p.HasPiece() && hasturn) {
                    setPieceToMove(p);
                    showMoves(p, p.getPossibleMoves());
                } else if (p == getPieceToMove()) {
                    resetColors();
                    deSelect();
                    setPieceToMove(null);
                    setSelected(false);
                } else if (getPieceToMove() instanceof Pawn && getPieceToMove().isWhite() && getAllSquares().indexOf(p) <= 7 || getPieceToMove() instanceof Pawn && !getPieceToMove().isWhite() && getAllSquares().indexOf(p) >= 56) // Pawn promotion
                {
                    promote(p);
                } else if (isSelected() && p.isSelected()) {
                    resetColors();
                    if (p.HasPiece()) {
                        if (p instanceof King) {
                            JOptionPane.showMessageDialog(null, "You can not kill King :D", "Information", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            kill(p, getPieceToMove(), getAllSquares());
                            if (isWhiteplayerturn()) {
                                setWhiteplayerturn(false);
                            } else {
                                setWhiteplayerturn(true);
                            }
                            setPieceToMove(null);
                        }

                    } else {
                        move(p, getPieceToMove(), getAllSquares());
                        if (isWhiteplayerturn()) {
                            setWhiteplayerturn(false);
                        } else {
                            setWhiteplayerturn(true);
                        }
                        setPieceToMove(null);
                    }
                    updateGui();
                    deSelect();
                    setSelected(false);

                    // if its Single PLayer Game 
                    if (isSingleplayer()) {
                        azeem.makeMove();
                        resetColors();
                        updateGui();
                        deSelect();
                        setSelected(false);
                        if (isWhiteplayerturn()) {
                            setWhiteplayerturn(false);
                        } else {
                            setWhiteplayerturn(true);
                        }
                    }

                } else if (!hasturn) {
                    if (p.isWhite()) {
                        JOptionPane.showMessageDialog(null, "Its Black Player Turn", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Its White Player Turn", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    public void promote(Piece clickedPiece) {
        pawnPromotion = new JFrame();
        pawnPromotion.setTitle("Pawn Promotion interface");
        pawnPromotion.setSize(new Dimension(400, 200));
        pawnPromotion.setResizable(false);
        pawnPromotion.setDefaultCloseOperation(ChessBoard.DISPOSE_ON_CLOSE);
        JPanel mainpanel = new JPanel(new GridLayout(1, 4));
        final Piece queen = new Queen(getPieceToMove().isWhite(), getboard());
        final Piece bishop = new Bishop(getPieceToMove().isWhite(), getboard());
        final Piece knight = new Knight(getPieceToMove().isWhite(), this.getboard());
        final Piece rook = new Rook(getPieceToMove().isWhite(), this.getboard());
        myMouseListner(clickedPiece, queen);
        myMouseListner(clickedPiece, bishop);
        myMouseListner(clickedPiece, knight);
        myMouseListner(clickedPiece, rook);
        mainpanel.add(queen);
        mainpanel.add(bishop);
        mainpanel.add(knight);
        mainpanel.add(rook);
        pawnPromotion.add(mainpanel);
        // code to open frame in center of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pawnPromotion.setLocation(dim.width / 2 - pawnPromotion.getSize().width / 2, dim.height / 2 - pawnPromotion.getSize().height / 2);
        pawnPromotion.setVisible(true);

    }

    public void myMouseListner(final Piece ClickedPiece, final Piece impPiece) {

        impPiece.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Piece piece = new Piece(null);
                if (impPiece instanceof Queen) {
                    piece = new Queen(getPieceToMove().isWhite(), getboard());
                }
                if (impPiece instanceof Bishop) {
                    piece = new Bishop(getPieceToMove().isWhite(), getboard());
                }
                if (impPiece instanceof Knight) {
                    piece = new Knight(getPieceToMove().isWhite(), getboard());
                }
                if (impPiece instanceof Rook) {
                    piece = new Rook(getPieceToMove().isWhite(), getboard());
                }
                piece.setBackground(ClickedPiece.getOldBackgroundColor());
                piece.setOldBackgroundColor(ClickedPiece.getOldBackgroundColor());
                addMouseListener(piece);
                if (ClickedPiece.HasPiece()) {
                    kill(ClickedPiece, getPieceToMove(), getAllSquares());
                } else {
                    move(ClickedPiece, getPieceToMove(), getAllSquares());
                }
                int indextofPawn = 0;
                indextofPawn = getAllSquares().indexOf(getPieceToMove());
                getAllSquares().remove(getPieceToMove());
                getAllSquares().add(indextofPawn, piece);
                resetColors();
                updateGui();
                deSelect();
                setSelected(false);
                setPieceToMove(null);
                if (isWhiteplayerturn()) {
                    setWhiteplayerturn(false);
                } else {
                    setWhiteplayerturn(true);
                }
                pawnPromotion.setVisible(false);

            }
        });

    }

    public JLabel getPlayer1name() {
        return player1name;
    }

    public void setPlayer1name(JLabel player1name) {
        this.player1name = player1name;
    }

    public JLabel getPlayer2name() {
        return player2name;
    }

    public void setPlayer2name(JLabel player2name) {
        this.player2name = player2name;
    }

    public void makegui(String firstPlayerName, String secondPlayerName) {
        MainPanel = new JPanel();
        player2icon = new JLabel();
        player1icon = new JLabel();
        player1name = new JLabel();
        player2name = new JLabel();
        btnNewGame = new JButton();
        player1name.setText(firstPlayerName);
        player2name.setText(secondPlayerName);
        player1name.setForeground(Color.WHITE);
        player2name.setForeground(Color.WHITE);
        player1name.setFont(new Font(player1name.getName(), Font.PLAIN, 15));
        player2name.setFont(new Font(player2name.getName(), Font.PLAIN, 15));
        player1icon.setIcon(new ImageIcon(getClass().getResource("player1.jpg")));
        if (isSingleplayer()) {
            player2icon.setIcon(new ImageIcon(getClass().getResource("azeem.png")));
        } else {
            player2icon.setIcon(new ImageIcon(getClass().getResource("player2.png")));
        }
        //MainPanel.setIcon(new ImageIcon(getClass().getResource("Images/chessbackground.png")));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        btnNewGame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Game game = new Game(Singleplayer, getPlayer1name().getText(), getPlayer2name().getText());
                getboard().dispose();
            }
        });
        MainPanel.setBackground(new Color(0, 51, 51));
        btnNewGame.setText("New Game");
        GroupLayout MainPanelLayout = new GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(MainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(player2name, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addComponent(player2icon, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bPannel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(player1name, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(player1icon, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap())
                                .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(btnNewGame)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(MainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(bPannel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(MainPanelLayout.createSequentialGroup()
                                                        .addComponent(player1name, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(player1icon, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(MainPanelLayout.createSequentialGroup()
                                                        .addComponent(player2name, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(player2icon, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(51, 51, 51)
                                        .addComponent(btnNewGame)
                                        .addGap(0, 402, Short.MAX_VALUE)))
                        .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(MainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(MainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );

        pack();
    }

    public ChessBoard getboard() {
        return this;
    }
}
