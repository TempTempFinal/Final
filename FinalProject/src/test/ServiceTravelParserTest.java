package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lecture.finalproject.service.ServiceTravelParser;

public class ServiceTravelParserTest {

	@Test
	public void test() {
		ServiceTravelParser one = new ServiceTravelParser();
		one.parseTravelPostList();
		one.parseTravelPostInformation();
	}
}
