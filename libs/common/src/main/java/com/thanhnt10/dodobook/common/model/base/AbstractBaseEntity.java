package com.thanhnt10.dodobook.common.model.base;

import com.thanhnt10.dodobook.common.business.TenantAware;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractBaseEntity implements TenantAware, Serializable {

    @Serial
    private static final long serialVersionUID = 11237465125L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getStringId() {
        return String.valueOf(id);
    }

    @Comment("Thời gian tạo")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    protected Date createdAt;

    @Comment("Người tạo")
    @Column(name = "creator_id")
    protected String creatorId;

    @Comment("Thời gian sửa")
    @UpdateTimestamp
    @Column(name = "updated_at")
    protected Date updatedAt;

    @Comment("Người sửa")
    @Column(name = "updated_by")
    protected String updatedBy;

    @Comment("Thời gian xóa")
    @Column(name = "deleted_at")
    protected Date deletedAt;

    @Comment("Người xóa")
    @Column(name = "deleted_by")
    protected String deletedBy;

    @Comment("Trạng thái xoá")
    @Column(name = "is_deleted")
    protected Boolean isDeleted = false;

}
