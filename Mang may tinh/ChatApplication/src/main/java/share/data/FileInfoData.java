package share.data;

import java.io.Serializable;

public class FileInfoData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileExtension;
    private long fileSize;
    private byte[] dataBytes;

    // Constructor
    // getter & setter


    public FileInfoData() {
    }

    public FileInfoData(String fileName, String fileExtension, long fileSize, byte[] dataBytes) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
        this.dataBytes = dataBytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }
}
