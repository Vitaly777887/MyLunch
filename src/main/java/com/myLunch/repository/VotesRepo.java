package com.myLunch.repository;

import com.myLunch.model.Restaurant;
import com.myLunch.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VotesRepo extends JpaRepository<Vote, Integer> {

    @Query("select v from Vote v where v.user.id=:userId and v.dateTime between :startDateTime and :endDateTime")
    List<Vote> findByUserAndDateTimeBetween(@Param("userId") int userId,
                                            @Param("startDateTime") LocalDateTime startDateTime,
                                            @Param("endDateTime") LocalDateTime endDateTime);

    @Override
    Vote save(Vote item);

    List<Vote> findAllByRestaurantAndDateTime(Restaurant restaurant, LocalDate localDate);
}
