package com.thanhnt10.dodobook.common.model.entity.core;

import com.thanhnt10.dodobook.common.model.base.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Table(name = "author")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorEntity  extends AbstractBaseEntity {
    @Comment("Tên tác giả")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Comment("Mô tả")
    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Comment("Xuất xứ")
    @Column(name = "country", nullable = true, length = 100)
    private String country;
}
