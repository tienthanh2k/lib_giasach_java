package com.thanhnt10.dodobook.apps.core.models.request;

import com.thanhnt10.dodobook.common.model.base.PagingWithKeywordRequestDto;
import lombok.Getter;

@Getter
public class AuthorSearchRequest extends PagingWithKeywordRequestDto {
    public String keyword;
}
