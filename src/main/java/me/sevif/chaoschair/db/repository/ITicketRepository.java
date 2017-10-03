package me.sevif.chaoschair.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import me.sevif.chaoschair.db.entity.Ticket;

public interface ITicketRepository extends PagingAndSortingRepository<Ticket,Long> {

}
