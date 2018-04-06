package com.myLunch.dto;

import java.time.LocalDateTime;

public class VoteTO extends BaseTo {

    private String restaurantName;

    private int restaurantId;

    private LocalDateTime localDateTime;

    public VoteTO(String restaurantName, int restaurantId, LocalDateTime localDateTime) {
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
        this.localDateTime = localDateTime;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
