package com.cohort22.data.repositories;

import com.cohort22.data.enums.GameStatus;
import com.cohort22.data.models.Game;
import com.cohort22.data.models.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    List<Game> findAllByStatus(GameStatus gameStatus);
    Optional<Game> findByGamePins_Pin(String gamePin);
    List<Game> findByStudentsId(String id);
    Optional<Game> findByQuizId(String id);
    Optional<Game> findByTeacher(Teacher teacher);
}