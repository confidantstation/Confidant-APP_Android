package events;

public class ToxReceiveFileNoticeEvent {

    private String key;
    private Integer fileNumber;
    private String filename;

    public ToxReceiveFileNoticeEvent(String key, Integer fileNumber,String filename) {
        this.fileNumber = fileNumber;
        this.key = key;
        this.filename = filename;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(Integer fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
