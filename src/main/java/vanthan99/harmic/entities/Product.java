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
public class Product extends BaseEntity {
    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Long quantity;

    @Column
    private String image;

    @Column
    private Long price;

    @Column
    private boolean enable = true;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    private Set<Review> reviews;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    private Set<ViewCountStatistic> viewCountStatistics;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    private Set<BillDetail> billDetails;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    private Set<DiscountDetail> discountDetails;
}
