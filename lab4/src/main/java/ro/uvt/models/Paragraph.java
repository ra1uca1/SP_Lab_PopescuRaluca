package ro.uvt.models;

import jakarta.persistence.Entity;

@Entity
public class Paragraph extends BaseElement {
    private String text;

    protected Paragraph() {}

    public Paragraph(String text) { this.text = text; }

    public String getText() { return text; }

    public void render() { System.out.println("Paragraph: " + text); }

    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
}