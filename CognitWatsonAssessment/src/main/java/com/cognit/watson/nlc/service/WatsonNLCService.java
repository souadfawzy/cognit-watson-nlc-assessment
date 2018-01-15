package com.cognit.watson.nlc.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.cognit.watson.nlc.constants.NLCServiceConstants;
import com.cognit.watson.nlc.utilities.Validator;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classifier.Status;

/**
 * @author Souad
 *
 */
@Service
public class WatsonNLCService {

	/**
	 * check if classifier status is available before use or not
	 * 
	 * @return true if status available,false otherwise
	 * 
	 */
	public boolean isClassifierAvaiable() {

		NaturalLanguageClassifier service = new NaturalLanguageClassifier();
		service.setUsernameAndPassword(NLCServiceConstants.USER_NAME, NLCServiceConstants.PASSWORD);

		Classifier classifier = service.getClassifier(NLCServiceConstants.CLASSIFIER_ID).execute();
		if (classifier.getStatus() == Status.AVAILABLE)

			return true;
		return false;
	}

	/**
	 * Find suitable classifier for asked question from trained data by call NLC
	 * service classify method
	 * 
	 * @param textToClassify
	 *            question you want to find the intent class for
	 * @return classified class
	 */
	public String classifyQuestion(String textToClassify) throws Exception {

		if (!Validator.isEmptyOrNull(textToClassify)) {
			/*
			 * Instantiates a new natural language service by username and
			 * password service credentials
			 */
			NaturalLanguageClassifier service = new NaturalLanguageClassifier();
			service.setUsernameAndPassword(NLCServiceConstants.USER_NAME, NLCServiceConstants.PASSWORD);
			/*
			 * Returns classification Details for a question as JSON but only we
			 * need topclass value
			 */
			Classification classification = service.classify(NLCServiceConstants.CLASSIFIER_ID, textToClassify)
					.execute();
			if (classification != null && !Validator.isEmptyOrNull(classification.getTopClass())) {
				return classification.getTopClass();
			}
		}
		return null;
	}

	/**
	 * create and train a classifier and returns classifier id to use it later
	 * in classify method
	 * 
	 * @param classifierName
	 *            classifier name
	 * @param trainingFilePath
	 *            Training data in CSV format file path
	 * @return classifier id if created and null if there is error occurred
	 */
	public String createClassifier(String classifierName, String trainingFilePath) throws Exception {

		if (!Validator.isEmptyOrNull(classifierName) && !Validator.isEmptyOrNull(trainingFilePath)) {

			NaturalLanguageClassifier service = new NaturalLanguageClassifier();
			service.setUsernameAndPassword(NLCServiceConstants.USER_NAME, NLCServiceConstants.PASSWORD);

			Classifier classifier = service.createClassifier(classifierName, "en", new File(trainingFilePath))
					.execute();
			if (classifier != null) {
				return classifier.getId();
			}
		}
		return null;

	}

	/**
	 * Delete classifier
	 * 
	 * @param classifierId
	 *            Classifier ID to delete
	 * @throws Exception
	 */
	public void deleteClassifier(String classifierId) throws Exception {
		if (!Validator.isEmptyOrNull(classifierId)) {
			NaturalLanguageClassifier service = new NaturalLanguageClassifier();
			service.setUsernameAndPassword(NLCServiceConstants.USER_NAME, NLCServiceConstants.PASSWORD);
			service.deleteClassifier(classifierId);
		}
	}
}
