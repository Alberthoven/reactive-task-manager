package es.juandavidvega.repository.crud;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import es.juandavidvega.entity.Team;


public interface TeamRepository extends ReactiveMongoRepository<Team, Integer> {


}
