import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {

        String srcFolder = "";
        String dstFolder = "";
        ConvertToThisFormats dstFormat;

        JFrame frame = new JFrame("My GUI Form");
        frame.setContentPane(new GUIForm().getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 700)); // Установите желаемый размер окна
        frame.setVisible(true);
    }


    public static ArrayList<File> listFiles(File src) {
        ArrayList<File> files = new ArrayList<>();

        if (!src.isDirectory()) {
            files.add(src);
            return files;
        }

        for (File file : src.listFiles()) {
            if (!file.isDirectory()) {
                files.add(file);
            } else {
                ArrayList<File> insideFiles = new ArrayList<>(Arrays.asList(file.listFiles()));
                insideFiles.forEach(insideFile -> files.addAll(listFiles(insideFile)));
            }
        }
        return files;
    }
}
