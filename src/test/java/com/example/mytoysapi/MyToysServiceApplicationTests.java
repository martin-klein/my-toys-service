package com.example.mytoysapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * End-to-end integration test for the application.
 * TODO: Test and handle malformed urls, test unhealthy upstream server.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyToysServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/links?parent=Ratgeber&sort=label:asc", String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(responseEntity.getBody());


		String expectedJSONResponse = "[{\"label\":\"Alle Ratgeber\",\"url\":\"http://www.mytoys.de/c/ratgeber.html\"},{\"label\":\"Baby\",\"url\":\"http://www.mytoys.de/c/babyratgeber.html\"},{\"label\":\"Einschulung\",\"url\":\"http://www.mytoys.de/c/einkaufsberatung-schule-einstiegsseite.html\"},{\"label\":\"Mode\",\"url\":\"http://www.mytoys.de/c/ratgeber-kindermode.html\"},{\"label\":\"PC/Games\",\"url\":\"http://www.mytoys.de/c/einkaufsberatung-pc-und-games-einstiegsseite.html\"},{\"label\":\"Reisen mit Kind\",\"url\":\"http://www.mytoys.de/c/ratgeber-reisen.html\"},{\"label\":\"Sport\",\"url\":\"http://www.mytoys.de/c/einkaufsberatung-sport-einstiegsseite.html\"}]";

		assertEquals(expectedJSONResponse, root.toString());
		assertEquals(200, responseEntity.getStatusCodeValue());

	}

	@Test
	public void illegalSortParameters() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/links?sort=test", String.class);
		int actualResponseCode = responseEntity.getStatusCodeValue();
		assertEquals(400, actualResponseCode);
	}

	/**
	 * TODO: Implement
	 */
	@Test
	@Ignore
	public void upstreamServerUnhealthy(){

	}

	@Test
	public void parentNotFound() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/links?parent=test", String.class);
		int actualResponseCode = responseEntity.getStatusCodeValue();
		assertEquals(404, actualResponseCode);
	}

}
