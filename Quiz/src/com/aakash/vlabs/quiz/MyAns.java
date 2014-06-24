package com.aakash.vlabs.quiz;

import java.util.ArrayList;

public class MyAns {

	// Data of a Single Question
	// This may be updated at runtime
	
	private String Qn,QnType;
	private int QnNo;
	private float scoredWeight;
	private boolean isAnswred;
	
	// Case : True / False
	private String trueAns,subAns;
	
	// Case : Multiple Choice
	private String truemulOptAns,submulOptAns;
	
	// Case : Multiple Many Choice & Short Answer
	private ArrayList<String> truemulManyAns,submulManyAns,mulFeedback;
	private String subShortAns;
	
	// Case : Numeric
	private ArrayList<float[]> trueNumeric;
	private float subNumeric;
	
	// Case : Matching
	private ArrayList<String[]> trueMatch,subMatch;
	
	// Feedback
	private String feedback;

	public String getQn() {
		return Qn;
	}

	public void setQn(String qn) {
		Qn = qn;
	}

	public String getQnType() {
		return QnType;
	}

	public void setQnType(String qnType) {
		QnType = qnType;
	}

	public int getQnNo() {
		return QnNo;
	}

	public void setQnNo(int qnNo) {
		QnNo = qnNo;
	}

	public float getScoredWeight() {
		return scoredWeight;
	}

	public void setScoredWeight(float scoredWeight) {
		this.scoredWeight = scoredWeight;
	}

	public boolean isAnswred() {
		return isAnswred;
	}

	public void setAnswred(boolean isAnswred) {
		this.isAnswred = isAnswred;
	}

	public String getTrueAns() {
		return trueAns;
	}

	public void setTrueAns(String trueAns) {
		this.trueAns = trueAns;
	}

	public String getSubAns() {
		return subAns;
	}

	public void setSubAns(String subAns) {
		this.subAns = subAns;
	}

	public String getTruemulOptAns() {
		return truemulOptAns;
	}

	public void setTruemulOptAns(String truemulOptAns) {
		this.truemulOptAns = truemulOptAns;
	}

	public String getSubmulOptAns() {
		return submulOptAns;
	}

	public void setSubmulOptAns(String submulOptAns) {
		this.submulOptAns = submulOptAns;
	}

	public float getSubNumeric() {
		return subNumeric;
	}

	public void setSubNumeric(float subNumeric) {
		this.subNumeric = subNumeric;
	}


	// Constructors with Question No
	public MyAns(int qnNo){
		this.QnNo = qnNo;
	}

	public ArrayList<String> getTruemulManyAns() {
		return truemulManyAns;
	}

	public void setTruemulManyAns(ArrayList<String> truemulManyAns) {
		this.truemulManyAns = truemulManyAns;
	}

	public ArrayList<String> getSubmulManyAns() {
		return submulManyAns;
	}

	public void setSubmulManyAns(ArrayList<String> submulManyAns) {
		this.submulManyAns = submulManyAns;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public ArrayList<String> getMulFeedback() {
		return mulFeedback;
	}

	public void setMulFeedback(ArrayList<String> mulFeedback) {
		this.mulFeedback = mulFeedback;
	}

	public ArrayList<float[]> getTrueNumeric() {
		return trueNumeric;
	}

	public void setTrueNumeric(ArrayList<float[]> trueNumeric) {
		this.trueNumeric = trueNumeric;
	}

	public ArrayList<String[]> getTrueMatch() {
		return trueMatch;
	}

	public void setTrueMatch(ArrayList<String[]> trueMatch) {
		this.trueMatch = trueMatch;
	}

	public ArrayList<String[]> getSubMatch() {
		return subMatch;
	}

	public void setSubMatch(ArrayList<String[]> subMatch) {
		this.subMatch = subMatch;
	}

	public String getSubShortAns() {
		return subShortAns;
	}

	public void setSubShortAns(String subShortAns) {
		this.subShortAns = subShortAns;
	}
	
	
}
