package net.jsrois.imageupload.services;

public class ImagePreview {
    private final String name;
    private final String id;
    private final int sizeInMB;

    public ImagePreview(String name, String id, int sizeInMB) {
        this.name = name;
        this.id = id;
        this.sizeInMB = sizeInMB;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


    public int getSizeInMB() {
        return sizeInMB;
    }
}
