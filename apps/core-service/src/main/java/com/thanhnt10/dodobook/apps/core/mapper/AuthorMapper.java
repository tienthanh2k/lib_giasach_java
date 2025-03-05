package com.thanhnt10.dodobook.apps.core.mapper;

import com.thanhnt10.dodobook.apps.core.models.request.AuthorCreateRequest;
import com.thanhnt10.dodobook.apps.core.models.request.AuthorUpdateRequest;
import com.thanhnt10.dodobook.apps.core.models.response.AuthorGetResponse;
import com.thanhnt10.dodobook.apps.core.models.response.AuthorSearchResponse;
import com.thanhnt10.dodobook.common.model.entity.core.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorSearchResponse toSearchDto(AuthorEntity entity);
    AuthorGetResponse toGetDto(AuthorEntity entity);
    AuthorEntity toEntity(AuthorCreateRequest dto);
    AuthorEntity toEntity(AuthorUpdateRequest dto);
}