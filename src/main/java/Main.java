import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;


public class Main {
    public static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        ConvertToThisFormats dstFormat = null;

        String currentDirectory = System.getProperty("user.dir");
        FileInputStream fileInputStream = new FileInputStream(currentDirectory + File.separator + "config.properties");


        properties.load(fileInputStream);
        fileInputStream.close();


        // Getting data from application.properties
        String srcFolder = properties.getProperty("input_path");
        String dstFolder = properties.getProperty("output_path");
        int height = Integer.parseInt(properties.getProperty("change_size_by_pizels_height"));
        int width = Integer.parseInt(properties.getProperty("change_size_by_pizels_width"));
        Integer compressionRate = Integer.parseInt(properties.getProperty("change_size_in_percents"));
        boolean renamePhotos = Boolean.valueOf(properties.getProperty("rename_photo_video_by_creation_date"));

        boolean changeFormat = false;
        if (!properties.getProperty("change_format").equals("null")) {
            switch (properties.getProperty("change_format")) {
                case "jpg" -> dstFormat = ConvertToThisFormats.jpg;
                case "png" -> dstFormat = ConvertToThisFormats.png;
                case "webp" -> dstFormat = ConvertToThisFormats.webp;
            }
            changeFormat = true;
        }


        //Create a list of files. Collect files from all subfolders using a recursive function
        File srcDir = new File(srcFolder);
        File[] filesArray = srcDir.listFiles();
        ArrayList<File> files = new ArrayList<>();
        try {
            files = listFiles(new ArrayList<>(Arrays.asList(filesArray)));
        } catch (Exception e) {
            logger.fatal("Неверно указан путь к папке или файлу с входящими данными: " + srcFolder);
        }

        // working with photo files
        if (renamePhotos) {
            RenameByCreationDate renameByCreationDate = new RenameByCreationDate(files, dstFolder);
            renameByCreationDate.rename();
        } else {
            ImageChanger imageChanger = new ImageChanger(files, compressionRate, width, height, dstFormat, dstFolder, changeFormat);
            imageChanger.handlePictures();

        }
    }

    // recursive function to create ArrayList with files
    public static ArrayList<File> listFiles(ArrayList<File> src) {
        ArrayList <File> files = new ArrayList<>();
        for (File file : src) {
            if (!file.isDirectory()) {
                files.add(file);
            } else {
                ArrayList<File> insideFiles = new ArrayList<>(Arrays.asList(file.listFiles()));
                files.addAll(listFiles(insideFiles));
            }
        }
        return files;
    }
}
