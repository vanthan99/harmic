package vanthan99.harmic.entities;

import lombok.Getter;
import lombok.Setter;
import vanthan99.harmic.entities.bases.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
public class Review extends BaseEntity {

    @Column
    private Double rate;

    @Column
    private String content;

    @Column
    private boolean enable = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;
}
