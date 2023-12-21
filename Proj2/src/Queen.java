public class Queen {
    private int row;
    private int col;
    private boolean isBlack;
    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            //calls verifySourceAndDestination to see if the start and end positions are valid
            //sees if either the vertical or horizontal or diagonal movements are legal
            return board.verifyVertical(this.row, this.col, endRow, endCol) || board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyDiagonal(this.row, this.col, endRow, endCol);
        }
        return false;
    }
}
