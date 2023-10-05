package application;

public class CheckWin {
	public boolean check(int camp, int[][] positions) { //camp為現在是哪個陣營，positions是現在棋盤的狀況
		int width = 8; //棋盤的寬
		int height = 4; //棋盤的高
		/*
		 * 會有陣營進來，之後去做判斷
		 * 如果是黑色陣營，那就去檢查整個棋盤，如果都沒有紅色的棋子的話，那就是黑色的贏(需要跳過沒有棋子的地方)
		 * 反之則是紅色的贏
		 */
		if(camp == 2) { //紅棋
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(positions[i][j] > 7)
						return false;	
				}
			}
			return true;
		} else if(camp == 1) { //黑棋
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(positions[i][j] <= 7 && positions[i][j] != 0) 
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
