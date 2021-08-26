package com.gigatorb.backendraddit.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubredditDto {

    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
