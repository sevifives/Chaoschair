package me.sevif.chaoschair.controllers.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.sevif.chaoschair.db.entity.Organization;
import me.sevif.chaoschair.db.entity.Ticket;
import me.sevif.chaoschair.db.entity.User;

@Controller
@RequestMapping("/")
public class RootController {
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@GetMapping("/search")
	public String home(
			Map<String, Object> model,
			@RequestParam(name="q", required=false, defaultValue="") String search,
			@RequestParam(name="e", defaultValue="organizations", required=true) String ent,
			@RequestParam(name="s", defaultValue="false") boolean isStrict,
			Pageable pageable) throws UnsupportedEncodingException {
		
		String decodedSearch = URLDecoder.decode(search,"UTF-8");
		
		SearchQuery searchQuery;
		// No special tricks here - just let the power of ES take over.
		// Strict puts almost all the URI power in the query while !strict
		// does auto-fuzzy matching. Don't really need tests around this
		// since it's not really a in-depth operation beyond using APIs
		if (isStrict) {
			searchQuery = new NativeSearchQueryBuilder()
			    .withQuery(QueryBuilders.queryStringQuery(decodedSearch))
			    .withPageable(pageable)
			    .build();
		} else {
			searchQuery = new NativeSearchQueryBuilder()
			    .withQuery(QueryBuilders.multiMatchQuery(decodedSearch, "_all").fuzziness(Fuzziness.AUTO))
			    .withPageable(pageable)
			    .build();
		}
		
		AggregatedPage ret;
		
		if (ent.equalsIgnoreCase("organizations")) {
			ret = elasticsearchTemplate.queryForPage(searchQuery, Organization.class);
		} else if (ent.equalsIgnoreCase("tickets")) {
			ret = elasticsearchTemplate.queryForPage(searchQuery, Ticket.class);
		} else if (ent.equalsIgnoreCase("users")) {
			ret = elasticsearchTemplate.queryForPage(searchQuery, User.class);
		} else {
			throw new NoClassDefFoundError();
		}
		PageImpl<Object> p = new PageImpl<Object>(ret.getContent(), pageable, ret.getTotalElements());
		
		model.put("results", p);
		model.put("query", search.replaceAll("^\'","").replaceAll("\'$",""));
		model.put("isStrict",isStrict);
		model.put("entity", ent);
		if (p.nextPageable() != null) {
			model.put("nextPage", String.format("?e=%s&s=%b&q=%s&page=%d", ent, isStrict, search, p.nextPageable().getPageNumber()));
		}
		if (p.previousPageable() != null) {
			model.put("prevPage", String.format("?e=%s&s=%b&q=%s&page=%d", ent, isStrict, search, p.previousPageable().getPageNumber()));
		}
		
		return "search";
	}
}
