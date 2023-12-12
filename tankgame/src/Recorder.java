import item.EnemyTank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Recorder {
    private static int enemyTankNum = 0;
    private static FileWriter fileWriter = null;
    private static BufferedWriter bufferedWriter = null;
    private static String filepath = "./src/record.txt";

    public static void storeRecord(Vector<EnemyTank> enemyTanks) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filepath));
            bufferedWriter.write(enemyTankNum + "\r\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                bufferedWriter.write(enemyTank.getX() + ", "
                        + enemyTank.getY() + ", "
                        + enemyTank.getDirection() + ", "
                        + "\r\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        }
    }

    public static int getEnemyTankNum() {
        return enemyTankNum;
    }

    public static void addEnemyTankNum() {
        enemyTankNum++;
    }
}
