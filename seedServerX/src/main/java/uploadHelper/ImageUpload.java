package uploadHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageUpload {

    private String path = "/var/www/img/";

    public void saveFile(InputStream is, String fileLocation) throws IOException {

        String location = path + fileLocation;
        try (OutputStream os = new FileOutputStream(new File(location))) {
            byte[] buffer = new byte[256];
            int bytes = 0;
            while ((bytes = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytes);
            }
        }
    }
}
