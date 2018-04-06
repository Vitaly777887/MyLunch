package com.myLunch;

import com.myLunch.model.Restaurant;

import java.util.Arrays;

import static com.myLunch.model.AbstractBaseEntity.START_SEQUENCE;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQUENCE + 2;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "2stars");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "3stars");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "4stars");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menu");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menu").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
}
