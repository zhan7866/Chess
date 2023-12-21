public class Rook {
    int row;
    int col;
    boolean isBlack;
    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        return (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) &&
                (board.verifyVertical(row, col, endRow, endCol) ||
                        board.verifyHorizontal(row, col, endRow, endCol)));
    }
}
