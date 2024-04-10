import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameWindow extends JFrame  implements KeyListener{
    Dot_Character charcter;
	Title Title;
	int count = 0;

	/**
	 * 画面の設定
	 * @param title　画面の名前
	 * @param width	画面の横幅
	 * @param height　画面の縦幅
	 * @param ch　Dot_Characterクラスをmainから持ってくる
	 * @param tl　Titleクラスをmainから持ってくる
	 * @throws IOException
	 */
    public GameWindow(String title, int width, int height, Dot_Character ch, Title tl) throws IOException {
		
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);


		this.add(tl);

        //キー入力の有効化
        charcter = ch;

		Title = tl;

		addKeyListener(this);

	}

    @Override
	public void keyTyped(KeyEvent e) {
		//使用しないので空にしておきます。
	}

    @Override
	public void keyPressed(KeyEvent e) {
		switch ( e.getKeyCode() ) {
		case KeyEvent.VK_UP:
			//上キー
			try {
				charcter.key(1);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		break;
		case KeyEvent.VK_DOWN:
			//スペースキー
			System.out.println("スペースが押されました");
			break;
		case KeyEvent.VK_LEFT:
        	try {
                    charcter.key(4);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
			break;
			
        case KeyEvent.VK_RIGHT:
                try {
                    charcter.key(2);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
				break;
		case KeyEvent.VK_ENTER:
				this.change();

				break;
		}
	}
 
	@Override
	public void keyReleased(KeyEvent e) {
		
	}


	/**
	 * 画面を切り替えるメソッド
	 */
	public void change() {
		//ContentPaneにはめ込まれたパネルを削除
		getContentPane().removeAll();
		
		if(count == 0){
			super.add(charcter);//パネルの追加
			charcter.CountDawn();
			count++;
		}
		validate();//更新
		repaint();//再描画
	}
    
}
