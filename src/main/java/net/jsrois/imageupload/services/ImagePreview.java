package net.jsrois.imageupload.services;

public class ImagePreview {
    private final String name;
    private final String id;
    private final int sizeInBytes;

    public ImagePreview(String name, String id, int sizeInBytes) {
        this.name = name;
        this.id = id;
        this.sizeInBytes = sizeInBytes;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


    public int getSizeInBytes() {
        return sizeInBytes;
    }
}
