package vanthan99.harmic.entities;

import lombok.Getter;
import lombok.Setter;
import vanthan99.harmic.entities.bases.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table
public class Category extends BaseEntity {
    @Column
    private String name;

    @Column
    private boolean enable = true;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    private Set<Product> products;
}
