package cn.ipay.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rayest on 2016/8/17 0017.
 */
public class Lover {
    private int id;
    private String name;
    private Set<String> images = new HashSet<String>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }
}
