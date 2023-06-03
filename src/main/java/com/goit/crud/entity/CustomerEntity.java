package com.goit.crud.entity;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CustomerEntity {
    private Long customerId;
    private String customerName;
    private String contactName;
    private String country;
}
