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
@Table(name = "book")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookEntity extends AbstractBaseEntity {

    @Comment("Tiêu đề")
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Comment("Mô tả")
    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Comment("Ngày phát hành")
    @Column(name = "publish_date", nullable = false, length = 50)
    private String publishDate;

    @Comment("Id Tác giả")
    @Column(name = "author_id")
    private long authorId;

    @Comment("Đường dẫn file sách")
    @Column(name = "book_path", length = 20)
    private String bookPath;

    @Comment("Đường dẫn bìa sách")
    @Column(name = "cover_path", length = 20)
    private String coverPath;

    @Comment("Trạng thái khoá")
    @Column(name = "is_locked")
    private Boolean isLocked = false;
}