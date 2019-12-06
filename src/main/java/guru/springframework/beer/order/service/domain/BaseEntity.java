package guru.springframework.beer.order.service.domain;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    public BaseEntity(UUID id, Integer version, Timestamp createdDate, Timestamp lastModifiedDate) {
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", nullable = false, updatable = false)
    private UUID id ;

    @Version
    private Integer version ;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate = null;

    @UpdateTimestamp
    private Timestamp lastModifiedDate = null;

    public boolean isNew(){
        return this.id == null;
    }

}
