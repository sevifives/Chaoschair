package me.sevif.chaoschair.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import me.sevif.chaoschair.db.entity.User;

public interface IUserRepository extends PagingAndSortingRepository<User, Long> {

}
