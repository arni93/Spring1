package com.capgemini.chess.controller.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import com.capgemini.chess.service.ManualChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.UserProfileTO;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author AWOZNICA
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ChessApplication.class)
@WebAppConfiguration
public class ManualChallengeRestControllerTest {

	@Mock
	private ManualChallengeService manualChallengeService;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ManualChallengeRestController manualChallengeRestController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(this.manualChallengeService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		ReflectionTestUtils.setField(manualChallengeRestController, "manualChallengeService", manualChallengeService);
	}

	/*
	 * Tests method with declaration:
	 * 
	 * @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
	 * public UserProfileTO findUserById(@RequestParam("id") long userId)
	 */
	@Test
	public void testFindUserByIdRestService() throws Exception {
		// given
		final Long givenId = 20l;
		final String givenName = "aaa";
		final UserProfileTO givenUserProfile = new UserProfileTO();
		givenUserProfile.setId(givenId);
		givenUserProfile.setLogin(givenName);
		String expectedContent = asJsonString(givenUserProfile);
		Mockito.when(this.manualChallengeService.findUserById(givenId)).thenReturn(givenUserProfile);
		// when
		ResultActions resultActions = mockMvc
				.perform(get("/rest/manualChallenge/findUserById").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).param("id", givenId.toString()));
		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("id").value((givenId.intValue())))
				.andExpect(jsonPath("login").value(givenName)).andExpect(content().string(expectedContent));

	}

	/*
	 * tests method with declaration
	 * 
	 * @RequestMapping(value = "/findUsersByName", method = RequestMethod.GET)
	 * public List<UserProfileTO> findUserByName(@RequestParam("name") String
	 * userName)
	 */

	@Test
	public void testFindUsersByNameRestService() throws Exception {
		// given
		final String givenName = "aaa";
		final UserProfileTO givenUserProfile = new UserProfileTO();
		givenUserProfile.setName(givenName);
		List<UserProfileTO> expectedResult = Arrays.asList(givenUserProfile, givenUserProfile);
		String expectedResultAsJsonString = asJsonString(expectedResult);
		Mockito.when(this.manualChallengeService.findUserByName(givenName))
				.thenReturn(Arrays.asList(givenUserProfile, givenUserProfile));
		// when
		ResultActions resultActions = mockMvc.perform(get("/rest/manualChallenge/findUsersByName")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).param("name", givenName));
		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("[0].name").value(givenName))
				.andExpect(jsonPath("[1].name").value(givenName))
				.andExpect(content().string(expectedResultAsJsonString));
	}

	/*
	 * tests method with declaration:
	 * 
	 * @RequestMapping(value = "/findUsersBySurname", method =
	 * RequestMethod.GET) public List<UserProfileTO>
	 * findUserBySurname(@RequestParam("surname") String userSurname)
	 */
	@Test
	public void testFindUsersBySurnameRestService() throws Exception {
		// given
		final String givenSurname = "bbb";
		final UserProfileTO givenUserProfile = new UserProfileTO();
		givenUserProfile.setSurname(givenSurname);
		List<Object> expectedResult = Arrays.asList(givenUserProfile, givenUserProfile);
		String expectedResultAsJsonString = asJsonString(expectedResult);
		Mockito.when(this.manualChallengeService.findUserBySurname(givenSurname))
				.thenReturn(Arrays.asList(givenUserProfile, givenUserProfile));
		// when
		ResultActions resultActions = mockMvc
				.perform(get("/rest/manualChallenge/findUsersBySurname").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).param("surname", givenSurname));
		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("[0].surname").value(givenSurname))
				.andExpect(jsonPath("[1].surname").value(givenSurname))
				.andExpect(content().string(expectedResultAsJsonString));
	}

	/*
	 * tests method with declaration:
	 * 
	 * @RequestMapping(value = "/createChallenge", method = RequestMethod.POST)
	 * public boolean createChallenge(@RequestBody ChallengeTO challenge)
	 */
	@Test
	public void testCreateChallengeRestService() throws Exception {
		// given
		final ChallengeTO givenChallengeToCreate = new ChallengeTO();
		final long givenSenderPlayerId = 1L;
		final long givenReceiverPlayerId = 2L;
		Boolean expectedStatus = true;
		givenChallengeToCreate.setSenderPlayerId(givenSenderPlayerId);
		givenChallengeToCreate.setReceiverPlayerId(givenReceiverPlayerId);
		givenChallengeToCreate.setChallengeStatus(ChallengeStatus.ACCEPTED);
		Mockito.when(manualChallengeService.createChallenge(givenSenderPlayerId, givenReceiverPlayerId))
				.thenReturn(true);
		// when
		ResultActions resultActions = mockMvc
				.perform(post("/rest/manualChallenge/createChallenge").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(asJsonString(givenChallengeToCreate)));
		// then
		resultActions.andExpect(status().isOk()).andExpect(content().string(expectedStatus.toString()));
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
