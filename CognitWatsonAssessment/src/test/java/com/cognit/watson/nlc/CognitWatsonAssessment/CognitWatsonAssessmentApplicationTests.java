package com.cognit.watson.nlc.CognitWatsonAssessment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognit.watson.nlc.service.WatsonNLCService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CognitWatsonAssessmentApplicationTests {

	@Autowired
	WatsonNLCService watsonNLCservice;

	private static final  String EMIRATE_QUESTION="What is the capital of UAE";
	private static final  String WATSON_QUESTION="What is IBM Watson";
	private static final  String WATSON_ANSWER="IBM Watson is a cognitive system developed by IBM";
	private static final  String TEMPERATURE_QUESTION="What is the temperature outside today";

	
	@Test
	public void contextLoads() {
	}

	/**
	 * Test classify function of natural language classifier service by using
	 * the pre created trained classifier
	 *
	 * @throws Exception
	 */
	@Test
	public void shouldReturnValidClassifier() throws Exception {
		
		/*Test first Question about UAE Capital*/
		String classifyClass = watsonNLCservice.classifyQuestion(EMIRATE_QUESTION);
		
		assertNotNull(classifyClass);
		assertEquals(classifyClass, "Abu Dhabi");
		
		/*Test first Question about IBM Watson*/
		classifyClass = watsonNLCservice.classifyQuestion(WATSON_QUESTION);
		
		assertNotNull(classifyClass);
		assertEquals(classifyClass, WATSON_ANSWER);

		/*Test first Question about Temperature*/
		classifyClass = watsonNLCservice.classifyQuestion(TEMPERATURE_QUESTION);
		
		assertNotNull(classifyClass);
		assertEquals(classifyClass, "It is very hot outside");
		

	}
	
	/**
	 * Test Create new classifier by provide classifier name and training data 
	 *
	 * @throws Exception
	 */
	@Test
	public void shouldReturnClassifierCreatedId() throws Exception {
		
		//watsonNLCservice.createClassifier("NLC-tes","");
	}
	
	/**
	 * check classifier status
	 *
	 * @throws Exception
	 */
	@Test
	public void shouldReturnTrueIfClassifierAvailable() throws Exception {
		
		boolean classifierStatus=watsonNLCservice.isClassifierAvaiable();
		assertTrue(classifierStatus);
	}
}
