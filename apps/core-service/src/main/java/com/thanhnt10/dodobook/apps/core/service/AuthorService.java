package com.thanhnt10.dodobook.apps.core.service;

import com.thanhnt10.dodobook.apps.core.models.request.AuthorCreateRequest;
import com.thanhnt10.dodobook.apps.core.models.request.AuthorSearchRequest;
import com.thanhnt10.dodobook.apps.core.models.response.AuthorGetResponse;
import com.thanhnt10.dodobook.apps.core.models.response.AuthorSearchResponse;
import com.thanhnt10.dodobook.common.model.base.PagingResponseDto;
import com.thanhnt10.dodobook.common.model.base.Response;

public interface AuthorService {
    void create(AuthorCreateRequest request);

    void update(long id, AuthorCreateRequest request);

    void delete(long id);

    Response<AuthorGetResponse> getById(long id);

    Response<PagingResponseDto<AuthorSearchResponse>> search(AuthorSearchRequest request);
}
