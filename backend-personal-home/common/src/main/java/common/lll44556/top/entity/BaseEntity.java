package common.lll44556.top.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 32, nullable = false)
    private String id;

    @Column(name = "created_time", nullable = false)
    private Long createdTime;

    @Column(name = "updated_time")
    private Long updatedTime;

    @Column(name = "operator")
    private String operator;

    @Column(name = "valid", nullable = false)
    private Integer valid;

    @PrePersist
    public void prePersist() {
        long now = System.currentTimeMillis();

        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString().replace("-", "");
        }

        if (this.createdTime == null) {
            this.createdTime = now;
        }

        if (this.updatedTime == null) {
            this.updatedTime = now;
        }

        if (this.valid == null) {
            this.valid = 1;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedTime = System.currentTimeMillis();
    }
}
