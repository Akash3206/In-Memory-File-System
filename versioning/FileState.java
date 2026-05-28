package versioning;

public class FileState {

    private String content;
    private int size;

    public FileState(String content) {
        this.content = content;
        this.size = content.length();
    }

    public String getContent() {
        return content;
    }

    public int getSize() {
        return size;
    }
}