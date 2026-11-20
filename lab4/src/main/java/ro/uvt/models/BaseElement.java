package ro.uvt.models;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseElement implements Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    protected BaseElement() {}

    public Integer getId() { return id; }

    @Override
    public void accept(Visitor visitor) {
        throw new UnsupportedOperationException();
    }
}