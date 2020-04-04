package com.vivek.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filteredData")
	public MappingJacksonValue getFilteredData() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		return this.doFiltering(someBean, "field1", "field3");
	}
	
//	@GetMapping("/filteredDataList")
//	public MappingJacksonValue getAllFilteredData() {
//		List<SomeBean> someBean = Arrays.asList(
//				new SomeBean("value1", "value2", "value3"),
//				new SomeBean("value12", "value22", "value32")
//		);
//		
//		return this.doFiltering(someBean, "field2", "field3");
//	}
	
	private MappingJacksonValue doFiltering(SomeBean someBean, String... fieldsArray) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsArray);
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
