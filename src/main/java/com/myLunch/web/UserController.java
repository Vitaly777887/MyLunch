package com.myLunch.web;


import com.myLunch.AuthorizedUser;
import com.myLunch.dto.VoteTO;
import com.myLunch.model.Restaurant;
import com.myLunch.model.User;
import com.myLunch.model.Vote;
import com.myLunch.service.VotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;


@RestController
@RequestMapping(value = "/user/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    VotesService votesService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteTO> createOrUpdate(@RequestBody VoteTO voteTo) throws Exception {
        int userId = AuthorizedUser.id();
        Vote created = votesService.create(new Vote(new Restaurant(voteTo.getRestaurantId(), ""),
                new User(userId), LocalDateTime.now()));
        if (created == null){
            throw new Exception("can't create");
        }
        VoteTO result = new VoteTO(created.getRestaurant().getName(), created.getRestaurant().getId(), created.getDateTime());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/votes" + "/{dateTime}")
                .buildAndExpand(result.getLocalDateTime()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(result);
    }


}
