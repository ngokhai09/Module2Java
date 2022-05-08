package module2.controller;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Read_Write<T> {

    public List<String> readFile(String path)  {
        List<String> list = new ArrayList<>();
        try {
            File f = new File(path);
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
            fr.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean wirteFile(String path, List<T> list) {
        try {
            File f = new File(path);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (T t : list) {
                bw.write(t.toString() + System.lineSeparator());
            }
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
