package application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Action {
	/*
	 * 棋子的移動跟吃子
	 */
	ChessAndPositions CAPositions = new ChessAndPositions();
	
	
	int[][] position = new int[4][8];
	public int countPeace = 0; //和局判斷次數
	int PEACE = 40;
	int[][] errorPositions = new int[4][8];
	
	public void setError() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 1; j++) {
				errorPositions[i][j] = -1;
			}
		}
	}
	
	public int[][] moveOrEat(int[] lastP, int[] nowP, int camp, int[][] position) {
		/*
		 * lastP為上個位置
		 * nowP為現在的位置
		 * camp為陣營
		 */
		if(position[nowP[0]][nowP[1]] == 0) {
			position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]]; //如果是空格，就直接移過去
			position[lastP[0]][lastP[1]] = 0;
			countPeace++;
			if(countPeace == PEACE) {
				Alert alert = new Alert(AlertType.INFORMATION); //跳出和局
				alert.setTitle("Game Over!");
				alert.setHeaderText("遊戲結束，已經走到四十步且無翻棋或吃子，此次為和局");
				alert.setContentText("感謝遊玩");
				if(alert.showAndWait().get() == ButtonType.OK)
					System.exit(0);
			}
			return position;
		} else {
			if(camp == 2) {
				if((position[lastP[0]][lastP[1]]) == 2 && position[nowP[0]][nowP[1]] > 7) { //上一個位置:黑包，現在的位置:紅的
					if(nowP[0] == lastP[0]) { //i相同，j不同
						if(nowP[1] > lastP[1]) { //上一個位置在現在位置的左邊
							int count = 0;
							for(int k = lastP[1]+1; k < nowP[1]; k++) {
								if(position[nowP[0]][k] != 0)
									count++;
							}
							if(count == 1) { //如果中間只有間隔一個，那就可以吃
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else {
								return errorPositions;
							}
						} else if(nowP[1] < lastP[1]) { //上一個位置在現在位置的右邊
							int count = 0;
							for(int k = nowP[1]+1; k < lastP[1]; k++) {
								if(position[nowP[0]][k] != 0)
									count++;
							}
							if(count == 1) { //如果中間只有間隔一個，那就可以吃
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else{
								return errorPositions;
							}
						}
					} else if(nowP[1] == lastP[1]) { //i不同，j相同
						if(nowP[0] > lastP[0]) { //上一個位置在現在位置的上面
							int count = 0;
							for(int k = lastP[0]+1; k < nowP[0]; k++) {
								if(position[k][lastP[1]] != 0)
									count++;
							}
							if(count == 1) { //如果中間只有間隔一個，那就可以吃
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else{
								return errorPositions;
							}
						} else if(nowP[0] < lastP[0]) { //上一個位置在現在位置的下面
							int count = 0;
							for(int k = nowP[0]+1; k < lastP[0]; k++) {
								if(position[k][lastP[1]] != 0)
									count++;
							}
							if(count == 1) { //如果中間只有間隔一個，那就可以吃
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else{
								return errorPositions;
							}
						}
					}
				} else if(position[nowP[0]][nowP[1]] == 14 && position[lastP[0]][lastP[1]] == 1 && (lastP[0]-nowP[0] == 1|| nowP[0]-lastP[0] == 1 || lastP[1]-nowP[1] == 1 || nowP[1] - lastP[1] == 1)) { //上一個位置:黑卒，現在位置:紅帥
					position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
					position[lastP[0]][lastP[1]] = 0;
					countPeace=0;
					return position;
				} else if((position[nowP[0]][nowP[1]] - 7) <= position[lastP[0]][lastP[1]]  && (position[nowP[0]][nowP[1]] - 7) > 0 && (lastP[0]-nowP[0] == 1|| nowP[0]-lastP[0] == 1 || lastP[1]-nowP[1] == 1 || nowP[1] - lastP[1] == 1)) {  //上一個位置:黑的(非包)，現在位置:紅的 
					if(!(position[nowP[0]][nowP[1]] == 8 && position[lastP[0]][lastP[1]] == 7)) { //若不是黑將對紅兵，那就可以吃
						position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
						position[lastP[0]][lastP[1]] = 0;
						countPeace=0;
						return position;
					} else {
						return errorPositions;
					}
				}
			} else if(camp == 1) {
				if((position[lastP[0]][lastP[1]]) == 9 && position[nowP[0]][nowP[1]] <= 7) { //上一個位置:紅炮，現在的位置:黑的
					if(nowP[0] == lastP[0]) { //i相同，j不同
						if(nowP[1] > lastP[1]) { //上一個位置在現在位置的左邊
							int count = 0;
							for(int k = lastP[1]+1; k < nowP[1]; k++) {
								if(position[nowP[0]][k] != 0)
									count++;
							}
							if(count == 1) { //如果中間只有間隔一個，那就可以吃
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else {
								return errorPositions;
							}
						} else if(nowP[1] < lastP[1]) { //上一個位置在現在位置的右邊
							int count = 0;
							for(int k = nowP[1]+1; k < lastP[1]; k++) {
								if(position[nowP[0]][k] != 0)
									count++;
							}
							if(count == 1) { //如果中間只有間隔一個，那就可以吃
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else {
								return errorPositions;
							}
						}
					} else if(nowP[1] == lastP[1]) { //i不同，j相同
						if(nowP[0] > lastP[0]) { //上一個位置在現在位置的上面
							int count = 0;
							for(int k = lastP[0]+1; k < nowP[0]; k++) {
								if(position[k][lastP[1]] != 0)
									count++;
							}
							if(count == 1) { //如果中間只有間隔一個，那就可以吃
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else {
								return errorPositions;
							}
						} else if(nowP[0] < lastP[0]) { //上一個位置在現在位置的下面
							int count = 0;
							for(int k = nowP[0]+1; k < lastP[0]; k++) {
								if(position[k][lastP[1]] != 0)
									count++;
							}
							if(count == 1) { //如果中間只有間隔一個，那就可以吃
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else {
								return errorPositions;
							}
						}
					}
				} else if(position[nowP[0]][nowP[1]] == 7 && position[lastP[0]][lastP[1]] == 8 && (lastP[0]-nowP[0] == 1|| nowP[0]-lastP[0] == 1 || lastP[1]-nowP[1] == 1 || nowP[1] - lastP[1] == 1)) { //上一個位置:紅兵，現在位置:黑將
					position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
					position[lastP[0]][lastP[1]] = 0;
					countPeace=0;
					return position;
				} else if((position[nowP[0]][nowP[1]] + 7) <= position[lastP[0]][lastP[1]]  && (position[nowP[0]][nowP[1]] + 7) <= 14 && (lastP[0]-nowP[0] == 1|| nowP[0]-lastP[0] == 1 || lastP[1]-nowP[1] == 1 || nowP[1] - lastP[1] == 1)) {  //上一個位置:紅的(非包)，現在位置:黑的 
					if(!(position[nowP[0]][nowP[1]] == 1 && position[lastP[0]][lastP[1]] == 14)) { //若不是紅帥對黑卒，那就可以吃
						position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
						position[lastP[0]][lastP[1]] = 0;
						countPeace=0;
						return position;
					} else {
						return errorPositions;
					}
				}
			}
		}
		return errorPositions;
	}
	public void clearPeace() {
		countPeace=0;
	}
}
