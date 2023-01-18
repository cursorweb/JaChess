package JaChess;

import javax.swing.*;
import java.awt.*;

public class Main {
    /* CONFIG */
    public static final int SCALE = 4;

    private static final JPanel panel = new JPanel();
    private static final Grid grid = new Grid();

    private static Piece.Side turn = Piece.Side.White;

    public static void changeTurn() {
        if (turn == Piece.Side.White) {
            turn = Piece.Side.Black;
        } else {
            turn = Piece.Side.White;
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