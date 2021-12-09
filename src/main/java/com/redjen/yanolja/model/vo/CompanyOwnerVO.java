package com.redjen.yanolja.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyOwnerVO {

    private int companyIdx;
    private String ownerName;
    private String businessName;
    private String ownerAddress;

    @Email
    private String ownerEmail;
    private String ownerPhoneNumber;
    private String businessNumber;
}
