package com.thanhnt10.dodobook.apps.core.models.request;

import lombok.Getter;

@Getter
public class AuthorUpdateRequest {
    private String name;
    private String description;
    private String country;
}
