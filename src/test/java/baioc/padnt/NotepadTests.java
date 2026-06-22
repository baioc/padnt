package baioc.padnt;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureRestTestClient
public class NotepadTests {

	@Autowired
	private NotepadService notepads;

	@Test
	public void notepadListDirectory() {
		var ls = notepads.list("/my/");
		var expected = new ArrayList<>(List.of("notes", "stuff", "online-notepad"));
		expected.sort(null);
		assertArrayEquals(expected.toArray(), ls.toArray());
	}

	private static void getEquals(RestTestClient http, String uri, String expected) {
		http
			.get().uri(uri)
			.accept(MediaType.TEXT_PLAIN)
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class).isEqualTo(expected);
	}

	private static void putOk(RestTestClient http, String uri, String body) {
		http
			.put().uri(uri)
			.contentType(MediaType.TEXT_PLAIN)
			.body(body)
			.exchange()
			.expectStatus().isOk();
	}

	private static void deleteOk(RestTestClient http, String uri) {
		http
			.delete().uri(uri)
			.exchange()
			.expectStatus().isOk();
	}

	@Test
	public void getExisting(@Autowired RestTestClient http) {
		getEquals(http, "/my/online-notepad", "Byen't, World!");
	}

	@Test
	public void getNotExisting(@Autowired RestTestClient http) {
		getEquals(http, "/test/getNotExisting", null);
	}

	@Test
	public void getInvalid(@Autowired RestTestClient http) {
		http
			.get().uri("/test/.invalid")
			.exchange()
			.expectStatus().isNotFound();
	}

	@Test
	public void putNewAndDelete(@Autowired RestTestClient http) {
		var path = "/test/putNewAndDelete";

		putOk(http, path, "testing");
		getEquals(http, path, "testing");

		deleteOk(http, path);
		getEquals(http, path, null);
	}

	@Test
	public void putChangeAndEmpty(@Autowired RestTestClient http) {
		var path = "/test/putChangeAndEmpty";

		putOk(http, path, "created");
		getEquals(http, path, "created");

		putOk(http, path, "edited");
		getEquals(http, path, "edited");

		putOk(http, path, "");
		getEquals(http, path, null);
	}

	@Test
	public void deleteNotExisting(@Autowired RestTestClient http) {
		deleteOk(http, "/test/deleteNotExisting");
	}

	@Test
	public void requestErrorNotepad(@Autowired RestTestClient http) {
		getEquals(http, "/error", null);
		// ^ should no longer be a Spring error endpoint
	}

}
