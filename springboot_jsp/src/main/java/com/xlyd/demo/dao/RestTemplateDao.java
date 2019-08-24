package com.xlyd.demo.dao;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

public class RestTemplateDao {

	private static final String GET_URL = "get_url";
	private static final String POST_URL = "post_url";

	@Autowired
	private RestTemplate template;

	public void getOps() {
		String str = template.getForObject(GET_URL, String.class);
		System.out.println(str);

		ResponseEntity<String> re = template.getForEntity(GET_URL, String.class);
		str = re.getBody();
		System.out.println(str);
	}

	public void postOps() {
		ResponseEntity<Boolean> flag = template.postForEntity(POST_URL, null, Boolean.class);
		HttpHeaders headers = flag.getHeaders();
		headers.keySet();
	}

	public void exchangeOps() {
		String body = "body";
		URI uri = new UriTemplate("http://example.com/{foo}").expand("bar");
		
		RequestEntity<String> request = RequestEntity
											.post(uri)
											.accept(MediaType.APPLICATION_JSON)
											.body(body);
		ResponseEntity<String> response = template.exchange(request, String.class);
		response.getBody();
		response.getHeaders();
	}
}
