package challenge.alura.literAlura;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LiterAluraApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetBooks() {
		String url = "http://localhost:" + port + "/books?authorYearStart=1900&languages=en,fr";
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).isNotEmpty();
	}
}
