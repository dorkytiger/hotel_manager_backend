package com.dorkytiger.hotel_manager.emun;

import lombok.Getter;

@Getter
public enum RoomStatus {
    SPACE(0),
    IN_USE(1),
    BOOK(2);
    private final Integer status;

    RoomStatus(Integer status) {
        this.status = status;
    }
}
