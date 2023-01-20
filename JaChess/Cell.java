package JaChess;

import JaChess.Piece.BasePiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JComponent {
    /**
     * CONFIG
     */
    public static final int SIZE = 18 * Main.SCALE;

    private static final Color darkColor = new Color(0, 132, 214);
    private static final Color darkColorH = new Color(0, 107, 171);
    private static final Color lightColor = new Color(170, 210, 255);
    private static final Color lightColorH = new Color(144, 177, 215);


    /**
     * Locational
     */
    private static Cell selectedCell = null; // the piece about to be 'moved'
    private BasePiece piece;
    private final int x;
    private final int y;
    private static Grid grid = null;


    /**
     * Design
     */
    private Color color;

    private final boolean dark;

    // where the actual piece is
    private boolean isSelected = false;


    public Cell(Grid grid, int x, int y) {
        if (Cell.grid == null) {
            Cell.grid = grid;
        }

        this.piece = null;
        this.x = x;
        this.y = y;

        dark = (x + y) % 2 == 0;

        if (dark) {
            color = darkColor;
        } else {
            color = lightColor;
        }

        setPreferredSize(new Dimension(SIZE, SIZE));
        setOpaque(true);
        addMouseListener(new MouseHover());
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, SIZE, SIZE);

        if (piece != null) {
            piece.draw(((Graphics2D) g));
        }

        if (isSelected) {
            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillArc(SIZE / 2 - SIZE / 6, SIZE / 2 - SIZE / 6, SIZE / 3, SIZE / 3, 0, 360);
        }
    }


    public void setPiece(BasePiece piece) {
        this.piece = piece;
        repaint();
    }

    public BasePiece getPiece() {
        return piece;
    }


    /**
     * Sets the chosen piece, and marks this current cell as a valid move
     * @param selectedCell the cell
     */
    public void setSelectedCell(Cell selectedCell) {
        if (Cell.selectedCell == null) {
            Cell.selectedCell = selectedCell;
        }

        // if it is null, this is how to remove
        isSelected = selectedCell != null;
        repaint();
    }
    private void clearMoves() {
        if (selectedCell != null) {
            selectedCell.getPiece().hideMoves(selectedCell);
        }
    }

    private class MouseHover implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
                if (!isSelected && piece != null) {
                    if (piece.getSide() == Main.getTurn()) {
                        // case 2: user is trying to select a piece
                        clearMoves();

                        selectedCell = Cell.this;
                        piece.showMoves(Cell.this);
                    }
                } else {
                        System.out.println(x + ", " + y);
                        clearMoves();

                        piece = selectedCell.getPiece();

                        selectedCell.setPiece(null);
                        selectedCell = null;

                        setPiece(piece);
                        piece.pieceMoved();

                        Main.changeTurn();
                        repaint();
                }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (dark) {
                color = darkColorH;
            } else {
                color = lightColorH;
            }

            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (dark) {
                color = darkColor;
            } else {
                color = lightColor;
            }

            repaint();
        }
    }


    public int cellX() {
        return x;
    }

    public int cellY() {
        return y;
    }
}
