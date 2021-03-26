package vanthan99.harmic.entities;

import lombok.Getter;
import lombok.Setter;
import vanthan99.harmic.entities.bases.BaseEntity;
import vanthan99.harmic.entities.enums.EBillStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table
public class Bill extends BaseEntity {
    @Column
    private Long total;

    @Column
    @Enumerated(EnumType.STRING)
    private EBillStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "bill")
    private Set<BillDetail> billDetails;
}
