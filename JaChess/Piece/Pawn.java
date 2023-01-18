package JaChess.Piece;

public class Pawn extends BasePiece {
    public Pawn(Side side) {
        super(Type.Pawn, side);
    }

    @Override
    protected void showPieceMoves(int x, int y) {
        /*
        switch (type) {
            case Pawn:
                if (grid.getPiece(x, y - 1) == null) {
                    grid.getCell(x, y - 1).setCircle(true);
                }

                if (doubleMove && grid.getPiece(x, y - 2) == null) {
                    grid.getCell(x, y - 2).setCircle(true);
                }
                break;

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
