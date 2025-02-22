package fi.ishtech.practice.oms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.ishtech.practice.oms.OmsApplication;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for info URLs
 *
 * @author Muneer Ahmed Syed
 */
@RestController
@Slf4j
public class HomeController {

	@Value("${spring.application.name}")
	private String appName;

	@Value("${spring.profiles.active:#{null}}")
	private String activeProfile;

	@Value("${spring.datasource.name:}")
	private String ds;

	@GetMapping(path = "/")
	public String index() {
		log.debug("In web root");
		return "Welcome to " + appName;
	}

	@GetMapping("/about")
	public ResponseEntity<Map<String, String>> about() throws IOException {
		log.trace("Getting application details");

		Map<String, String> results = new HashMap<String, String>(0);

		results.put("applicationName", appName);
		results.put("activeProfile", activeProfile);
		results.put("datasource", ds);

		Manifest manifest = new Manifest(OmsApplication.class.getResourceAsStream("/META-INF/MANIFEST.MF"));
		String version = (String) manifest.getMainAttributes().get(Attributes.Name.IMPLEMENTATION_VERSION);
		results.put("version", version);

		log.debug("About={}", results);
		return ResponseEntity.ok(results);
	}

}