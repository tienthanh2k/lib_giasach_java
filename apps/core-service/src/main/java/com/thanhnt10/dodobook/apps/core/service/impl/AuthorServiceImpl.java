package com.thanhnt10.dodobook.apps.core.service.impl;

import com.thanhnt10.dodobook.apps.core.mapper.AuthorMapper;
import com.thanhnt10.dodobook.apps.core.models.request.AuthorCreateRequest;
import com.thanhnt10.dodobook.apps.core.models.request.AuthorSearchRequest;
import com.thanhnt10.dodobook.apps.core.models.response.AuthorGetResponse;
import com.thanhnt10.dodobook.apps.core.models.response.AuthorSearchResponse;
import com.thanhnt10.dodobook.apps.core.repository.AuthorRepository;
import com.thanhnt10.dodobook.apps.core.service.AuthorService;
import com.thanhnt10.dodobook.common.enums.ErrorCode;
import com.thanhnt10.dodobook.common.exception.BusinessException;
import com.thanhnt10.dodobook.common.model.base.PagingResponseDto;
import com.thanhnt10.dodobook.common.model.base.Response;
import com.thanhnt10.dodobook.common.model.entity.core.AuthorEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public void create(AuthorCreateRequest request) {
        AuthorEntity existAuthor = authorRepository.findByName(request.getName()).orElse(null);

        if(existAuthor != null) {
            throw new BusinessException(ErrorCode.AUTHOR_EXISTED);
        }

        AuthorEntity author = authorMapper.toEntity(request);

        authorRepository.save(author);
    }

    @Override
    public void update(long id, AuthorCreateRequest request) {
        AuthorEntity existAuthor = authorRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.AUTHOR_NOT_FOUND));
        existAuthor.setName(request.getName());
        existAuthor.setCountry(request.getCountry());

        authorRepository.save(existAuthor);
    }

    @Override
    public void delete(long id) {
        AuthorEntity existAuthor = authorRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.AUTHOR_NOT_FOUND));
        authorRepository.delete(existAuthor);
    }

    @Override
    public Response<AuthorGetResponse> getById(long id) {
        AuthorEntity existAuthor = authorRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.AUTHOR_NOT_FOUND));
        return Response.ok(authorMapper.toGetDto(existAuthor));
    }

    @Override
    public Response<PagingResponseDto<AuthorSearchResponse>> search(AuthorSearchRequest request) {
        return null;
    }
}
