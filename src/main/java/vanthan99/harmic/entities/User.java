package vanthan99.harmic.entities;

import lombok.Getter;
import lombok.Setter;
import vanthan99.harmic.entities.bases.Audit;
import vanthan99.harmic.entities.bases.BaseEntity;
import vanthan99.harmic.entities.enums.ERole;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table
public class User extends Audit {
    @Id
    @Column(updatable = false,nullable = false,unique = true)
    private String email;

    @Column(length = 60,nullable = false)
    private String password;

    @Column(length = 11)
    private String phoneNumber;

    @Column
    private String fullName;

    @Column
    private boolean enable = false;

    @Column
    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Review> reviews;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Bill> bills;

}
