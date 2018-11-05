package com.venturedive.rotikhilao.enums;

public enum OrderStatus {
    PENDING((short) 1),
    ACCEPTED((short) 2),
    DELIVERED((short) 3),
    CANCELLED((short) 4);

    private Short value;

    OrderStatus(short value) {
        this.value = value;
    }

    public Short value() {
        return this.value;
    }
}
