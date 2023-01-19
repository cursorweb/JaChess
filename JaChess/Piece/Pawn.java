package JaChess.Piece;

import JaChess.Cell;

public class Pawn extends BasePiece {
    private boolean doubleMove = true;

    public Pawn(Side side) {
        super(Type.Pawn, side);
    }

    @Override
    public void changePieces(Cell cell, Cell val) {
        if (side == Side.White) {
            int x = cell.cellX(), y = cell.cellY();

            if (grid.getPiece(x, y - 1) == null) {
                grid.getCell(x, y - 1).setSelectedCell(val);
            }

            if (doubleMove && grid.getPiece(x, y - 2) == null) {
                grid.getCell(x, y - 2).setSelectedCell(val);
            }
        } else {
            int x = cell.cellX(), y = cell.cellY();

            if (grid.getPiece(x, y + 1) == null) {
                grid.getCell(x, y + 1).setSelectedCell(val);
            }

            if (doubleMove && grid.getPiece(x, y + 2) == null) {
                grid.getCell(x, y + 2).setSelectedCell(val);
            }
        }
    }

    @Override
    public void pieceMoved() {
        doubleMove = false;
    }
}
