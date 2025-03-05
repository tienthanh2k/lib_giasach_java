package com.thanhnt10.dodobook.apps.core.controller;

import com.thanhnt10.dodobook.apps.core.models.request.AuthorCreateRequest;
import com.thanhnt10.dodobook.apps.core.models.request.AuthorSearchRequest;
import com.thanhnt10.dodobook.apps.core.models.response.AuthorGetResponse;
import com.thanhnt10.dodobook.apps.core.models.response.AuthorSearchResponse;
import com.thanhnt10.dodobook.apps.core.service.AuthorService;
import com.thanhnt10.dodobook.common.enums.ResponseCode;
import com.thanhnt10.dodobook.common.model.base.PagingResponseDto;
import com.thanhnt10.dodobook.common.model.base.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PreAuthorize("hasAuthority('author:create')")
    @PostMapping("/create")
    public Response<Void> createAuthor(AuthorCreateRequest request) {

        // Lấy danh sách quyền từ Security Context
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        // Chuyển đổi danh sách quyền về List<String>
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        authorService.create(request);
        return Response.of(ResponseCode.SUCCESS);
    }

    @PreAuthorize("hasAuthority('author:update')")
    @PostMapping("/update/{id}")
    public Response<Void> updateAuthor(@PathVariable long id, AuthorCreateRequest request) {
        authorService.update(id, request);
        return Response.of(ResponseCode.SUCCESS);
    }

    @PreAuthorize("hasAuthority('author:delete')")
    @DeleteMapping("/delete/{id}")
    public Response<Void> deleteAuthor(@PathVariable long id) {
        authorService.delete(id);
        return Response.of(ResponseCode.SUCCESS);
    }

    @GetMapping("/{id}")
    public Response<AuthorGetResponse> getById(@PathVariable long id) {
        return authorService.getById(id);
    }

    @GetMapping("/search")
    public Response<PagingResponseDto<AuthorSearchResponse>> search(AuthorSearchRequest request) {
        return authorService.search(request);
    }
}
