import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Title extends Game {

	/**
	 * 画面に表示させる文字列を格納
	 */
	String Entercric = "Enterを入力して始めよう!!";

	/**
	 * キャラクターの動きかたが3パターンあるのでそれをカウントするための変数
	 */
	int CharacterCount = 1;

	/**
	 * Enterを点滅をon.offするための変数
	 */
	int LetterFlashing = 1;

	/**
	 * 背景などすべての画像を画面に出力する
	 */
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Font font = new Font("ベストテン", Font.PLAIN, 100);
		
		try {
            CharacterImg = getCharacter();
        } catch (IOException e) {
            e.printStackTrace();
        }

		 getEnter();

		g.drawImage(backGround, 0, 0, 1920, 1080, this);
		

		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("勇者の大冒険", 650, 200);

		g.setFont(fm);
		g.drawString(Entercric, 600,650);

		
		

		try {
			drawCharacterImage(g, character_Trim());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
	}

	/**
	 * キャラクターをその場で動かすメソッド
	 * @return　動くキャラクターの形の配列番号
	 * @throws IOException
	 */
	public BufferedImage getCharacter() throws IOException {
		if (CharacterCount == 1) {
			character_Width = 1;
			character_Height = 1;
			CharacterCount++;
		} else if (CharacterCount == 2) {
			character_Width = 2;
			CharacterCount++;
		} else {
			character_Width = 3;
			CharacterCount = 1;
		}

		return character_Trim();
	}

	/**
	 * Enterを短滅させるためのメソッド
	 */
	public void getEnter(){
		if(LetterFlashing == 1){
			Entercric = "Enterを入力して始めよう!!";
			LetterFlashing++;
		}else{
			Entercric = "";
			LetterFlashing = 1;
		}
	}
}
