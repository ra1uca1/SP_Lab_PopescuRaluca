package ro.uvt.models;

import jakarta.persistence.Entity;

@Entity
public class Table extends BaseElement {
    private String title;

    protected Table() {}

    public Table(String title) { this.title = title; }

    public String getTitle() { return title; }

    public void render() { System.out.println("Table with title:" + title); }

    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
}