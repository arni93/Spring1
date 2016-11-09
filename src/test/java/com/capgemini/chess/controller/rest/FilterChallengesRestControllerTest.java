package com.capgemini.chess.controller.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.FilterChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ChessApplication.class)
@WebAppConfiguration
public class FilterChallengesRestControllerTest {
	@Mock
	private FilterChallengeService filterChallengeService;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private FilterChallengesRestController filterChallengesRestController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(this.filterChallengeService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		ReflectionTestUtils.setField(filterChallengesRestController, "filterChallengeService", filterChallengeService);
	}

	@Test
	public void testGetChallengesFilteredByRequestParam1() throws Exception {
		// given
		final Long givenSenderId = 1L;
		final Long givenReceiverId = 2L;
		final ChallengeTO givenChallenge = new ChallengeTO();
		givenChallenge.setSenderPlayerId(givenSenderId);
		givenChallenge.setReceiverPlayerId(givenReceiverId);
		givenChallenge.setChallengeStatus(ChallengeStatus.SEND);
		final List<ChallengeTO> resultFilteredChallenges = Arrays.asList(givenChallenge, givenChallenge);
		final String resultFilteredChallengesAsJsonString = asJsonString(resultFilteredChallenges);
		Mockito.when(filterChallengeService.getChallengesFilteredBy(givenSenderId, givenReceiverId))
				.thenReturn(resultFilteredChallenges);
		// when
		ResultActions resultActions = mockMvc.perform(get("/rest/filterChallenges/byPlayerIds")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.param("senderId", givenSenderId.toString()).param("receiverId", givenReceiverId.toString()));
		// then
		resultActions.andExpect(status().isOk()).andExpect(content().string(resultFilteredChallengesAsJsonString));
	}

	@Test
	public void testGetChallengesFilteredByPathVariables() throws Exception {
		// given
		final Long givenSenderId = 1L;
		final Long givenReceiverId = 2L;
		final ChallengeTO givenChallenge = new ChallengeTO();
		givenChallenge.setSenderPlayerId(givenSenderId);
		givenChallenge.setReceiverPlayerId(givenReceiverId);
		givenChallenge.setChallengeStatus(ChallengeStatus.SEND);
		final List<ChallengeTO> resultFilteredChallenges = Arrays.asList(givenChallenge, givenChallenge);
		final String resultFilteredChallengesAsJsonString = asJsonString(resultFilteredChallenges);
		Mockito.when(filterChallengeService.getChallengesFilteredBy(givenSenderId, givenReceiverId))
				.thenReturn(resultFilteredChallenges);
		final String requestPath = "/rest/filterChallenges/byPlayerIds/" + givenSenderId.intValue() + "/"
				+ givenReceiverId.intValue();
		// when
		ResultActions resultActions = mockMvc
				.perform(get(requestPath).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions.andExpect(status().isOk()).andExpect(content().string(resultFilteredChallengesAsJsonString));
	}

	@Test
	public void testGetChallengesFilteredByRequestParam2() throws Exception {
		// given
		final Long givenSenderId = 1L;
		final Long givenReceiverId = 2L;
		final ChallengeStatus givenChallengeStatus = ChallengeStatus.ACCEPTED;
		final ChallengeTO givenChallenge = new ChallengeTO();
		givenChallenge.setSenderPlayerId(givenSenderId);
		givenChallenge.setReceiverPlayerId(givenReceiverId);
		givenChallenge.setChallengeStatus(givenChallengeStatus);
		final List<ChallengeTO> resultFilteredChallenges = Arrays.asList(givenChallenge, givenChallenge);
		final String resultFilteredChallengesAsJsonString = asJsonString(resultFilteredChallenges);
		Mockito.when(
				filterChallengeService.getChallengesFilteredBy(givenSenderId, givenReceiverId, givenChallengeStatus))
				.thenReturn(resultFilteredChallenges);
		// when
		ResultActions resultActions = mockMvc.perform(get("/rest/filterChallenges/byPlayerIdsAndStatus")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.param("senderId", givenSenderId.toString()).param("receiverId", givenReceiverId.toString())
				.param("status", givenChallengeStatus.toString()));
		// then
		resultActions.andExpect(status().isOk()).andExpect(content().string(resultFilteredChallengesAsJsonString));
	}

	@Test
	public void testGetChallengesFilteredByPathVariables2() throws Exception {
		// given
		final Long givenSenderId = 1L;
		final Long givenReceiverId = 2L;
		final ChallengeStatus givenChallengeStatus = ChallengeStatus.ACCEPTED;
		final ChallengeTO givenChallenge = new ChallengeTO();
		givenChallenge.setSenderPlayerId(givenSenderId);
		givenChallenge.setReceiverPlayerId(givenReceiverId);
		givenChallenge.setChallengeStatus(givenChallengeStatus);
		final List<ChallengeTO> resultFilteredChallenges = Arrays.asList(givenChallenge, givenChallenge);
		final String resultFilteredChallengesAsJsonString = asJsonString(resultFilteredChallenges);
		Mockito.when(
				filterChallengeService.getChallengesFilteredBy(givenSenderId, givenReceiverId, givenChallengeStatus))
				.thenReturn(resultFilteredChallenges);
		// when
		final String requestPath = "/rest/filterChallenges//byPlayerIdsAndStatus/" + givenSenderId.intValue() + "/"
				+ givenReceiverId.intValue() + "/" + givenChallengeStatus.toString();
		ResultActions resultActions = mockMvc
				.perform(get(requestPath).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions.andExpect(status().isOk()).andExpect(content().string(resultFilteredChallengesAsJsonString));
	}

	@Test
	public void testGetChallengesFilteredByRequestParam3() throws Exception {
		// given
		final Long givenPlayerId = 1L;
		final ChallengeStatus givenChallengeStatus = ChallengeStatus.ACCEPTED;
		final ChallengeTO givenChallenge = new ChallengeTO();
		givenChallenge.setSenderPlayerId(givenPlayerId);
		givenChallenge.setChallengeStatus(givenChallengeStatus);
		final List<ChallengeTO> resultFilteredChallenges = Arrays.asList(givenChallenge, givenChallenge);
		final String resultFilteredChallengesAsJsonString = asJsonString(resultFilteredChallenges);
		Mockito.when(filterChallengeService.getChallengesFilteredBy(givenPlayerId, givenChallengeStatus))
				.thenReturn(resultFilteredChallenges);
		// when
		ResultActions resultActions = mockMvc.perform(get("/rest/filterChallenges/byStatusAndPlayerId")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.param("playerId", givenPlayerId.toString()).param("status", givenChallengeStatus.toString()));
		// then
		resultActions.andExpect(status().isOk()).andExpect(content().string(resultFilteredChallengesAsJsonString));
	}

	@Test
	public void testGetChallengesFilteredByPathVariables3() throws Exception {
		// given
		final Long givenPlayerId = 1L;
		final ChallengeStatus givenChallengeStatus = ChallengeStatus.ACCEPTED;
		final ChallengeTO givenChallenge = new ChallengeTO();
		givenChallenge.setSenderPlayerId(givenPlayerId);
		givenChallenge.setChallengeStatus(givenChallengeStatus);
		final List<ChallengeTO> resultFilteredChallenges = Arrays.asList(givenChallenge, givenChallenge);
		final String resultFilteredChallengesAsJsonString = asJsonString(resultFilteredChallenges);
		Mockito.when(filterChallengeService.getChallengesFilteredBy(givenPlayerId, givenChallengeStatus))
				.thenReturn(resultFilteredChallenges);
		// when
		final String requestPath = "/rest/filterChallenges/byStatusAndPlayerId/" + givenPlayerId.intValue() + "/"
				+ givenChallengeStatus.toString();
		ResultActions resultActions = mockMvc
				.perform(get(requestPath).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions.andExpect(status().isOk()).andExpect(content().string(resultFilteredChallengesAsJsonString));
	}

	@Test
	public void testGetChallengesFilteredByRequestParam4() throws Exception {
		// given
		final ChallengeStatus givenChallengeStatus = ChallengeStatus.ACCEPTED;
		final ChallengeTO givenChallenge = new ChallengeTO();
		givenChallenge.setChallengeStatus(givenChallengeStatus);
		final List<ChallengeTO> resultFilteredChallenges = Arrays.asList(givenChallenge, givenChallenge);
		final String resultFilteredChallengesAsJsonString = asJsonString(resultFilteredChallenges);
		Mockito.when(filterChallengeService.getChallengesFilteredBy(givenChallengeStatus))
				.thenReturn(resultFilteredChallenges);
		// when
		ResultActions resultActions = mockMvc
				.perform(get("/rest/filterChallenges/byStatus").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).param("status", givenChallengeStatus.toString()));
		// then
		resultActions.andExpect(status().isOk()).andExpect(content().string(resultFilteredChallengesAsJsonString));
	}

	@Test
	public void testGetChallengesFilteredByPathVariables4() throws Exception {
		// given
		final ChallengeStatus givenChallengeStatus = ChallengeStatus.ACCEPTED;
		final ChallengeTO givenChallenge = new ChallengeTO();
		givenChallenge.setChallengeStatus(givenChallengeStatus);
		final List<ChallengeTO> resultFilteredChallenges = Arrays.asList(givenChallenge, givenChallenge);
		final String resultFilteredChallengesAsJsonString = asJsonString(resultFilteredChallenges);
		Mockito.when(filterChallengeService.getChallengesFilteredBy(givenChallengeStatus))
				.thenReturn(resultFilteredChallenges);
		// when
		final String requestPath = "/rest/filterChallenges/byStatus/" + givenChallengeStatus.toString();
		ResultActions resultActions = mockMvc
				.perform(get(requestPath).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions.andExpect(status().isOk()).andExpect(content().string(resultFilteredChallengesAsJsonString));
	}

	/**
	 * (Example) Sample method which convert's any object from Java to String
	 */
	private static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
