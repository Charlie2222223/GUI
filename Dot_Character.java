import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Dot_Character extends Game {

	/**
	 * アイテム画像
	 */
	Image Dango = Toolkit.getDefaultToolkit().getImage("img/Dango.png");

	/**
	 * 背景の横の大きさ
	 */
	final int BACKGROUND_WIDTH = 1920;

	/**
	 * 背景の縦の大きさ
	 */
	final int BACKGROUND_HEIGHT = 1080;

	/**
	 * キャラクターの左横姿の横の初期値
	 */
	final int FERST_CHARACTER_LEFT_WIDTH = 1;

	/**
	 * キャラクターの左横姿の縦の初期値
	 */
	final int FERST_CHARACTER_LEFT_HEIGHT = 2;

	/**
	 * キャラクターの右横姿の横の初期値
	 */
	final int FERST_CHARACTER_RIGHT_WIDTH = 1;

	/**
	 * キャラクターの右横姿の縦の初期値
	 */
	final int FERST_CHARACTER_RIGHT_HEIGHT = 3;

	/**
	 * 歩く時に画像を動かすカウント
	 */
	int work_Count = 1;

	/**
	 * 歩く距離
	 */
	final int MOVE = 10;

	/**
	 * ジャンプンの高さ
	 */
	final int JUNP_POWOR = 70;

	/**
	 * ジャンプした際に最大どこまで飛べるか
	 */
	final int MAX_JUNP = character_Position_Height - JUNP_POWOR * 2;

	/**
	 * キャラクターの切り分けた画像を格納
	 */
	BufferedImage CharacterImg;

	/**
	 * グラフィックを格納
	 */
	Graphics grp;

	/**
     * お団子の縦軸を格納する配列
     */
    protected ArrayList<Integer> DangoHeight = new ArrayList<>();

    /**
     * お団子の横軸を格納する配列
     */
    protected ArrayList<Integer> DangoWeight = new ArrayList<>();


	/**
	 * お団子をいくつとったかをカウントダウン形式で格納する変数 
	 */	
	int DangoCount = 5;

	/**
	 * お団子の配置場所をランダムで用意する変数
	 */
	Random rm = new Random();

	/**
	 * 時間を格納
	 */
	Timer aTimer = new Timer(true);

	/**
	 * 制限時間
	 */
	int CountDown = 20;

	/** 
	*　カウントダウンする変数 
	*/
	TimerTask CountDowntask = new TimerTask() {
		public void run() {
			if (CountDown > 0) {
				CountDown--;
			} else if(DangoCount == 0){
				aTimer.cancel();
			} else {
				aTimer.cancel();
			}
		}
	};

	/**
	 * お団子の位置を最初に決めるためのメソッド
	 */
	public Dot_Character(){

		RandomDango();

	}

	/**
	 * 背景などすべての画像を画面に出力する
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		grp = g;

		g.setFont(fm);
		g.setColor(Color.BLACK);

		try {
			CharacterImg = character_Trim();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 画像の表示
		g.drawImage(backGround, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, this);

		PrintDango(g);

		try {
			drawCharacterImage(g, character_Trim());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (CountDown == 0) {
			g.drawString("GAMEOVER", 750, 500);
		} else if(DangoCount == 0){
			g.drawString("GAMECLEAR!!", 750, 500);
			aTimer.cancel();
		}else {
			// カウントダウンの描画
			g.drawString(Integer.valueOf(CountDown).toString(), 940, 100);
		}

		DangoJuge();
	}

	/**
	 * 歩いているキャラクターを取得する
	 * 
	 * @return 歩き姿のキャラクター
	 * @throws IOException
	 */
	public BufferedImage Work_Select_Character() throws IOException {
		if (work_Count == 1) {
			character_Width = 1;
			character_Height = 1;
			work_Count++;
		} else if (work_Count == 2) {
			character_Width = 2;
			work_Count++;
		} else {
			character_Width = 3;
			work_Count = 1;
		}

		return character_Trim();
	}

	/**
	 * キー入力されたキャラクターを動かす
	 * 
	 * @param direction どのキーが押されたか
	 * @throws IOException
	 */
	public void key(int direction) throws IOException {

		switch (direction) {
			case 1:
				switch (character_Height) {
					case 2:
						character_Height = 1;
						character_Width = 3;
						JunpCharacter("left");

						break;
					case 3:
						character_Height = 2;
						character_Width = 3;
						JunpCharacter("right");

						break;
				}
				break;

			case 2:
				character_Height = 3;
				if (work_Count == 1) {
					character_Width = 1;
					work_Count++;
				} else if (work_Count == 2) {
					character_Width = 2;
					work_Count++;
				} else {
					character_Width = 3;
					work_Count = 1;
				}
				character_Position_Width += MOVE;
				drawCharacterImage(grp, CharacterImg);
				break;

			case 4:
				character_Height = 2;
				if (work_Count == 1) {
					character_Width = 1;
					work_Count++;
				} else if (work_Count == 2) {
					character_Width = 2;
					work_Count++;
				} else {
					character_Width = 3;
					work_Count = 1;
				}
				character_Position_Width -= MOVE;
				drawCharacterImage(grp, CharacterImg);
				break;

		}

	}

	/**
	 * キャラクターが飛んだ時に使うメソッド
	 * 
	 * @param direction どの方向に飛んだか
	 */
	public void JunpCharacter(String direction) {
		Timer aTimer = new Timer(true);
		TimerTask atask = new TimerTask() {
			@Override
			public void run() {
				DangoJuge();
				if (character_Width == 5) {
					character_Position_Height = FIRST_CHARACTER_POSITION_HEIGHT; // 強制的に床に着地
				} else {
					character_Position_Height -= JUNP_POWOR; // ジャンプの高さ調整
				}
				try {
					CharacterImg = character_Trim();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					drawCharacterImage(grp, CharacterImg);
				} catch (IOException e) {
					e.printStackTrace();
				}
				character_Width++; // 画像を動かす
				if (character_Width == 6 || character_Position_Height < MAX_JUNP) { // 終了判定
					if (direction == "left") { // 左向きの時に飛んだので左に戻る
						character_Height = FERST_CHARACTER_LEFT_HEIGHT;
						character_Width = FERST_CHARACTER_LEFT_WIDTH;
					} else if (direction == "right") { // 右向きに富んだので右に戻る
						character_Height = FERST_CHARACTER_RIGHT_HEIGHT;
						character_Width = FERST_CHARACTER_RIGHT_WIDTH;
					}

					character_Position_Height = FIRST_CHARACTER_POSITION_HEIGHT; // 強制的に床に着地

					try {
						CharacterImg = character_Trim();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						drawCharacterImage(grp, CharacterImg);
					} catch (IOException e) {
						e.printStackTrace();
					}

					aTimer.cancel();
				}

			}
		};
		aTimer.schedule(atask, 0, 180);

	}

	/**
	 * カウントダウンを実行するメソッド
	 */
	public void CountDawn() {

		aTimer.scheduleAtFixedRate(CountDowntask, 1000, 1000);
	}

	/**
	 * お団子の位置をランダムに配列に格納するメソッド
	 */
	public void RandomDango(){

		for(int i = 0; i < 5; i++){
			DangoHeight.add(700 - rm.nextInt(JUNP_POWOR * 3));

			DangoWeight.add(rm.nextInt(1800) + 1);
		}

	}

	/**
	 * お団子を画面に表示させるメソッド
	 * @param g
	 */
	public void PrintDango(Graphics g){
		for(int i = 0; i < DangoCount; i++){
			g.drawImage(Dango, DangoWeight.get(i),  DangoHeight.get(i), 100, 100,null);
		}
	}

	/**
	 * お団子をとったか確認するメソッド
	 */
	public void DangoJuge(){
		for(int i = 0; i < DangoCount; i++){
			int width =  DangoWeight.get(i);
			int height = DangoHeight.get(i);

			if(width - 101 < character_Position_Width && character_Position_Width < width){
				if(height < character_Position_Height && character_Position_Height < height + 101){
					DangoWeight.remove(i);
					DangoHeight.remove(i);
					DangoCount--;
				}
			}
		}
	}

}
