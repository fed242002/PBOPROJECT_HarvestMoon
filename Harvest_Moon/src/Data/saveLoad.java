package Data;

import Main.GamePanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class saveLoad {
    GamePanel gp;

    public saveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public void save() {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")))) {
                dataStorage ds = new dataStorage();
                // tambah sendiri bang

                ds.energy = gp.player.energy;
                ds.coin = gp.player.gold;
                ds.hour = gp.hour;
                ds.minute = gp.minute;
                ds.timeCounter = gp.timeCounter;
                

                // write datastorage obj
                oos.writeObject(ds);
            }

        } catch (Exception e) {
            System.out.println("Save Exception");
            e.printStackTrace();
        }
    }

    public void load() {
        try {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")))) {
                // read objectnya
                dataStorage ds = (dataStorage) ois.readObject();

                // contoh masukinnya
                gp.player.energy = ds.energy;
                gp.player.gold = ds.coin;
                gp.hour =  ds.hour;
                gp.minute = ds.minute; 
                gp.timeCounter = ds.timeCounter;
            }

        } catch (Exception e) {
            System.out.println("Load Exception");
            e.printStackTrace();
        }
    }
}
