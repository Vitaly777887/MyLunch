package com.myLunch.service;

import com.myLunch.model.Restaurant;
import com.myLunch.model.Vote;
import com.myLunch.repository.VotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class VotesService {

    public static final int HOUR_OF_NO_RETURN = 11;

    @Autowired
    VotesRepo votesRepo;


    @Transactional
    public Vote create(Vote vote) {
        LocalDateTime startDateTime = LocalDateTime.now().withHour(0).withMinute(0);
        LocalDateTime endDateTime = startDateTime.withHour(23).withMinute(59);
        List<Vote> voteList = votesRepo.findByUserAndDateTimeBetween(vote.getUser().getId(), startDateTime, endDateTime);
        if (!voteList.isEmpty()) {
            if (LocalTime.now().isAfter(LocalTime.of(HOUR_OF_NO_RETURN, 0))) {
                return null;
            } else {
                Vote found = voteList.get(0);
                found.setDateTime(LocalDateTime.now());
                found.setRestaurant(vote.getRestaurant());
                return votesRepo.save(found);
            }
        }

        vote = votesRepo.save(vote);

        return vote;
    }

    public List<Vote> getAll() {
        return votesRepo.findAll();
    }

    public int getCountForRestaurant(int restaurantId){
        return votesRepo.findAllByRestaurantAndDateTime(new Restaurant(restaurantId, ""), LocalDate.now()).size();
    }
}
