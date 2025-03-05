package com.thanhnt10.dodobook.common.model.base;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PagingResponseDto<T> {
    private Integer pageNumber;
    private Integer pageSize;
    private Long total;
    private List<T> data;
}
