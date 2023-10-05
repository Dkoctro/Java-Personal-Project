package application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Action {
	/*
	 * �Ѥl�����ʸ�Y�l
	 */
	ChessAndPositions CAPositions = new ChessAndPositions();
	
	
	int[][] position = new int[4][8];
	public int countPeace = 0; //�M���P�_����
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
		 * lastP���W�Ӧ�m
		 * nowP���{�b����m
		 * camp���}��
		 */
		if(position[nowP[0]][nowP[1]] == 0) {
			position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]]; //�p�G�O�Ů�A�N�������L�h
			position[lastP[0]][lastP[1]] = 0;
			countPeace++;
			if(countPeace == PEACE) {
				Alert alert = new Alert(AlertType.INFORMATION); //���X�M��
				alert.setTitle("Game Over!");
				alert.setHeaderText("�C�������A�w�g����|�Q�B�B�L½�ѩΦY�l�A�������M��");
				alert.setContentText("�P�¹C��");
				if(alert.showAndWait().get() == ButtonType.OK)
					System.exit(0);
			}
			return position;
		} else {
			if(camp == 2) {
				if((position[lastP[0]][lastP[1]]) == 2 && position[nowP[0]][nowP[1]] > 7) { //�W�@�Ӧ�m:�¥]�A�{�b����m:����
					if(nowP[0] == lastP[0]) { //i�ۦP�Aj���P
						if(nowP[1] > lastP[1]) { //�W�@�Ӧ�m�b�{�b��m������
							int count = 0;
							for(int k = lastP[1]+1; k < nowP[1]; k++) {
								if(position[nowP[0]][k] != 0)
									count++;
							}
							if(count == 1) { //�p�G�����u�����j�@�ӡA���N�i�H�Y
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else {
								return errorPositions;
							}
						} else if(nowP[1] < lastP[1]) { //�W�@�Ӧ�m�b�{�b��m���k��
							int count = 0;
							for(int k = nowP[1]+1; k < lastP[1]; k++) {
								if(position[nowP[0]][k] != 0)
									count++;
							}
							if(count == 1) { //�p�G�����u�����j�@�ӡA���N�i�H�Y
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else{
								return errorPositions;
							}
						}
					} else if(nowP[1] == lastP[1]) { //i���P�Aj�ۦP
						if(nowP[0] > lastP[0]) { //�W�@�Ӧ�m�b�{�b��m���W��
							int count = 0;
							for(int k = lastP[0]+1; k < nowP[0]; k++) {
								if(position[k][lastP[1]] != 0)
									count++;
							}
							if(count == 1) { //�p�G�����u�����j�@�ӡA���N�i�H�Y
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else{
								return errorPositions;
							}
						} else if(nowP[0] < lastP[0]) { //�W�@�Ӧ�m�b�{�b��m���U��
							int count = 0;
							for(int k = nowP[0]+1; k < lastP[0]; k++) {
								if(position[k][lastP[1]] != 0)
									count++;
							}
							if(count == 1) { //�p�G�����u�����j�@�ӡA���N�i�H�Y
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else{
								return errorPositions;
							}
						}
					}
				} else if(position[nowP[0]][nowP[1]] == 14 && position[lastP[0]][lastP[1]] == 1 && (lastP[0]-nowP[0] == 1|| nowP[0]-lastP[0] == 1 || lastP[1]-nowP[1] == 1 || nowP[1] - lastP[1] == 1)) { //�W�@�Ӧ�m:�¨�A�{�b��m:����
					position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
					position[lastP[0]][lastP[1]] = 0;
					countPeace=0;
					return position;
				} else if((position[nowP[0]][nowP[1]] - 7) <= position[lastP[0]][lastP[1]]  && (position[nowP[0]][nowP[1]] - 7) > 0 && (lastP[0]-nowP[0] == 1|| nowP[0]-lastP[0] == 1 || lastP[1]-nowP[1] == 1 || nowP[1] - lastP[1] == 1)) {  //�W�@�Ӧ�m:�ª�(�D�])�A�{�b��m:���� 
					if(!(position[nowP[0]][nowP[1]] == 8 && position[lastP[0]][lastP[1]] == 7)) { //�Y���O�±N����L�A���N�i�H�Y
						position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
						position[lastP[0]][lastP[1]] = 0;
						countPeace=0;
						return position;
					} else {
						return errorPositions;
					}
				}
			} else if(camp == 1) {
				if((position[lastP[0]][lastP[1]]) == 9 && position[nowP[0]][nowP[1]] <= 7) { //�W�@�Ӧ�m:�����A�{�b����m:�ª�
					if(nowP[0] == lastP[0]) { //i�ۦP�Aj���P
						if(nowP[1] > lastP[1]) { //�W�@�Ӧ�m�b�{�b��m������
							int count = 0;
							for(int k = lastP[1]+1; k < nowP[1]; k++) {
								if(position[nowP[0]][k] != 0)
									count++;
							}
							if(count == 1) { //�p�G�����u�����j�@�ӡA���N�i�H�Y
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else {
								return errorPositions;
							}
						} else if(nowP[1] < lastP[1]) { //�W�@�Ӧ�m�b�{�b��m���k��
							int count = 0;
							for(int k = nowP[1]+1; k < lastP[1]; k++) {
								if(position[nowP[0]][k] != 0)
									count++;
							}
							if(count == 1) { //�p�G�����u�����j�@�ӡA���N�i�H�Y
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else {
								return errorPositions;
							}
						}
					} else if(nowP[1] == lastP[1]) { //i���P�Aj�ۦP
						if(nowP[0] > lastP[0]) { //�W�@�Ӧ�m�b�{�b��m���W��
							int count = 0;
							for(int k = lastP[0]+1; k < nowP[0]; k++) {
								if(position[k][lastP[1]] != 0)
									count++;
							}
							if(count == 1) { //�p�G�����u�����j�@�ӡA���N�i�H�Y
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else {
								return errorPositions;
							}
						} else if(nowP[0] < lastP[0]) { //�W�@�Ӧ�m�b�{�b��m���U��
							int count = 0;
							for(int k = nowP[0]+1; k < lastP[0]; k++) {
								if(position[k][lastP[1]] != 0)
									count++;
							}
							if(count == 1) { //�p�G�����u�����j�@�ӡA���N�i�H�Y
								position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
								position[lastP[0]][lastP[1]] = 0;
								countPeace=0;
								return position;
							} else {
								return errorPositions;
							}
						}
					}
				} else if(position[nowP[0]][nowP[1]] == 7 && position[lastP[0]][lastP[1]] == 8 && (lastP[0]-nowP[0] == 1|| nowP[0]-lastP[0] == 1 || lastP[1]-nowP[1] == 1 || nowP[1] - lastP[1] == 1)) { //�W�@�Ӧ�m:���L�A�{�b��m:�±N
					position[nowP[0]][nowP[1]] = position[lastP[0]][lastP[1]];
					position[lastP[0]][lastP[1]] = 0;
					countPeace=0;
					return position;
				} else if((position[nowP[0]][nowP[1]] + 7) <= position[lastP[0]][lastP[1]]  && (position[nowP[0]][nowP[1]] + 7) <= 14 && (lastP[0]-nowP[0] == 1|| nowP[0]-lastP[0] == 1 || lastP[1]-nowP[1] == 1 || nowP[1] - lastP[1] == 1)) {  //�W�@�Ӧ�m:����(�D�])�A�{�b��m:�ª� 
					if(!(position[nowP[0]][nowP[1]] == 1 && position[lastP[0]][lastP[1]] == 14)) { //�Y���O���ӹ�¨�A���N�i�H�Y
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
