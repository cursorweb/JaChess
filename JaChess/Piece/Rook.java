package JaChess.Piece;

import JaChess.Cell;

public class Rook extends BasePiece {
    public Rook(Side side) {
        super(Type.Rook, side);
    }

    @Override
    public void changePieces(Cell cell, Cell val) {
        int x = cell.cellX(), y = cell.cellY();

        for (int pieceY = y + 1; pieceY < 8; pieceY++) {
            if (noMove(x, pieceY, val)) {
                break;
            }
        }

        for (int pieceY = y - 1; pieceY >= 0; pieceY--) {
            if (noMove(x, pieceY, val)) {
                break;
            }
        }

        for (int pieceX = x + 1; pieceX < 8; pieceX++) {
            if (noMove(pieceX, y, val)) {
                break;
            }
        }

        for (int pieceX = x - 1; pieceX >= 0; pieceX--) {
            if (noMove(pieceX, y, val)) {
                break;
            }
        }
    }
}
