package com.vivek.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Vivek Gusain");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Vivek","Gusain"));
	}
	
	@GetMapping(value="/person/params", params="version=1")
	public PersonV1 getPersonParamsV1() {
		return new PersonV1("Vivek Gusain");
	}
	
	@GetMapping(value="/person/params", params="version=2")
	public PersonV2 getPersonParamsV2() {
		return new PersonV2(new Name("Vivek","Gusain"));
	}
	
	@GetMapping(value="/person/headers", headers="X-API-VERSION=1")
	public PersonV1 getPersonHeadersV1() {
		return new PersonV1("Vivek Gusain");
	}
	
	@GetMapping(value="/person/headers", headers="X-API-VERSION=2")
	public PersonV2 getPersonHeadersV2() {
		return new PersonV2(new Name("Vivek","Gusain"));
	}
	
	@GetMapping(value="/person/produces", produces="application/TPG.company.app-v1+json")
	public PersonV1 getPersonProducesV1() {
		return new PersonV1("Vivek Gusain");
	}
	
	@GetMapping(value="/person/produces", produces="application/TPG.company.app-v2+json")
	public PersonV2 getPersonProducesV2() {
		return new PersonV2(new Name("Vivek","Gusain"));
	}
}
