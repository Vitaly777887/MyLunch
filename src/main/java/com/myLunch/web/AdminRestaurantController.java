package com.myLunch.web;

import com.myLunch.dto.RestaurantTo;
import com.myLunch.model.Restaurant;
import com.myLunch.service.RestaurantService;
import com.myLunch.service.VotesService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.myLunch.web.AdminRestaurantController.REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController {
    final static String REST_URL = "/admin/restaurants";
    private final static Logger log = getLogger(AdminRestaurantController.class);

    @Autowired
    RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("Get all restaurants");
        return restaurantService.getAll();

    }

    @GetMapping(REST_URL + "/{id}")
    public RestaurantTo getOne(@PathVariable("id") int id) {
        log.info("Get restaurant with id {}", id);
        Restaurant result = restaurantService.getById(id);
        return new RestaurantTo(result.getId(), result.getName(), 0);
    }

    @DeleteMapping(REST_URL + "/{id}")
    public void delete(@RequestParam("id") int id) {
        log.info("Delete restaurant with id {}", id);
        restaurantService.delete(id);
    }

    @PutMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurantTo, @PathVariable("id") int id) {
        log.info("Update restaurant with id {}", id);
        Restaurant updated = new Restaurant(id, restaurantTo.getName());
        restaurantService.update(updated);
    }

    @PostMapping
    public ResponseEntity<RestaurantTo> createWithLocation(@RequestBody RestaurantTo restaurantTo) throws Exception {
        log.info("Create new restaurant with name {}", restaurantTo.getName());
        Restaurant created = restaurantService.create(new Restaurant(restaurantTo.getId(), restaurantTo.getName()));
        if (created == null)
            throw new Exception("can't create");
        RestaurantTo result = new RestaurantTo(created.getId(), created.getName(),0);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "{/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(result);
    }
}
