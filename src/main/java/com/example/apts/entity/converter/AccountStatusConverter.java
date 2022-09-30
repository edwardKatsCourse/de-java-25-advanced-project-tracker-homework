package com.example.apts.entity.converter;

import com.example.apts.entity.type.AccountStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AccountStatus accountStatus) {
        return accountStatus == null ? null : accountStatus.getAccountStatusId();
    }

    @Override
    public AccountStatus convertToEntityAttribute(Integer integer) {
        return integer == null ? null : AccountStatus.findByStatusId(integer);
    }
}
