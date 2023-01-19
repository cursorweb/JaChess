package JaChess.Piece;

import JaChess.Cell;

public class Rook extends BasePiece {
    public Rook(Side side) {
        super(Type.Rook, side);
    }

    @Override
    public void changePieces(Cell cell, Cell val) {
        /*
        switch (type) {
            case Rook:
                for (int pieceY = y; pieceY < 8; pieceY++) {
                    if (noMove(x, pieceY)) {
                        break;
                    }
                }

                for (int pieceY = y; pieceY >= 0; pieceY--) {
                    if (noMove(x, pieceY)) {
                        break;
                    }
                }

                for (int pieceX = x; pieceX < 8; pieceX++) {
                    if (noMove(pieceX, y)) {
                        break;
                    }
                }

                for (int pieceX = x; pieceX >= 0; pieceX--) {
                    if (noMove(pieceX, y)) {
                        break;
                    }
                }
                break;

            case Bishop: {
                loop:
                for (int pieceY = y; pieceY >= 0; pieceY--) {
                    for (int pieceX = x; pieceX >= 0; pieceX--) {
                        if (noMove(pieceX, pieceY)) {
                            break loop;
                        }
                    }
                }

                loop:
                for (int pieceY = y; pieceY < 8; pieceY++) {
                    for (int pieceX = x; pieceX >= 0; pieceX--) {
                        if (noMove(pieceX, pieceY)) {
                            break loop;
                        }
                    }
                }
            } break;
        }
        */
    }
}
