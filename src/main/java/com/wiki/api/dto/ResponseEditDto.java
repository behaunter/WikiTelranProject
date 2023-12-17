package com.wiki.api.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseEditDto {

    private String result;
    private int pageid;
    private String title;
    private String contentmodel;
    private boolean nochange;
    private boolean watched;
}
