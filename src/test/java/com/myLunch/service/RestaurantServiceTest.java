package com.myLunch.service;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.myLunch.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    RestaurantService restaurantService;

    @Test
    public void delete() throws Exception{
        restaurantService.delete(RESTAURANT1_ID);
        assertMatch(restaurantService.getAll(), RESTAURANT2, RESTAURANT3);
    }
}
