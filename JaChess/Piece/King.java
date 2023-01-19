package JaChess.Piece;

import JaChess.Cell;

public class King extends BasePiece {
    public King(Side side) {
        super(Type.King, side);
    }

    @Override
    public void changePieces(Cell cell, Cell val) {
        int x = cell.cellX(), y = cell.cellY();

        for (int pieceX = x - 1; pieceX <= x + 1; pieceX++) {
            for (int pieceY = y - 1; pieceY <= y + 1; pieceY++) {
                noMove(pieceX, pieceY, val);
            }
        }
    }
}
