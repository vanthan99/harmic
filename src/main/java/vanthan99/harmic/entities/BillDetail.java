package vanthan99.harmic.entities;

import lombok.Getter;
import lombok.Setter;
import vanthan99.harmic.entities.bases.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
public class BillDetail extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billId")
    private Bill bill;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;

    @Column
    private Long quantity;

    @Column
    private Long unitPrice;

    @Column
    private Long discountPrice;

    @Column
    private Long finalPrice;
}
