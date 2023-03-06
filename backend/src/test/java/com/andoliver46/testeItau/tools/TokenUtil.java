package com.andoliver46.testeItau.tools;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andoliver46.testeItau.dtos.authentication.AuthenticationRequest;
import com.andoliver46.testeItau.entities.UserAccess;
import com.andoliver46.testeItau.repositories.UserAccessRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class TokenUtil {

	@Autowired
	UserAccessRepository userAccessRepository;

	@Autowired
	private ObjectMapper mapper;

	public String obtainAccessToken(MockMvc mockMvc, String username, String password) throws Exception {

		AuthenticationRequest request = new AuthenticationRequest(username, password);
		String json = mapper.writeValueAsString(request);

		ResultActions result = mockMvc
				.perform(post("/api/login").content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("token").toString();
	}
}
