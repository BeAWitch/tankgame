import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class TankGame extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }

    public TankGame() {
        mp=new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);// 添加游戏绘图区域
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // 关闭窗口的时事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Recorder.storeRecord(mp.enemyTanks);
            }
        });
    }
}
