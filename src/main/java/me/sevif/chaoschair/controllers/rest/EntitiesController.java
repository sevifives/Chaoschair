package me.sevif.chaoschair.controllers.rest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping(value="/user", produces = "application/json")
	public @ResponseBody String createUser(@RequestBody User user) throws JSONException {
		User ret = userRepo.save(user);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entity",ret);
		return r.toString();
	}
	
	@PostMapping(value="/ticket", produces = "application/json")
	public @ResponseBody String createTicket(@RequestBody Ticket ticket) throws JSONException {
		Ticket ret = ticketRepo.save(ticket);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entity",ret);
		return r.toString();
	}
	
	@PostMapping(value="/organization", produces = "application/json")
	public @ResponseBody String createOrganization(@RequestBody Organization org) throws JSONException {
		Organization ret = orgRepo.save(org);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entity",ret);
		return r.toString();
	}
	
	@PostMapping(value="/organizations", produces = "application/json")
	public @ResponseBody String createOrganizations(@RequestBody Iterable<Organization> orgs) throws JSONException {
		Iterable<Organization> ret = orgRepo.save(orgs);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entities",ret);
		return r.toString();
	}
	
	@PostMapping(value="/tickets", produces = "application/json")
	public @ResponseBody String createTickets(@RequestBody Iterable<Ticket> tickets) throws JSONException {
		Iterable<Ticket> ret = ticketRepo.save(tickets);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entities",ret);
		return r.toString();
	}
	
	@PostMapping(value="/users", produces = "application/json")
	public @ResponseBody String createUsers(@RequestBody Iterable<User> users) throws JSONException {
		Iterable<User> ret = userRepo.save(users);
		
		JSONObject r = new JSONObject();
		r.put("status",200);
		r.put("entities",ret);
		return r.toString();
	}
}
