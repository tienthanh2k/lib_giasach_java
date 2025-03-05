package com.thanhnt10.dodobook.apps.core.repository;

import com.thanhnt10.dodobook.common.model.entity.core.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
