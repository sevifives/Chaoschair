package me.sevif.chaoschair.db.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Component;

import me.sevif.chaoschair.db.entity.User;

@Component
public interface IUserRepository extends ElasticsearchCrudRepository<User, Long> {

}
