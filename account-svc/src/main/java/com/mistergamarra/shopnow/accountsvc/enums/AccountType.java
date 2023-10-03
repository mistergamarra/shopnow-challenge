package com.mistergamarra.shopnow.accountsvc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum AccountType {
        REGULAR("REGULAR"),
        PREMIUM("PREMIUM");

        private String code;

        AccountType(String code) {
                this.code = code;
        }

        @JsonCreator
        public static AccountType getAccountTypeFromString(String value) {

                for (AccountType dep : AccountType.values()) {

                        if (dep.getCode().equals(value)) {

                                return dep;
                        }
                }

                return null;
        }
}

