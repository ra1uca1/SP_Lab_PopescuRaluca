package ro.uvt.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section extends BaseElement {
    protected String title;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<BaseElement> children = new ArrayList<>();

    protected Section() {}

    public Section(String title) { this.title = title; }

    public String getTitle() { return title; }
    public List<BaseElement> getChildren() { return children; }

    @Override
    public void add(Element e) { children.add((BaseElement) e); }

    @Override
    public void remove(Element e) { children.remove(e); }

    @Override
    public Element get(int index) { return children.get(index); }

    public void render() { System.out.println(title); }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (BaseElement e : children) {
            e.accept(visitor);
        }
    }
}