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
    private static Cell selectedPiece = null; // the piece about to be 'moved'
    private BasePiece piece;
    private final int x;
    private final int y;


    /**
     * Design
     */
    private Color color;

    private final boolean dark;

    // where the actual piece is
    private Cell selectedCell = null;


    public Cell(int x, int y) {
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

        if (selectedCell != null) {
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


    public void setSelectedCell(Cell selectedCell) {
        if (selectedPiece == null) {
            selectedPiece = selectedCell;
        }

        this.selectedCell = selectedCell;
        repaint();
    }
    private void clear() {
        if (selectedPiece != null) {
            selectedPiece.getPiece().hideMoves(selectedPiece);
        }
    }

    private class MouseHover implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            mousePressed(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (piece == null) {
                if (selectedCell != null) {
                    piece = selectedCell.getPiece();

                    clear();
                    selectedCell.setPiece(null);
                    selectedPiece = null;

                    setPiece(piece);
                    repaint();
                }
            } else {
                // case 2: user is trying to select a piece
                clear();

                selectedPiece = Cell.this;
                piece.showMoves(Cell.this);
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePressed(e);
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
