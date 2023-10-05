package application;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {
	/*
	 * 這邊是按鈕的動作
	 */
	@FXML
	Button button1;
	@FXML
	Button button2;
	@FXML
	Button button3;
	@FXML
	Button button4;
	@FXML
	Button button5;
	@FXML
	Button button6;
	@FXML
	Button button7;
	@FXML
	Button button8;
	@FXML
	Button button9;
	@FXML
	Button button10;
	@FXML
	Button button11;
	@FXML
	Button button12;
	@FXML
	Button button13;
	@FXML
	Button button14;
	@FXML
	Button button15;
	@FXML
	Button button16;
	@FXML
	Button button17;
	@FXML
	Button button18;
	@FXML
	Button button19;
	@FXML
	Button button20;
	@FXML
	Button button21;
	@FXML
	Button button22;
	@FXML
	Button button23;
	@FXML
	Button button24;
	@FXML
	Button button25;
	@FXML
	Button button26;
	@FXML
	Button button27;
	@FXML
	Button button28;
	@FXML
	Button button29;
	@FXML
	Button button30;
	@FXML
	Button button31;
	@FXML
	Button button32;
	@FXML
	Button gameStart;
	@FXML 
	TextField P1;
	@FXML 
	TextField P2;
	@FXML
	ImageView imageView1;
	@FXML
	ImageView imageView2;
	@FXML
	ImageView Left;
	@FXML
	ImageView Right;
	@FXML
	Label name1;
	@FXML
	Label name2;
	
	int height = 4; //棋盤的長
	int width = 8; //棋盤的寬
	int[][] judgeOpen = new int[height][width]; //判斷是否已翻牌
	int actionMode = 0; //判斷是第一次按還是第二次按
	int camp = 1; //陣營
	int[] lastPosition = {0, 0}; //上一個位置
	int[] nowPosition = {0, 0};	//現在位置
	int[] playerCamp = new int[2]; //紀錄玩家的陣營，玩家1是playerCamp[0]，玩家2是playerCamp[1]
	int[][] positions = new int[height][width]; //紀錄現在的位置狀況
	
	ChessAndPositions CAPositions = new ChessAndPositions();
	CheckWin CWin = new CheckWin();
	Action action = new Action();
	
	public static String player1; //玩家1
	public static String player2; //玩家2
	
	public void confirm(ActionEvent event) throws IOException{
		Alert rule = new Alert(AlertType.INFORMATION);
		rule.setTitle("RULE");
		rule.setHeaderText("規則講解:");
		rule.setContentText("1.把敵方的子都吃光即獲勝\n"
						  + "2.將(帥)>士(仕)>象(像)>車(俥)>馬(傌)>卒(兵)，但卒(兵)又可以吃帥(將)\n"
						  + "3.包(炮)只有在要吃的子跟自己有隔一個(不論明暗)的情況下才可以吃\n"
						  + "4.連續四十步不吃子或翻棋將和局\n"
						  + "5.不可悔棋\n");
		rule.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		rule.showAndWait();
		player1 = P1.getText();
		player2 = P2.getText();	
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("GamingScene.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setX(300);
		stage.setY(50);
		stage.show();
	}
	/*
	 * 去呼叫遊戲畫面，並將棋子都隨機好
	 */
	
	public void setting() {
		setJudge(); //初始化判斷翻牌
		CAPositions.reset(positions); //初始化棋子
		CAPositions.random(positions); //隨機棋子
		gameStart.setVisible(false); //按下遊戲開始按鈕後消失
		action.countPeace = 0; //將和局計算設置為0
		Right.setStyle("-fx-opacity:0;"); //設置箭頭
		action.setError(); //設置錯誤訊息
		name1.setText(player1);
		name2.setText(player2);
	}
	public void setJudge() { //重設翻棋判斷
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				judgeOpen[i][j] = 0;
			}
		}
	}
	public void setPositions(int[][] actionPositions) { //將算出來的位置填到positions來記錄
		for(int i = 0 ; i < height; i++) {
			for(int j = 0; j < width; j++) {
				positions[i][j] = actionPositions[i][j];
			}
		}
	}
	
	public void setChess(int positions, Button button) { //設置翻開的圖片
		Image image1 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\黑卒.png");
		Image image2 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\黑包.png");
		Image image3 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\黑馬.png");
		Image image4 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\黑車.png");
		Image image5 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\黑象.png");
		Image image6 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\黑士.png");
		Image image7 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\黑將.png");
		Image image8 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\紅兵.png");
		Image image9 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\紅炮.png");
		Image image10 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\紅傌.png");
		Image image11 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\紅俥.png");
		Image image12 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\紅像.png");
		Image image13 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\紅仕.png");
		Image image14 = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\紅帥.png");
		ImageView imageView1 = new ImageView(image1);
		ImageView imageView2 = new ImageView(image2);
		ImageView imageView3 = new ImageView(image3);
		ImageView imageView4 = new ImageView(image4);
		ImageView imageView5 = new ImageView(image5);
		ImageView imageView6 = new ImageView(image6);
		ImageView imageView7 = new ImageView(image7);
		ImageView imageView8 = new ImageView(image8);
		ImageView imageView9 = new ImageView(image9);
		ImageView imageView10 = new ImageView(image10);
		ImageView imageView11 = new ImageView(image11);
		ImageView imageView12 = new ImageView(image12);
		ImageView imageView13 = new ImageView(image13);
		ImageView imageView14 = new ImageView(image14);
		switch(positions) { //根據位置是甚麼數字來放圖片
			case 1:
				button.setGraphic(imageView1);
				imageView1.setFitHeight(70);
				imageView1.setFitWidth(70);
				break;
			case 2:
				button.setGraphic(imageView2);
				imageView2.setFitHeight(70);
				imageView2.setFitWidth(70);
				break;
			case 3:
				button.setGraphic(imageView3);
				imageView3.setFitHeight(70);
				imageView3.setFitWidth(70);
				break;
			case 4:
				button.setGraphic(imageView4);
				imageView4.setFitHeight(70);
				imageView4.setFitWidth(70);
				break;
			case 5:
				button.setGraphic(imageView5);
				imageView5.setFitHeight(70);
				imageView5.setFitWidth(70);
				break;
			case 6:
				button.setGraphic(imageView6);
				imageView6.setFitHeight(70);
				imageView6.setFitWidth(70);
				break;
			case 7:
				button.setGraphic(imageView7);
				imageView7.setFitHeight(70);
				imageView7.setFitWidth(70);
				break;
			case 8:
				button.setGraphic(imageView8);
				imageView8.setFitHeight(70);
				imageView8.setFitWidth(70);
				break;
			case 9:
				button.setGraphic(imageView9);
				imageView9.setFitHeight(70);
				imageView9.setFitWidth(70);
				break;
			case 10:
				button.setGraphic(imageView10);
				imageView10.setFitHeight(70);
				imageView10.setFitWidth(70);
				break;
			case 11:
				button.setGraphic(imageView11);
				imageView11.setFitHeight(70);
				imageView11.setFitWidth(70);
				break;
			case 12:
				button.setGraphic(imageView12);
				imageView12.setFitHeight(70);
				imageView12.setFitWidth(70);
				break;
			case 13:
				button.setGraphic(imageView13);
				imageView13.setFitHeight(70);
				imageView13.setFitWidth(70);
				break;
			case 14:
				button.setGraphic(imageView14);
				imageView14.setFitHeight(70);
				imageView14.setFitWidth(70);
				break;
		}
	}
	/*
	 * 把所有按鈕按照隨機出來的位置放入相對應的棋子
	 * 如果是可以放的，那就放
	 * 不能的話，甚麼也不會發生
	 */
	int firstOpen = 0; //是否是第一次翻棋的變數
	public void checkFirstOpen(int position) { //確認是否是第一次按按鈕，需要決定陣營
		Image imageBlack = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\陣營(黑).png");
		Image imageRed = new Image("file:C:\\Users\\kemen\\Desktop\\Programming\\For Java\\Java Programming\\FinalPersonalProject\\src\\application\\陣營(紅).png");
		if(firstOpen == 0) {
			if(position > 7) {
				camp = 1; //紅的
				playerCamp[0] = 1;
				playerCamp[1] = 2;
				firstOpen = 1;
				imageView1.setImage(imageRed);
				imageView2.setImage(imageBlack);
			}
			else {
				camp = 2; //黑的
				playerCamp[0] = 2;
				playerCamp[1] = 1;
				firstOpen = 1;
				imageView1.setImage(imageBlack);
				imageView2.setImage(imageRed);
			}
		}
	}
	
	public void winner(int camp) { //確定是誰贏
		if(camp == playerCamp[0]) {	
			String player = player1;
			alertCreate(player);
		} else if(camp == playerCamp[1]) {
			String player = player2;
			alertCreate(player);
		}
	}
	
	public void alertCreate(String player) { //建立alert
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over!");
		alert.setHeaderText("遊戲已結束，此次為" + player + "獲勝");
		alert.setContentText("感謝遊玩");
		if(alert.showAndWait().get() == ButtonType.OK) {
			System.exit(0);
		}
	}
	String nowPlayer = player1;
	public void changePlayer() { //變換箭頭方向
		if(nowPlayer.equals(player1)) {
			Left.setStyle("-fx-opacity:0;");
			Right.setStyle("-fx-opacity:1;");
			nowPlayer = player2;
		} else if(nowPlayer.equals(player2)){
			Left.setStyle("-fx-opacity:1;");
			Right.setStyle("-fx-opacity:0;");
			nowPlayer = player1;
		}
		if(camp == 1) camp = 2;
		else if(camp == 2) camp = 1;
	}
	public void clearBackground() { //將邊界發光取消
		button1.setStyle("-fx-background-color: transparent; ");
		button2.setStyle("-fx-background-color: transparent; ");
		button3.setStyle("-fx-background-color: transparent; "); 
		button4.setStyle("-fx-background-color: transparent; "); 
		button5.setStyle("-fx-background-color: transparent; "); 
		button6.setStyle("-fx-background-color: transparent; "); 
		button7.setStyle("-fx-background-color: transparent; "); 
		button8.setStyle("-fx-background-color: transparent; "); 
		button9.setStyle("-fx-background-color: transparent; "); 
		button10.setStyle("-fx-background-color: transparent; "); 
		button11.setStyle("-fx-background-color: transparent; "); 
		button12.setStyle("-fx-background-color: transparent; "); 
		button13.setStyle("-fx-background-color: transparent; "); 
		button14.setStyle("-fx-background-color: transparent; "); 
		button15.setStyle("-fx-background-color: transparent; "); 
		button16.setStyle("-fx-background-color: transparent; "); 
		button17.setStyle("-fx-background-color: transparent; "); 
		button18.setStyle("-fx-background-color: transparent; "); 
		button19.setStyle("-fx-background-color: transparent; "); 
		button20.setStyle("-fx-background-color: transparent; "); 
		button21.setStyle("-fx-background-color: transparent; "); 
		button22.setStyle("-fx-background-color: transparent; "); 
		button23.setStyle("-fx-background-color: transparent; "); 
		button24.setStyle("-fx-background-color: transparent; "); 
		button25.setStyle("-fx-background-color: transparent; "); 
		button26.setStyle("-fx-background-color: transparent; "); 
		button27.setStyle("-fx-background-color: transparent; "); 
		button28.setStyle("-fx-background-color: transparent; "); 
		button29.setStyle("-fx-background-color: transparent; "); 
		button30.setStyle("-fx-background-color: transparent; "); 
		button31.setStyle("-fx-background-color: transparent; "); 
		button32.setStyle("-fx-background-color: transparent; "); 
	}
	public void setChessZero(int[][] positions) { //將空白子的圖片拿掉
		ImageView imageViewChess = new ImageView();
		if(positions[0][0] == 0) {button1.setGraphic(imageViewChess);}
		if(positions[0][1] == 0) {button2.setGraphic(imageViewChess);}
		if(positions[0][2] == 0) {button3.setGraphic(imageViewChess);}
		if(positions[0][3] == 0) {button4.setGraphic(imageViewChess);}
		if(positions[0][4] == 0) {button5.setGraphic(imageViewChess);}
		if(positions[0][5] == 0) {button6.setGraphic(imageViewChess);}
		if(positions[0][6] == 0) {button7.setGraphic(imageViewChess);}
		if(positions[0][7] == 0) {button8.setGraphic(imageViewChess);}
		if(positions[1][0] == 0) {button9.setGraphic(imageViewChess);}
		if(positions[1][1] == 0) {button10.setGraphic(imageViewChess);}
		if(positions[1][2] == 0) {button11.setGraphic(imageViewChess);}
		if(positions[1][3] == 0) {button12.setGraphic(imageViewChess);}
		if(positions[1][4] == 0) {button13.setGraphic(imageViewChess);}
		if(positions[1][5] == 0) {button14.setGraphic(imageViewChess);}
		if(positions[1][6] == 0) {button15.setGraphic(imageViewChess);}
		if(positions[1][7] == 0) {button16.setGraphic(imageViewChess);}
		if(positions[2][0] == 0) {button17.setGraphic(imageViewChess);}
		if(positions[2][1] == 0) {button18.setGraphic(imageViewChess);}
		if(positions[2][2] == 0) {button19.setGraphic(imageViewChess);}
		if(positions[2][3] == 0) {button20.setGraphic(imageViewChess);}
		if(positions[2][4] == 0) {button21.setGraphic(imageViewChess);}
		if(positions[2][5] == 0) {button22.setGraphic(imageViewChess);}
		if(positions[2][6] == 0) {button23.setGraphic(imageViewChess);}
		if(positions[2][7] == 0) {button24.setGraphic(imageViewChess);}
		if(positions[3][0] == 0) {button25.setGraphic(imageViewChess);}
		if(positions[3][1] == 0) {button26.setGraphic(imageViewChess);}
		if(positions[3][2] == 0) {button27.setGraphic(imageViewChess);}
		if(positions[3][3] == 0) {button28.setGraphic(imageViewChess);}
		if(positions[3][4] == 0) {button29.setGraphic(imageViewChess);}
		if(positions[3][5] == 0) {button30.setGraphic(imageViewChess);}
		if(positions[3][6] == 0) {button31.setGraphic(imageViewChess);}
		if(positions[3][7] == 0) {button32.setGraphic(imageViewChess);} 
	}
	
	int[][] actionMove = new int[4][8];
	boolean trueOfFalse = false;
	public void action1(ActionEvent event) {
		if(judgeOpen[0][0] == 0) { //把棋子翻開
			judgeOpen[0][0] = 1;
			setChess(positions[0][0], button1);
			checkFirstOpen(positions[0][0]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[0][0] == 1) {
			if(actionMode == 0) {
				button1.setStyle("-fx-background-color: DODGERBLUE; ");  //設立邊界(發光)，表示選取
				lastPosition[0] = 0; //第一個位置的i點
				lastPosition[1] = 0; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 0; //第二個位置的i點
				nowPosition[1] = 0; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[0][0], button1);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer(); 
				} 
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
					
			}
		}
	}
	/*
	 * 棋子的動作1
	 */
	public void action2(ActionEvent event) {
		if(judgeOpen[0][1] == 0) {
			judgeOpen[0][1] = 1;
			setChess(positions[0][1], button2);
			checkFirstOpen(positions[0][1]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[0][1] == 1) {
			if(actionMode == 0) {
				button2.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 0; //第一個位置的i點
				lastPosition[1] = 1; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 0; //第二個位置的i點
				nowPosition[1] = 1; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0]!= -1) {
					setPositions(actionMove);
					setChess(positions[0][1], button2);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作2
	 */
	public void action3(ActionEvent event) {
		if(judgeOpen[0][2] == 0) {
			judgeOpen[0][2] = 1;
			setChess(positions[0][2], button3);
			checkFirstOpen(positions[0][2]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[0][2] == 1) {
			if(actionMode == 0) {
				button3.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 0; //第一個位置的i點
				lastPosition[1] = 2; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 0; //第二個位置的i點
				nowPosition[1] = 2; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[0][2], button3);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作3
	 */
	public void action4(ActionEvent event) {
		if(judgeOpen[0][3] == 0) {
			judgeOpen[0][3] = 1;
			setChess(positions[0][3], button4);
			checkFirstOpen(positions[0][3]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[0][3] == 1) {
			if(actionMode == 0) {
				button4.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 0; //第一個位置的i點
				lastPosition[1] = 3; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 0; //第二個位置的i點
				nowPosition[1] = 3; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0]!= -1) {
					setPositions(actionMove);
					setChess(positions[0][3], button4);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作4
	 */
	public void action5(ActionEvent event) {
		if(judgeOpen[0][4] == 0) {
			judgeOpen[0][4] = 1;
			setChess(positions[0][4], button5);
			checkFirstOpen(positions[0][4]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[0][4] == 1) {
			if(actionMode == 0) {
				button5.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 0; //第一個位置的i點
				lastPosition[1] = 4; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 0; //第二個位置的i點
				nowPosition[1] = 4; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[0][4], button5);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作5
	 */
	public void action6(ActionEvent event) {
		if(judgeOpen[0][5] == 0) {
			judgeOpen[0][5] = 1;
			setChess(positions[0][5], button6);
			checkFirstOpen(positions[0][5]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[0][5] == 1) {
			if(actionMode == 0) {
				button6.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 0; //第一個位置的i點
				lastPosition[1] = 5; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 0; //第二個位置的i點
				nowPosition[1] = 5; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[0][5], button6);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作6
	 */
	public void action7(ActionEvent event) {
		if(judgeOpen[0][6] == 0) {
			judgeOpen[0][6] = 1;
			setChess(positions[0][6], button7);
			checkFirstOpen(positions[0][6]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[0][6] == 1) {
			if(actionMode == 0) {
				button7.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 0; //第一個位置的i點
				lastPosition[1] = 6; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 0; //第二個位置的i點
				nowPosition[1] = 6; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[0][6], button7);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作7
	 */
	public void action8(ActionEvent event) {
		if(judgeOpen[0][7] == 0) {
			judgeOpen[0][7] = 1;
			setChess(positions[0][7], button8);
			checkFirstOpen(positions[0][7]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[0][7] == 1) {
			if(actionMode == 0) {
				button8.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 0; //第一個位置的i點
				lastPosition[1] = 7; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 0; //第二個位置的i點
				nowPosition[1] = 7; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[0][7], button8);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作8
	 */
	public void action9(ActionEvent event) {
		if(judgeOpen[1][0] == 0) {
			judgeOpen[1][0] = 1;
			setChess(positions[1][0], button9);
			checkFirstOpen(positions[1][0]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[1][0] == 1) {
			if(actionMode == 0) {
				button9.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 1; //第一個位置的i點
				lastPosition[1] = 0; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 1; //第二個位置的i點
				nowPosition[1] = 0; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[1][0], button9);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作9
	 */
	public void action10(ActionEvent event) {
		if(judgeOpen[1][1] == 0) {
			judgeOpen[1][1] = 1;
			setChess(positions[1][1], button10);
			checkFirstOpen(positions[1][1]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[1][1] == 1) {
			if(actionMode == 0) {
				button10.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 1; //第一個位置的i點
				lastPosition[1] = 1; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 1; //第二個位置的i點
				nowPosition[1] = 1; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[1][1], button10);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作10
	 */
	public void action11(ActionEvent event) {
		if(judgeOpen[1][2] == 0) {
			judgeOpen[1][2] = 1;
			setChess(positions[1][2], button11);
			checkFirstOpen(positions[1][2]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[1][2] == 1) {
			if(actionMode == 0) {
				button11.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 1; //第一個位置的i點
				lastPosition[1] = 2; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 1; //第二個位置的i點
				nowPosition[1] = 2; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[1][2], button11);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();	
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作11
	 */
	public void action12(ActionEvent event) {
		if(judgeOpen[1][3] == 0) {
			judgeOpen[1][3] = 1;
			setChess(positions[1][3], button12);
			checkFirstOpen(positions[1][3]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[1][3] == 1) {
			if(actionMode == 0) {
				button12.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 1; //第一個位置的i點
				lastPosition[1] = 3; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 1; //第二個位置的i點
				nowPosition[1] = 3; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[1][3], button12);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作12
	 */
	public void action13(ActionEvent event) {
		if(judgeOpen[1][4] == 0) {
			judgeOpen[1][4] = 1;
			setChess(positions[1][4], button13);
			checkFirstOpen(positions[1][4]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[1][4] == 1) {
			if(actionMode == 0) {
				button13.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 1; //第一個位置的i點
				lastPosition[1] = 4; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 1; //第二個位置的i點
				nowPosition[1] = 4; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[1][4], button13);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作13
	 */
	public void action14(ActionEvent event) {
		if(judgeOpen[1][5] == 0) {
			judgeOpen[1][5] = 1;
			setChess(positions[1][5], button14);
			checkFirstOpen(positions[1][5]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[1][5] == 1) {
			if(actionMode == 0) {
				button14.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 1; //第一個位置的i點
				lastPosition[1] = 5; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 1; //第二個位置的i點
				nowPosition[1] = 5; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					changePlayer();
					setPositions(actionMove);
					setChess(positions[1][5], button14);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作14
	 */
	public void action15(ActionEvent event) {
		if(judgeOpen[1][6] == 0) {
			judgeOpen[1][6] = 1;
			setChess(positions[1][6], button15);
			checkFirstOpen(positions[1][6]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[1][6] == 1) {
			if(actionMode == 0) {
				button15.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 1; //第一個位置的i點
				lastPosition[1] = 6; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 1; //第二個位置的i點
				nowPosition[1] = 6; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[1][6], button15);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作15
	 */
	public void action16(ActionEvent event) {
		if(judgeOpen[1][7] == 0) {
			judgeOpen[1][7] = 1;
			setChess(positions[1][7], button16);
			checkFirstOpen(positions[1][7]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[1][7] == 1) {
			if(actionMode == 0) {
				button16.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 1; //第一個位置的i點
				lastPosition[1] = 7; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 1; //第二個位置的i點
				nowPosition[1] = 7; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[1][7], button16);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作16
	 */
	public void action17(ActionEvent event) {
		if(judgeOpen[2][0] == 0) {
			judgeOpen[2][0] = 1;
			setChess(positions[2][0], button17);
			checkFirstOpen(positions[2][0]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[2][0] == 1) {
			if(actionMode == 0) {
				button17.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 2; //第一個位置的i點
				lastPosition[1] = 0; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 2; //第二個位置的i點
				nowPosition[1] = 0; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[2][0], button17);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作17
	 */
	public void action18(ActionEvent event) {
		if(judgeOpen[2][1] == 0) {
			judgeOpen[2][1] = 1;
			setChess(positions[2][1], button18);
			checkFirstOpen(positions[2][1]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[2][1] == 1) {
			if(actionMode == 0) {
				button18.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 2; //第一個位置的i點
				lastPosition[1] = 1; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 2; //第二個位置的i點
				nowPosition[1] = 1; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[2][1], button18);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作18
	 */
	public void action19(ActionEvent event) {
		if(judgeOpen[2][2] == 0) {
			judgeOpen[2][2] = 1;
			setChess(positions[2][2], button19);
			checkFirstOpen(positions[2][2]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[2][2] == 1) {
			if(actionMode == 0) {
				button19.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 2; //第一個位置的i點
				lastPosition[1] = 2; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 2; //第二個位置的i點
				nowPosition[1] = 2; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[2][2], button19);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作19
	 */
	public void action20(ActionEvent event) {
		if(judgeOpen[2][3] == 0) {
			judgeOpen[2][3] = 1;
			setChess(positions[2][3], button20);
			checkFirstOpen(positions[2][3]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[2][3] == 1) {
			if(actionMode == 0) {
				button20.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 2; //第一個位置的i點
				lastPosition[1] = 3; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 2; //第二個位置的i點
				nowPosition[1] = 3; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[2][3], button20);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作20
	 */
	public void action21(ActionEvent event) {
		if(judgeOpen[2][4] == 0) {
			judgeOpen[2][4] = 1;
			setChess(positions[2][4], button21);
			checkFirstOpen(positions[2][4]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[2][4] == 1) {
			if(actionMode == 0) {
				button21.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 2; //第一個位置的i點
				lastPosition[1] = 4; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 2; //第二個位置的i點
				nowPosition[1] = 4; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[2][4], button21);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作21
	 */
	public void action22(ActionEvent event) {
		if(judgeOpen[2][5] == 0) {
			judgeOpen[2][5] = 1;
			setChess(positions[2][5], button22);
			checkFirstOpen(positions[2][5]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[2][5] == 1) {
			if(actionMode == 0) {
				button22.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 2; //第一個位置的i點
				lastPosition[1] = 5; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 2; //第二個位置的i點
				nowPosition[1] = 5; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[2][5], button22);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作22
	 */
	public void action23(ActionEvent event) {
		if(judgeOpen[2][6] == 0) {
			judgeOpen[2][6] = 1;
			setChess(positions[2][6], button23);
			checkFirstOpen(positions[2][6]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[2][6] == 1) {
			if(actionMode == 0) {
				button23.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 2; //第一個位置的i點
				lastPosition[1] = 6; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 2; //第二個位置的i點
				nowPosition[1] = 6; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[2][6], button23);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作23
	 */
	public void action24(ActionEvent event) {
		if(judgeOpen[2][7] == 0) {
			judgeOpen[2][7] = 1;
			setChess(positions[2][7], button24);
			checkFirstOpen(positions[2][7]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[2][7] == 1) {
			if(actionMode == 0) {
				button24.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 2; //第一個位置的i點
				lastPosition[1] = 7; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 2; //第二個位置的i點
				nowPosition[1] = 7; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[2][7], button24);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作24
	 */
	public void action25(ActionEvent event) {
		if(judgeOpen[3][0] == 0) {
			judgeOpen[3][0] = 1;
			setChess(positions[3][0], button25);
			checkFirstOpen(positions[3][0]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[3][0] == 1) {
			if(actionMode == 0) {
				button25.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 3; //第一個位置的i點
				lastPosition[1] = 0; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 3; //第二個位置的i點
				nowPosition[1] = 0; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[3][0], button25);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作25
	 */
	public void action26(ActionEvent event) {
		if(judgeOpen[3][1] == 0) {
			judgeOpen[3][1] = 1;
			setChess(positions[3][1], button26);
			checkFirstOpen(positions[3][1]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[3][1] == 1) {
			if(actionMode == 0) {
				button26.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 3; //第一個位置的i點
				lastPosition[1] = 1; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 3; //第二個位置的i點
				nowPosition[1] = 1; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[3][1], button26);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作26
	 */
	public void action27(ActionEvent event) {
		if(judgeOpen[3][2] == 0) {
			judgeOpen[3][2] = 1;
			setChess(positions[3][2], button27);
			checkFirstOpen(positions[3][2]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[3][2] == 1) {
			if(actionMode == 0) {
				button27.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 3; //第一個位置的i點
				lastPosition[1] = 2; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 3; //第二個位置的i點
				nowPosition[1] = 2; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[3][2], button27);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作27
	 */
	public void action28(ActionEvent event) {
		if(judgeOpen[3][3] == 0) {
			judgeOpen[3][3] = 1;
			setChess(positions[3][3], button28);
			checkFirstOpen(positions[3][3]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[3][3] == 1) {
			if(actionMode == 0) {
				button28.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 3; //第一個位置的i點
				lastPosition[1] = 3; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 3; //第二個位置的i點
				nowPosition[1] = 3; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[3][3], button28);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作28
	 */
	public void action29(ActionEvent event) {
		if(judgeOpen[3][4] == 0) {
			judgeOpen[3][4] = 1;
			setChess(positions[3][4], button29);
			checkFirstOpen(positions[3][4]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[3][4] == 1) {
			if(actionMode == 0) {
				button29.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 3; //第一個位置的i點
				lastPosition[1] = 4; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 3; //第二個位置的i點
				nowPosition[1] = 4; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[3][4], button29);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作29
	 */
	public void action30(ActionEvent event) {
		if(judgeOpen[3][5] == 0) {
			judgeOpen[3][5] = 1;
			setChess(positions[3][5], button30);
			checkFirstOpen(positions[3][5]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[3][5] == 1) {
			if(actionMode == 0) {
				button30.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 3; //第一個位置的i點
				lastPosition[1] = 5; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 3; //第二個位置的i點
				nowPosition[1] = 5; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[3][5], button30);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作30
	 */
	public void action31(ActionEvent event) {
		if(judgeOpen[3][6] == 0) {
			judgeOpen[3][6] = 1;
			setChess(positions[3][6], button31);
			checkFirstOpen(positions[3][6]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[3][6] == 1) {
			if(actionMode == 0) {
				button31.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 3; //第一個位置的i點
				lastPosition[1] = 6; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 3; //第二個位置的i點
				nowPosition[1] = 6; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[3][6], button31);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作31
	 */
	public void action32(ActionEvent event) {
		if(judgeOpen[3][7] == 0) {
			judgeOpen[3][7] = 1;
			setChess(positions[3][7], button32);
			checkFirstOpen(positions[3][7]);
			changePlayer();
			clearBackground();
			action.clearPeace();
			actionMode = 0; //變回第一次按按鈕
		}
		else if(judgeOpen[3][7] == 1) {
			if(actionMode == 0) {
				button32.setStyle("-fx-background-color: DODGERBLUE; ");   //設立邊界(發光)，表示選取
				lastPosition[0] = 3; //第一個位置的i點
				lastPosition[1] = 7; //第一個位置的j點
				actionMode = 1; //變成第二次按按鈕
			} else if(actionMode == 1) {
				nowPosition[0] = 3; //第二個位置的i點
				nowPosition[1] = 7; //第二個位置的j點
				actionMove = action.moveOrEat(lastPosition, nowPosition, camp, positions);
				if(actionMove[0][0] != -1) {
					setPositions(actionMove);
					setChess(positions[3][7], button32);
					setChessZero(positions);
					trueOfFalse = CWin.check(camp, positions); 
					if(trueOfFalse) winner(camp);
					changePlayer();
				}
				clearBackground();
				actionMode = 0; //變回第一次按按鈕
			}
		}
	}
	/*
	 * 棋子的動作32
	 */
}