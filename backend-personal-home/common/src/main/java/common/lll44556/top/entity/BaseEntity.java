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

    @Column(name = "cjsj", nullable = false)
    private Long createdTime;

    @Column(name = "gxsj")
    private Long updatedTime;

    @Column(name = "czz")
    private String operator;

    @Column(name = "yxx", nullable = false)
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
