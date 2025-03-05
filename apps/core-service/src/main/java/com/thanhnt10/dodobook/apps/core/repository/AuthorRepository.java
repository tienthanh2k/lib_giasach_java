package com.thanhnt10.dodobook.apps.core.repository;

import com.thanhnt10.dodobook.apps.core.models.request.AuthorSearchRequest;
import com.thanhnt10.dodobook.apps.core.models.response.AuthorSearchResponse;
import com.thanhnt10.dodobook.common.model.entity.core.AuthorEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByName(String name);

    @Query("""
        SELECT a
        from AuthorEntity a
        where :#{#request.keyword} IS NULL OR a.name like :#{#input.keyword}
    """)
    Optional<List<AuthorSearchResponse>> search(AuthorSearchRequest request, Pageable pageable);
}
