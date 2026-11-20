package ro.uvt.models;

import jakarta.persistence.Entity;

@Entity
public class Image extends BaseElement {
    private String url;

    protected Image() {}

    public Image(String url) { this.url = url;
    }

    public String getUrl() { return url; }

    public void render() { System.out.println("Image with name:" + url); }

    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
}