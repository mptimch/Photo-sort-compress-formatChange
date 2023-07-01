import com.drew.imaging.FileType;
import com.drew.imaging.FileTypeDetector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileFormat {
    private String path;

    public FileFormat(String path) {
        this.path = path;
    }


    public String getFileFormat() {
        try {
            if (new File(path).isFile()) {

            }
            FileInputStream is = new FileInputStream(path);
            BufferedInputStream myStream = new BufferedInputStream(is); // BufferedInputStream is needed to determine the file type

            FileType fileType = FileTypeDetector.detectFileType(myStream); // got file type

            // return different String depending on file type (look at fileType)
            switch (fileType) {
                case Jpeg: return "jpg";
                case Png: return "png";
                case Bmp: return "bmp";
                case Gif: return "gif";
                case WebP:  return "webP";
                case Heif: return "heif";
                case Mp4: return "mp4";
                case QuickTime: return "mov";
            }
            } catch (Exception ex) {
            ex.printStackTrace();
            }

        // below code - to determine file formats not pictures and not videos
        File file = new File(path);
        Pattern pattern = Pattern.compile("\\.(\\w+)$");
        Matcher matcher = pattern.matcher(file.getName());
        if (matcher.find()) {
            return matcher.group(1);
        }


        return "";
    }
}
