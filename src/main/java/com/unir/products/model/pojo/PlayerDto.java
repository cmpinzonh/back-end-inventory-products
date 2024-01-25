package com.unir.products.model.pojo;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PlayerDto {
    private String id;
    private String name;
    private String club;
    private String position;
    private int overall;
    private int pace;
    private int shooting;
    private int passing;
    private int dribbling;
    private int defending;
    private int physicality;
    private String image;
}