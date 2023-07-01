import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.mov.media.QuickTimeMediaDirectory;
import com.drew.metadata.mp4.Mp4Directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RenameByCreationDate {

    ArrayList<File> filesList;
    String dstFolder;
    int exceptions = 1;
    String errorFolder;


    public RenameByCreationDate(ArrayList<File> filesList, String dstFolder) {
        this.filesList = filesList;
        this.dstFolder = dstFolder;
        errorFolder = dstFolder + "/error files";
    }


    public boolean rename() {
        for (File file : filesList) {

            // Get file format, read metadata
            FileFormat fileFormat = new FileFormat(file.getAbsolutePath());
            String format = fileFormat.getFileFormat();
            Metadata aData = null;
            try {
                aData = ImageMetadataReader.readMetadata(file);
            } catch (Exception e) {
                Main.logger.error("Не удалось прочитать метаданные! Вероятно, файл не является картинкой " +
                        "или видео. Файл - " + file.getAbsolutePath());
                copyErrorFile(errorFolder, format, file);
            }


            Date date = new Date();
            if (format.equals("mp4")) {
                try {
                    Mp4Directory mp4Directory = aData.getFirstDirectoryOfType(Mp4Directory.class);
                    date = mp4Directory.getDate(256);
                    File folder = new File(dstFolder + "/video");
                    folder.mkdir();
                    makeNewFile(folder.getPath(), date, format, file);
                } catch (NullPointerException nullPointerException) {
                    copyErrorFile(errorFolder, format, file);
                    Main.logger.error("не удалось получить дату создания! Формат - " + format + "файл - "
                    + file.getAbsolutePath());
                }


            } else if (format.equals("mov")) {
                try {
                    QuickTimeMediaDirectory quickTimeMediaDirectory = aData.getFirstDirectoryOfType(QuickTimeMediaDirectory.class);
                    date = quickTimeMediaDirectory.getDate(20481);
                    File folder = new File(dstFolder + "/video");
                    folder.mkdir();
                    makeNewFile(folder.getPath(), date, format, file);
                } catch (NullPointerException nullPointerException) {
                    copyErrorFile(errorFolder, format, file);
                    Main.logger.error("не удалось получить дату создания! Формат - " + format + "файл - "
                            + file.getAbsolutePath());
                }


            } else {
                ExifSubIFDDirectory aDirectory = aData.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
                try {
                    date = aDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                    makeNewFile(dstFolder, date, format, file);
                } catch (NullPointerException nullPointerException) {
                    copyErrorFile(errorFolder, format, file);
                    Main.logger.error("не удалось получить дату создания! Формат - " + format + "файл - "
                            + file.getAbsolutePath());
                }
            }
        }
        return true;
    }

    private void makeNewFile(String path, Date date, String format, File file) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        File newFile = new File(path + "\\" + dateFormat.format(date) + "." + format);

        // Check if there is a file with the same name. If there is, add a number to the end of the name
        int x = 1;
        while (newFile.exists()) {
            newFile = new File(path + "\\" + dateFormat.format(date) + x + "." + format);
            x++;
        }

        // save the file
        try {
            Files.copy(file.toPath(), newFile.toPath());
            Main.logger.info("обработали файл " + newFile.getName());
        } catch (IOException e) {
            Main.logger.error("Не удалось создать файл " + newFile.getPath() + "Сообщение об ошибке: " +
                    e.getMessage());
        }
    }

    private void copyErrorFile(String errorFolder, String format, File file) {
        File folder = new File(errorFolder);
        folder.mkdir();
        File errorFile = new File(errorFolder + "\\" + exceptions + "." + format);
        exceptions++;
        int x = 1;
        while (errorFile.exists()) {
            errorFile = new File(errorFolder + "\\" + exceptions + x + "." + format);
            x++;
        }
        try {
            Files.copy(file.toPath(), errorFile.toPath());
            Main.logger.info("обработали \"ошибочный\" файл " + errorFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
            Main.logger.error("Не удалось создать \"ошибочный\" файл  " + errorFile.getPath() + "Сообщение" +
                    " об ошибке: " + e.getMessage());
        }

    }

}


