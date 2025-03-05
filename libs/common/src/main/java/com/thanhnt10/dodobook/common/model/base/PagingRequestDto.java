package com.thanhnt10.dodobook.common.model.base;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingRequestDto {
    @NotNull(message = "pageNumber không được để trống")
    @PositiveOrZero(message = "pageNumber phải lớn hơn hoặc bằng 0")
    public Integer pageNumber;

    @NotNull(message = "pageSize không được để trống")
    @Positive(message = "pageSize phải là số dương")
    @Max(value = 50, message = "pageSize tối đa 50")
    public Integer pageSize;
}
