package me.sevif.chaoschair.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import me.sevif.chaoschair.db.entity.Organization;

public interface IOrganizationRepository extends PagingAndSortingRepository<Organization,Long> {

}
