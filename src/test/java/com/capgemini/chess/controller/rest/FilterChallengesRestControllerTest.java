package com.capgemini.chess.controller.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

/**
 * @author AWOZNICA
 *
 */
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

	/*
	 * Tests method with declaration
	 * 
	 * @RequestMapping(value = "/byPlayerIds", method = RequestMethod.GET)
	 * public List<ChallengeTO>
	 * getChallengesFilteredByRequestParams(@RequestParam long
	 * senderId,@RequestParam long receiverId)
	 * 
	 */
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

	/*
	 * Tests method with declaration
	 * 
	 * @RequestMapping(value = "/byPlayerIds/{senderId}/{receiverId}", method =
	 * RequestMethod.GET) public List<ChallengeTO>
	 * getChallengesFilteredByPathVariables(@PathVariable("senderId") long
	 * senderId,@PathVariable("receiverId") long receiverId)
	 */
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

	/*
	 * Tests method with declaration
	 * 
	 * @RequestMapping(value = "/byPlayerIdsAndStatus", method =
	 * RequestMethod.GET) public List<ChallengeTO>
	 * getChallengesFilteredByRequestParams(@RequestParam long
	 * senderId,@RequestParam long receiverId, @RequestParam ChallengeStatus
	 * status)
	 */
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

	/*
	 * tests method with declaration
	 * 
	 * @RequestMapping(value =
	 * "/byPlayerIdsAndStatus/{senderId}/{receiverId}/{status}", method =
	 * RequestMethod.GET) public List<ChallengeTO>
	 * getChallengesFilteredByPathVariables(@PathVariable("senderId") long
	 * senderId, @PathVariable("receiverId") long
	 * receiverId, @PathVariable("status") ChallengeStatus status)
	 */

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

	/*
	 * Tests method with declaration
	 * 
	 * @RequestMapping(value = "/byStatusAndPlayerId", method =
	 * RequestMethod.GET) public List<ChallengeTO>
	 * getChallengesFilteredByRequestParams(@RequestParam long
	 * playerId, @RequestParam ChallengeStatus status)
	 */
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

	/*
	 * Tests method with declaration:
	 * 
	 * @RequestMapping(value = "/byStatusAndPlayerId/{playerId}/{status}",
	 * method = RequestMethod.GET) public List<ChallengeTO>
	 * getChallengesFilteredByPathVariables(@PathVariable("playerId") long
	 * playerId,@PathVariable("status") ChallengeStatus status)
	 */
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

	/*
	 * Tests method with declaration:
	 * 
	 * @RequestMapping(value = "/byStatus", method = RequestMethod.GET) public
	 * List<ChallengeTO> getChallengesFilteredByRequestParams(@RequestParam
	 * ChallengeStatus status)
	 */
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

	/*
	 * Tests method with declaration:
	 * 
	 * @RequestMapping(value = "/byStatus/{status}", method = RequestMethod.GET)
	 * public List<ChallengeTO>
	 * getChallengesFilteredByPathVariables(@PathVariable("status")
	 * ChallengeStatus status)
	 */
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

	/*
	 * Tests method with declaration:
	 * 
	 * @RequestMapping(value = "/byParams/{params}", method = RequestMethod.GET)
	 * public List<ChallengeTO>
	 * getChallengesFilteredByCriteria(@MatrixVariable(pathVar = "params")
	 * Map<String, List<String>> filters)
	 */
	@Test
	public void testGetChallengesFilteredByCriteria() throws Exception {
		// given
		final Map<String, List<String>> filters = new HashMap<String, List<String>>();
		filters.put("playerId", Arrays.asList("2"));
		final ChallengeTO givenChallenge = new ChallengeTO();
		List<ChallengeTO> givenFilteredChallenges = Arrays.asList(givenChallenge, givenChallenge);
		String givenFilteredChallengesAsJsonString = asJsonString(givenFilteredChallenges);
		Mockito.when(filterChallengeService.getChallengeFilteredBy(filters)).thenReturn(givenFilteredChallenges);
		// when
		final String createdURLEndingForMatrixVariable = createURLEndingForMatrixVariable(filters);
		String requestPath = "/rest/filterChallenges" + "/byParams/params;" + createdURLEndingForMatrixVariable;
		ResultActions resultActions = mockMvc
				.perform(get(requestPath).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions.andExpect(status().isOk()).andExpect(content().string(givenFilteredChallengesAsJsonString));
	}

	private static String createURLEndingForMatrixVariable(final Map<String, List<String>> filters) {
		String matrixURL = "";
		for (Map.Entry<String, List<String>> entry : filters.entrySet()) {
			matrixURL += ";";
			matrixURL += entry.getKey() + "=";
			for (String paramValue : entry.getValue()) {
				matrixURL += paramValue + ",";
			}
			matrixURL = matrixURL.substring(0, matrixURL.length() - 1);
		}
		return matrixURL;
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
