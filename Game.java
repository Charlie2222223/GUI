import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel {

	/**
	 * 背景画像を取得
	 */
	Image backGround = Toolkit.getDefaultToolkit().getImage("img/background.jpg");

	/**
	 * キャラクターの画像パス
	 */
	String CharacterImg_Path = "img/Character.png";

	/**
	 * 画像を格納する変数
	 */
	BufferedImage CharacterImg;

	/**
	 * キャラクター画像の横
	 */
	int character_Width = 1;

	/**
	 * キャラクター画像の縦
	 */
	int character_Height = 1;

	/**
	 * キャラクターの切り分けた一つのピクセル数
	 */
	int character_Img_Size = 128;

	/**
	 * キャラクター画像の縦のピクセル数
	 */
	int character_Img_Height = 512;

	/**
	 * キャラクターの横の初期位置
	 */
	final int FIRST_CHARACTER_POSITION_WIDTH = 900;

	/**
	 *キャラクターの縦の初期位置
	 */
	final int FIRST_CHARACTER_POSITION_HEIGHT = 700;

	/**
	 * キャラクターの横の位置
	 */
	int character_Position_Width = 900;

	/**
	 * キャラクターの縦の位置
	 */
	int character_Position_Height = 700;

    /**
	 * 文字のフォントを格納する変数
	 */
	Font fm = new Font("Serif" , Font.PLAIN , 60);

	/**
	 * キャラクター画像の指定した位置を切り取る
	 * @return	切り取ったキャラクターの画像
	 * @throws IOException
	 */
	public BufferedImage character_Trim() throws IOException {
		File f = new File(CharacterImg_Path);
		BufferedImage read = ImageIO.read(f);
		BufferedImage write = read.getSubimage(0 + (character_Img_Size * (character_Width - 1)),
				0 + (character_Img_Size * (character_Height - 1)), character_Img_Size,
				character_Img_Size);

		return write;
	}


	/**
	 * キャラクターの画像を画面に出力
	 * @param g	画面
	 * @param characterimg　出力するキャラクターの画像
	 * @throws IOException
	 */
	public void drawCharacterImage(Graphics g, BufferedImage characterimg) throws IOException {
		g.drawImage(characterimg, character_Position_Width, character_Position_Height, null);
	}
}
