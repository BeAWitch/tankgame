import javax.swing.*;

public class TankGame extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }

    public TankGame() {
        mp=new MyPanel();
        this.add(mp);// 添加游戏绘图区域
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
