package JaChess;

import JaChess.Piece.*;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private final Cell[][] grid = new Cell[8][8];

    public Grid() {
        setLayout(new GridLayout(8, 8));

        makeGrid();

        makePieces(BasePiece.Side.White);
        makePieces(BasePiece.Side.Black);

        BasePiece.setGrid(this);
    }

    private void makeGrid() {
        for (int y = 0; y < 8; y++) { // 1-8
            for (int x = 0; x < 8; x++) { // a-z
                grid[y][x] = new Cell(this, x, y);
                add(grid[y][x]);
            }
        }
    }

    private void makePieces(BasePiece.Side side) {
        int pawnY, pieceY;

        if (side == BasePiece.Side.Black) {
            pawnY = 1;
            pieceY = 0;
        } else {
            pawnY = 6;
            pieceY = 7;
        }

        for (int x = 0; x < 8; x++) {
            getCell(x, pawnY).setPiece(new Pawn(side));
        }

        getCell(0, pieceY).setPiece(new Rook(side));
        getCell(1, pieceY).setPiece(new Knight(side));
        getCell(2, pieceY).setPiece(new Bishop(side));

        getCell(3, pieceY).setPiece(new Queen(side));
        getCell(4, pieceY).setPiece(new King(side));

        getCell(5, pieceY).setPiece(new Bishop(side));
        getCell(6, pieceY).setPiece(new Knight(side));
        getCell(7, pieceY).setPiece(new Rook(side));
    }

    public Cell getCell(int x, int y) {
        return grid[y][x];
    }

    public BasePiece getPiece(int x, int y) {
        return getCell(x, y).getPiece();
    }
}
