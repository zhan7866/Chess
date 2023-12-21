public class King {
    private int row;
    private int col;
    private boolean isBlack;
    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            //calls verifySourceAndDestination to see if the start and end positions are valid
            if (board.verifyAdjacent(this.row, this.col, endRow, endCol))
                //sees if the adjacent movements are legal
                return true;
        }
        return false;
    }
}
