package com.cognit.watson.nlc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognit.watson.nlc.service.WatsonNLCService;
import com.cognit.watson.nlc.utilities.Validator;

/**
 * @author Souad
 *
 */
@Controller
public class WatsonNLCController {

	@Autowired
	WatsonNLCService WatsonNLCService;

	/**
	 * @param model
	 * @return home page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		return "natural-language-classifier";
	}

	/**
	 * classify question by calling nlc service and return The class with the
	 * highest confidence
	 * 
	 * @param model
	 * @param question
	 * @return view
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String classifyQuestion(Map<String, Object> model, @RequestParam String question) {
		try {
			if (!Validator.isEmptyOrNull(question)) {
				model.put("classifierClass", WatsonNLCService.classifyQuestion(question));
			}

		} catch (Exception e) {
			/* show error message in case on any exception */
			model.put("errorMessage", e.getMessage());
		}
		return "natural-language-classifier";
	}

	/**
	 * this method called if sample question link clicked
	 * 
	 * @param model
	 * @param question
	 * @return the classified class
	 */
	@RequestMapping(value = "/ajax", method = RequestMethod.POST)
	public @ResponseBody String classifyQuestionAjax(Map<String, Object> model, @RequestParam String question) {
		String classifierClass = null;
		try {
			if (!Validator.isEmptyOrNull(question)) {
				classifierClass = WatsonNLCService.classifyQuestion(question);
			}
		} catch (Exception e) {
			model.put("errorMessage", e.getMessage());
		}
		return classifierClass;
	}
}
