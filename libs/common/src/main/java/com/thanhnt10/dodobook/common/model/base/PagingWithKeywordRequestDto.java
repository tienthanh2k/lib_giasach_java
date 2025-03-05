package com.thanhnt10.dodobook.common.model.base;

import lombok.Getter;

@Getter
public class PagingWithKeywordRequestDto extends PagingRequestDto{
    private String keyword;
}
