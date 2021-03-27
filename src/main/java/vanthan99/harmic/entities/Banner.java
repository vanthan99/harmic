package vanthan99.harmic.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vanthan99.harmic.entities.bases.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Banner extends BaseEntity {
    @Column
    private String author;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String homeURL;

    @Column
    private String imageURL;
}
