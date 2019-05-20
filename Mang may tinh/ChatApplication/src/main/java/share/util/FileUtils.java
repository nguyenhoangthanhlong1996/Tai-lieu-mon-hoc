package share.util;

import share.data.FileInfoData;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {
    public static FileInfoData getFileInfo(File sourceFile) {
        FileInfoData fileInfo = null;
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFile));
            fileInfo = new FileInfoData();
            byte[] fileBytes = new byte[(int) sourceFile.length()];
            // get file info
            bis.read(fileBytes, 0, fileBytes.length);
            String pathName = sourceFile.getName();
            String fileExtension = pathName.substring(pathName.lastIndexOf(".") + 1);
            String fileName = pathName.replace("."+fileExtension, "");
            fileInfo.setFileName(fileName);
            fileInfo.setFileExtension(fileExtension);
            fileInfo.setFileSize(fileBytes.length);
            fileInfo.setDataBytes(fileBytes);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return fileInfo;
    }

}
