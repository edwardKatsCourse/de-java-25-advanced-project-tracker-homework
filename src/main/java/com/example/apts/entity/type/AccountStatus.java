package com.example.apts.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum AccountStatus {
    INACTIVE(0, "Inactive"),
    ACTIVE(1, "Active");

    private final Integer accountStatusId;
    private final String accountStatusName;

    @JsonValue
    public String getAccountStatusName() {
        return accountStatusName;
    }

    public static AccountStatus findByStatusId(Integer statusId) {
        if (statusId == null) {
            return null;
        }

        return Arrays.stream(AccountStatus.values())
                .filter(x -> x.getAccountStatusId().equals(statusId))
                .findFirst()
                .orElse(null);
    }

    @JsonCreator
    public static AccountStatus findByStatusName(String statusName) {
        if (statusName == null) {
            return ACTIVE;
        }

        return Arrays.stream(AccountStatus.values())
                .filter(x -> x.getAccountStatusName().equals(statusName))
                .findFirst()
                .orElse(ACTIVE);
    }
}
