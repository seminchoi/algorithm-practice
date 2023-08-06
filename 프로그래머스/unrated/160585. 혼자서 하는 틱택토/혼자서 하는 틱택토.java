class Solution {
    int oCnt = 0;
    int xCnt = 0;
    int oWin = 0, xWin = 0;
    
    public int solution(String[] board) {
        return checkValid(board) ? 1 : 0;
    }
    
    public boolean checkValid(String[] board) {
        checkNumber(board);
        checkWidth(board);
        checkHeight(board);
        checkDiagonal(board);
        
        return (oCnt - xCnt == 0 || oCnt - xCnt == 1) && !winButNotFinished();
    }
    
    
    public void checkNumber(String[] board) {
        for(String point : board) {
            for(int i = 0; i < 3; i++) {
                if(point.charAt(i) == 'O') {
                    oCnt++;
                } else if(point.charAt(i) == 'X') {
                    xCnt++;
                }
            }
        }
    }
    
    public void checkWidth(String[] board) {
        for(String point : board) {
            int O = 0, X = 0;
            for(int i = 0; i < 3; i++) {
                if(point.charAt(i) == 'O') {
                    O++;
                } else if(point.charAt(i) == 'X') {
                    X++;
                }
            }
            
            if(O == 3)
                oWin++;
            if(X == 3)
                xWin++;
        }
    }
    
     public void checkHeight(String[] board) {
        for(int i = 0; i < 3; i++) {
            int O = 0, X = 0;
            for(int j = 0; j < 3; j++) {
                if(board[j].charAt(i) == 'O') {
                    O++;
                } else if(board[j].charAt(i) == 'X') {
                    X++;
                }
            }
            
            if(O == 3)
                oWin++;
            if(X == 3)
                xWin++;
        }
    }
    
    public void checkDiagonal(String[] board) {
        if(isSameChar(board[0].charAt(0), board[1].charAt(1), board[2].charAt(2)) || 
          isSameChar(board[0].charAt(2), board[1].charAt(1), board[2].charAt(0))) {
            if(board[1].charAt(1) == 'O') {
                oWin++;
            }
            else if(board[1].charAt(1) == 'X') {
                xWin++;
            }
        } 
    }
    
    public boolean isSameChar(char ...args) {
        char first = args[0];
        for(char c : args) {
            if(c != first) 
                return false;
        }
        return true;
    }
    
    public boolean winButNotFinished() {
        return (oWin == 1 && xCnt == oCnt) || (xWin == 1 && oCnt > xCnt);
    }  
}