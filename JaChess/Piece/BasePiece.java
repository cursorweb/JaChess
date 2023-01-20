package JaChess.Piece;

import JaChess.Cell;
import JaChess.Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

public abstract class BasePiece {
    /** CONFIG */
    private static final int SIZE = (int) (Cell.SIZE * 0.8);
    private static final int OFFSET = (Cell.SIZE - SIZE) / 2;
    private static final double BORDER = 0.9;
    private static final Color WHITE = new Color(232, 241, 248);

    protected static Grid grid = null;
    public static void setGrid(Grid grid) {
        BasePiece.grid = grid;
    }

    public enum Type {
        Pawn("Pawn"),
        Rook("Rook"),
        Knight("Knight"),
        Bishop("Bishop"),
        King("King"),
        Queen("Queen");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum Side {
        Black,
        White,
    }

    private final Type type;
    protected final Side side;

    protected BasePiece(Type type, Side side) {
        this.type = type;
        this.side = side;
    }

    /** RENDER */
    public void draw(Graphics2D g) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Sprites/" + type + ".png")));

            g.drawImage(image, OFFSET, OFFSET, SIZE, SIZE, null);
            if (side == Side.White) {
                lighten(g, image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void lighten(Graphics2D g, BufferedImage image) {
        image.getGraphics().drawImage(image, 0, 0, null);

        int[] pixel = {0, 0, 0, 0};

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                image.getRaster().getPixel(j, i, pixel);

                image.getRaster().setPixel(j, i, new int[]{WHITE.getRed(), WHITE.getGreen(), WHITE.getBlue(), pixel[3]});
            }
        }

        AffineTransform transform = g.getTransform();

        g.translate(OFFSET + SIZE / 2, OFFSET + SIZE / 2);
        g.scale(BORDER, BORDER);
        g.drawImage(image, -SIZE / 2, -SIZE / 2, SIZE, SIZE, null);

        g.setTransform(transform);
    }

    /** VIEW MOVES + UTIL */
    public void showMoves(Cell cell) {
        changePieces(cell, cell);
    }
    public void hideMoves(Cell cell) {
        changePieces(cell, null);
    }

    public Side getSide() {
        return side;
    }

    protected abstract void changePieces(Cell cell, Cell val);

    protected boolean noMove(int x, int y, Cell val) {
        BasePiece piece = grid.getPiece(x, y);
        Cell cell = grid.getCell(x, y);

        if (piece != null) {
            if (piece.getSide() != side) {
                cell.setSelectedCell(val);
            }

            return true;
        }


        if (cell == null) {
            return true;
        }

        cell.setSelectedCell(val);

        return false;
    }

    protected void captureEnemyPiece(int x, int y, Cell val) {
        if (isEnemyPiece(x, y)) {
            grid.getCell(x, y).setSelectedCell(val);
        }
    }

    protected boolean isEnemyPiece(int x, int y) {
        Cell cell = grid.getCell(x, y);
        System.out.println(cell);
        if (cell == null) {
            return false;
        }

        if (cell.getPiece() == null) {
            return false;
        }

        System.out.println(cell.getPiece());

        return cell.getPiece().getSide() == side;
    }

    /** MOVED */
    public void pieceMoved() {}
}
