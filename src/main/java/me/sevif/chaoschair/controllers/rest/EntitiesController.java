package me.sevif.chaoschair.controllers.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.sevif.chaoschair.db.entity.Organization;
import me.sevif.chaoschair.db.entity.Ticket;
import me.sevif.chaoschair.db.entity.User;
import me.sevif.chaoschair.db.repository.IOrganizationRepository;
import me.sevif.chaoschair.db.repository.ITicketRepository;
import me.sevif.chaoschair.db.repository.IUserRepository;

@RestController("/rest/1.0")
public class EntitiesController {
	
	@Autowired private IUserRepository userRepo;
	@Autowired private ITicketRepository ticketRepo;
	@Autowired private IOrganizationRepository orgRepo;

	@PostMapping(value="/rest/1.0/user", produces = "application/json")
	public @ResponseBody String createUser(@RequestBody User user) throws JSONException {
		User ret = userRepo.save(user);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entity",ret);
		return r.toString();
	}
	
	@PostMapping(value="/rest/1.0/ticket", produces = "application/json")
	public @ResponseBody String createTicket(@RequestBody Ticket ticket) throws JSONException {
		Ticket ret = ticketRepo.save(ticket);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entity",ret);
		return r.toString();
	}
	
	@PostMapping(value="/rest/1.0/organization", produces = "application/json")
	public @ResponseBody String createOrganization(@RequestBody Organization org) throws JSONException {
		Organization ret = orgRepo.save(org);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entity",ret);
		return r.toString();
	}
	
	@PostMapping(value="/rest/1.0/organizations", produces = "application/json")
	public @ResponseBody String createOrganizations(@RequestBody Iterable<Organization> orgs) throws JSONException {
		Iterable<Organization> ret = orgRepo.save(orgs);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entities",ret);
		r.put("count", orgRepo.count());
		return r.toString();
	}
	
	@PostMapping(value="/rest/1.0/tickets", produces = "application/json")
	public @ResponseBody String createTickets(@RequestBody Iterable<Ticket> tickets) throws JSONException {
		Iterable<Ticket> ret = ticketRepo.save(tickets);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entities",ret);
		r.put("count", ticketRepo.count());
		return r.toString();
	}
	
	@PostMapping(value="/rest/1.0/users", produces = "application/json")
	public @ResponseBody String createUsers(@RequestBody Iterable<User> users) throws JSONException {
		Iterable<User> ret = userRepo.save(users);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entities",ret);
		r.put("count", userRepo.count());
		return r.toString();
	}
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@GetMapping(value="/rest/1.0/search", produces = "application/json") 
	public Object search(
				@RequestParam(name="q", required=false) String search,
				Pageable pageable
			) {
		
		
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
			    .withQuery(QueryBuilders.matchAllQuery())
			    .withIndices("organization")
			    .withPageable(pageable)
			    .build();
		
		return elasticsearchTemplate.queryForPage(searchQuery, Object.class);
	}
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@GetMapping(value="/rest/1.0/search/organizations", produces = "application/json") 
	public @ResponseBody Page<Organization> searchOrganization(
				@RequestParam(name="q", required=false) String search,
				Pageable pageable
			) throws JsonProcessingException {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
			    .withQuery(QueryBuilders.multiMatchQuery(search, "_all").fuzziness(Fuzziness.AUTO))
			    .withPageable(pageable)
			    .build();
		
		AggregatedPage<Organization> ret = elasticsearchTemplate.queryForPage(searchQuery, Organization.class);
		
		return new PageImpl<Organization>(ret.getContent(), pageable, ret.getTotalElements());
	}
	
	@GetMapping(value="/rest/1.0/search/tickets", produces = "application/json") 
	public @ResponseBody Page<Ticket> searchTickets(
				@RequestParam(name="q", required=false) String search,
				Pageable pageable
			) throws JsonProcessingException {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.multiMatchQuery(search, "_all").fuzziness(Fuzziness.AUTO))
			    .withPageable(pageable)
			    .build();
		
		AggregatedPage<Ticket> ret = elasticsearchTemplate.queryForPage(searchQuery, Ticket.class);
		
		return new PageImpl<Ticket>(ret.getContent(), pageable, ret.getTotalElements());
	}
	
	@GetMapping(value="/rest/1.0/search/users", produces = "application/json") 
	public @ResponseBody Page<User> searchUsers(
				@RequestParam(name="q", required=false) String search,
				Pageable pageable
			) throws JsonProcessingException {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.multiMatchQuery(search, "_all").fuzziness(Fuzziness.AUTO))
			    .withPageable(pageable)
			    .build();
		
		AggregatedPage<User> ret = elasticsearchTemplate.queryForPage(searchQuery, User.class);
		
		return new PageImpl<User>(ret.getContent(), pageable, ret.getTotalElements());
	}
}
