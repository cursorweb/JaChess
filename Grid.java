import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private final Cell[][] grid = new Cell[8][8];

    public Grid() {
        setLayout(new GridLayout(8, 8));

        makeGrid();

        makePieces(Piece.Side.White);
        makePieces(Piece.Side.Black);
    }

    private void makeGrid() {
        for (int y = 0; y < 8; y++) { // 1-8
            for (int x = 0; x < 8; x++) { // a-z
                grid[y][x] = new Cell(x, y);
                add(grid[y][x]);
            }
        }
    }

    public void clearCircles() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                grid[y][x].setCircle(false);
            }
        }
    }

    private void makePieces(Piece.Side side) {
        int pawnY, line;

        if (side == Piece.Side.Black) {
            pawnY = 1;
            line = -1;
        } else {
            pawnY = 6;
            line = 1;
        }

        Piece.Type[] order = { Piece.Type.Rook, Piece.Type.Knight, Piece.Type.Bishop };

        for (int x = 0; x < 8; x++) {
            getCell(x, pawnY).setPiece(new Piece(Piece.Type.Pawn, side));
        }

        for (int x = 0; x < 3; x++) {
            getCell(x, pawnY + line).setPiece(new Piece(order[x], side));
        }

        getCell(3, pawnY + line).setPiece(new Piece(Piece.Type.Queen, side));
        getCell(4, pawnY + line).setPiece(new Piece(Piece.Type.King, side));

        for (int x = 5; x < 8; x++) {
            getCell(x, pawnY + line).setPiece(new Piece(order[2 - (x - 5)], side));
        }
    }

    public Cell getCell(int x, int y) {
        return grid[y][x];
    }

    public Piece getPiece(int x, int y) {
        return getCell(x, y).getPiece();
    }
}
