package vanthan99.harmic.entities;

import lombok.Getter;
import lombok.Setter;
import vanthan99.harmic.entities.bases.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table
public class Discount extends BaseEntity {
    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column
    private LocalDate startDay;

    @Column
    private LocalDate endDay;

    @Column
    private boolean enable = true;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "discount")
    private Set<DiscountDetail> discountDetails;
}


