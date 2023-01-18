package JaChess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Piece {
    private static final int SIZE = (int) (Cell.SIZE * 0.8);
    private static final int OFFSET = (Cell.SIZE - SIZE) / 2;
    private static final double BORDER = 0.9;
    private static final Color WHITE = new Color(232, 241, 248);

    private static Grid grid = null;
    public static void setGrid(Grid grid) {
        Piece.grid = grid;
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
    private final Side side;

    private boolean doubleMove = true;

    public Piece(Type type, Side side) {
        this.type = type;
        this.side = side;
    }

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

    public void showMoves(int x, int y) {
        grid.clearCircles();

        if (side == Side.White) {
            showMovesWhite(x, y);
        }

        grid.repaint();
    }

    private void showMovesWhite(int x, int y) {
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
    }

    private boolean noMove(int x, int y) {
        if (grid.getPiece(x, y) != null) {
            return true;
        }

        grid.getCell(x, y).setCircle(true);

        return false;
    }
}
