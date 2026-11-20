package ro.uvt.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TableOfContents extends BaseElement {
    @ElementCollection
    private List<String> entries = new ArrayList<>();

    public TableOfContents() {}

    public void addEntry(String entry) { entries.add(entry); }

    public List<String> getEntries() { return entries; }

    public void render() {
        System.out.println("Table of Contents");
        for (String e : entries) {
            System.out.println(" - " + e);
        }
    }

    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
}