package application;

public class CheckWin {
	public boolean check(int camp, int[][] positions) { //camp���{�b�O���Ӱ}��Apositions�O�{�b�ѽL�����p
		int width = 8; //�ѽL���e
		int height = 4; //�ѽL����
		/*
		 * �|���}��i�ӡA����h���P�_
		 * �p�G�O�¦�}��A���N�h�ˬd��ӴѽL�A�p�G���S�����⪺�Ѥl���ܡA���N�O�¦⪺Ĺ(�ݭn���L�S���Ѥl���a��)
		 * �Ϥ��h�O���⪺Ĺ
		 */
		if(camp == 2) { //����
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(positions[i][j] > 7)
						return false;	
				}
			}
			return true;
		} else if(camp == 1) { //�´�
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
