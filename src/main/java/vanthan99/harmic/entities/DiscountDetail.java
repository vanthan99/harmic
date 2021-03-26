package vanthan99.harmic.entities;

import lombok.Getter;
import lombok.Setter;
import vanthan99.harmic.entities.bases.Audit;
import vanthan99.harmic.entities.bases.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
public class DiscountDetail extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discountId")
    private Discount discount;

    @Column
    private Double rate;
}
