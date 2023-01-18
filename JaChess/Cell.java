package JaChess;

import JaChess.Piece.BasePiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JComponent {
    /** CONFIG */
    public static final int SIZE = 18 * Main.SCALE;

    private static final Color darkColor = new Color(0, 132, 214);
    private static final Color darkColorH = new Color(0, 107, 171);
    private static final Color lightColor = new Color(170, 210, 255);
    private static final Color lightColorH = new Color(144, 177, 215);


    /** Locational */
    private BasePiece piece;
    private final int x;
    private final int y;

    /** Design */
    private Color color;

    private final boolean dark;
    private boolean circle = false;

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

        if (circle) {
            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillArc(SIZE / 2 - SIZE / 6, SIZE / 2 - SIZE / 6, SIZE / 3, SIZE / 3, 0, 360);
        }
    }

    public void setPiece(BasePiece piece) {
        this.piece = piece;
    }

    public BasePiece getPiece() {
        return piece;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
    }

    private class MouseHover implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            mousePressed(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (piece == null) {
                return;
            }

            piece.showMoves(x, y);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePressed(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
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
}
