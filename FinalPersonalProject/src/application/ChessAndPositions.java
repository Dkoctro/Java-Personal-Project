package application;

import java.util.Random;

public class ChessAndPositions {
	int height = 4;
	int width = 8;
//	int[][] positions = new int[height][width];
//	int[][] lastPositions = new int[height][width]; 如果要用悔棋才用上去紀錄上一步的陣列的位置
	/*
	 * 黑 紅
	 * 7、14為將
	 * 6、13為士
	 * 5、12為象
	 * 4、11為車
	 * 3、10為馬
	 * 2、9為炮
	 * 1、8為兵
	 * 所以1跟8要有五個、7跟14只有一個，其它的是兩個
	 */
	public void random(int[][] position) {
		Random rand = new Random();
		int count = 0;
		int number = 1;
		while(true){
			int rHeight = rand.nextInt(4);
			int rWidth = rand.nextInt(8);
			switch(number) {
				case 1: //隨機出卒
					if(count == 5) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 2: //隨機出包
					if(count == 2) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 3: //隨機出馬
					if(count == 2) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 4: //隨機出車
					if(count == 2) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 5: //隨機出象
					if(count == 2) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 6: //隨機出士
					if(count == 2) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 7: //隨機出將
					if(count == 1) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 8: //隨機出兵
					if(count == 5) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 9: //隨機出炮
					if(count == 2) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 10: //隨機出傌
					if(count == 2) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 11: //隨機出俥
					if(count == 2) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 12: //隨機出像
					if(count == 2) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 13: //隨機出仕
					if(count == 2) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				case 14: //隨機出帥
					if(count == 1) {
						number++;
						count = 0;
						break;
					} else if(position[rHeight][rWidth] == 0) {
						position[rHeight][rWidth] = number;
						count++;
						break;
					}
					else continue;
				}
			if(number == 15) break; //中止隨機
		}
		
	}
	
	/*
	 * 隨機出棋盤的位置
	 */
	public void reset(int[][] position) {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				position[i][j] = 0;
			}
		}
	}
	/*
	 * 把棋盤位置重新歸零
	 */
}
