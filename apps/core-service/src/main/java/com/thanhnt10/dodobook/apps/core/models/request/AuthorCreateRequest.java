package com.thanhnt10.dodobook.apps.core.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorCreateRequest {
    private String name;
    private String description;
    private String country;
}
