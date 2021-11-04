package com.redjen.yanolja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyOwner {

    private int companyIdx;
    private String ownerName;
    private String businessName;
    private String ownerAddress;
    private String ownerEmail;
    private String ownerPhoneNumber;
    private String businessNumber;
}
