import java.util.*;
public class Knight {
    int row;
    int col;
    boolean isBlack;
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        return (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) && (Math.abs(endCol-col) == 2 && Math.abs(endRow-row) == 1 ||
                Math.abs(endCol-col) == 1 && Math.abs(endRow-row) == 2));
    }
}
