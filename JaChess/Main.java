package JaChess;

import JaChess.Piece.BasePiece;

import javax.swing.*;
import java.awt.*;

public class Main {
    /* CONFIG */
    public static final int SCALE = 4;

    private static final JPanel panel = new JPanel();
    private static final Grid grid = new Grid();

    private static BasePiece.Side turn = BasePiece.Side.White;

    public static void changeTurn() {
        if (turn == BasePiece.Side.White) {
            turn = BasePiece.Side.Black;
        } else {
            turn = BasePiece.Side.White;
        }
    }

    private static void setup() {
        panel.setBackground(new Color(0, 0, 0));
        panel.add(grid);
    }

    public static void main(String[] args) {
        setup();

        JFrame frame = new JFrame();
        frame.setTitle("JaChess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.pack();
    }
}
