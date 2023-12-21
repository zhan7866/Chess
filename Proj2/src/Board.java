import java.util.*;
public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not 
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece. 
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (board[startRow][startCol] != null && board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
            board[endRow][endCol] = board[startRow][startCol];
            board[startRow][startCol] = null;
            return true;
        }
        return false;
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int countKings = 0; //counts total kings
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { //loops through all the positions
                if (board[i][j] != null && (board[i][j].getCharacter() == '\u265a' ||  board[i][j].getCharacter() == '\u2654'))
                    //see if there are kings & add to the count of kings
                    countKings ++;
            }
        }
        if (countKings < 2) //game is over if there are less than two kings
            return true;
        return false;
    }

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String temp = "";
        temp += '\u2001';
        temp += '\uFF10';
        temp += '\u2001';
        temp += '\uFF11';
        temp += '\u2001';
        temp += '\uFF12';
        temp += '\u2001';
        temp += '\uFF13';
        temp += '\u2001';
        temp += '\uFF14';
        temp += '\u2001';
        temp += '\uFF15';
        temp += '\u2001';
        temp += '\uFF16';
        temp += '\u2001';
        temp += '\uFF17';
        temp += '\n';
        //adds the column numbers in one row then adds newline
        for (int i = 0; i < 8; i++) {
            temp += i + "" + '\u2001'; //row numbers
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j] != null)
                    temp += "|" + board[i][j].toString(); //adds | and the piece
                else {
                    temp += "|" + '\u2001'; //adds | and a space
                }
            }
            temp += "|\n";
        }
        return temp;
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i > 8; i++) {
            for (int j = 0; j > 8; j++) {
                board[i][j] = null; //loops through all the position & makes them null
            }
        }
    }

    // Movement helper functions

    public boolean inBounds(int row, int col) {
        return ((0 <= row && row <= 7) && (0 <= col && col <= 7));
    }


    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        return (inBounds(startRow, startCol) && inBounds(endRow, endCol) && board[startRow][startCol] != null &&
                isBlack == board[startRow][startCol].getIsBlack() && (board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack));
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        return (Math.abs(startRow-endRow) <= 1 && Math.abs(startCol-endCol) <= 1);
//        (startRow != endRow && endRow != endCol) &&
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol)
            return true;
        if (startRow != endRow)
            return false;
        int direction = (endCol-startCol) / Math.abs(endCol-startCol);
        while (startCol+direction != endCol) {
            if (board[startRow][startCol+direction] != null)
                return false;
            startCol += direction;
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol)
            return true;
        if (startCol != endCol)
            return false;
        int direction = (endRow-startRow) / Math.abs(endRow-startRow);
        while (startRow+direction != endRow) {
            if (board[startRow+direction][startCol] != null)
                return false;
            startRow += direction;
        }
        return true;
    }


    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol)
            return true;
        if (Math.abs(endRow - startRow) != Math.abs(endCol - startCol))
            return false; //checks to see if the horizontal and vertical change are equal & false if not
        else {
            int rowDirection = (endRow - startRow) / Math.abs(endRow - startRow); //divides change by the absolute value
            int colDirection = (endCol - startCol) / Math.abs(endCol - startCol); //gets either 1 or -1
            while ((startRow+rowDirection != endRow) && (endCol+colDirection != startCol)) { //loops through all of the pieces in between
                if (getPiece(startRow+rowDirection, startCol+colDirection) != null) //checks to see if they're all null
                    return false;
                startRow += rowDirection;
                startCol += colDirection;
            }
        }
        return true;
    }
}
