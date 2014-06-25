package com.aakash.vlabs.quiz;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class MyAns implements Parcelable{

	// Data of a Single Question
	// This may be updated at runtime
	
	private String Qn,QnType;
	private int QnNo;
	private float scoredWeight;
	private int isAnswred = 0;
	
	private String trueString = "";
	private String subString = "";
	private String cssCls = "";
	
	// Case : True / False
	private String trueAns,subAns;
	
	// Case : Multiple Choice
	private String truemulOptAns,submulOptAns;
	
	// Case : Multiple Many Choice & Short Answer
	private ArrayList<String> truemulManyAns,submulManyAns,mulFeedback;
	private float[] trueMulManyWeight;
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

	public int getIsAnswred() {
		return isAnswred;
	}

	public void setIsAnswred(int isAnswred) {
		this.isAnswred = isAnswred;
	}
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
		dest.writeString(this.Qn);
		dest.writeString(this.QnType);
		dest.writeInt(this.QnNo);
		dest.writeFloat(this.scoredWeight);
		dest.writeInt(this.isAnswred);
		dest.writeString(this.trueAns);
		dest.writeString(this.subAns);
		dest.writeString(this.truemulOptAns);
		dest.writeString(this.submulOptAns);
		dest.writeList(this.truemulManyAns);
		dest.writeList(this.submulManyAns);
		dest.writeList(this.mulFeedback);
		dest.writeString(this.subShortAns);
		dest.writeList(this.trueNumeric);
		dest.writeFloat(this.subNumeric);
		dest.writeList(this.trueMatch);
		dest.writeList(this.subMatch);
		dest.writeString(this.feedback);
		dest.writeFloatArray(this.trueMulManyWeight);
	}
	
	public static final Parcelable.Creator<MyAns> CREATOR = new Creator<MyAns>() {
		
		@Override
		public MyAns[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MyAns[size];
		}
		
		@Override
		public MyAns createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new MyAns(source);
		}
	};

	@SuppressWarnings("unchecked")
	private MyAns(Parcel source){
		this.Qn = source.readString();
		this.QnType = source.readString();
		this.QnNo = source.readInt();
		this.scoredWeight = source.readFloat();
		this.isAnswred = source.readInt();
		this.trueAns = source.readString();
		this.subAns = source.readString();
		this.truemulOptAns = source.readString();
		this.submulOptAns = source.readString();
		this.truemulManyAns = source.readArrayList(null);
		this.submulManyAns = source.readArrayList(null);
		this.mulFeedback = source.readArrayList(null);
		this.subShortAns = source.readString();
		this.trueNumeric = source.readArrayList(null);
		this.subNumeric = source.readFloat();
		this.trueMatch = source.readArrayList(null);
		this.subMatch = source.readArrayList(null);
		this.feedback = source.readString();
		this.trueMulManyWeight = source.createFloatArray();
	}

	public float[] getTrueMulManyWeight() {
		return trueMulManyWeight;
	}

	public void setTrueMulManyWeight(float[] trueMulManyWeight) {
		this.trueMulManyWeight = trueMulManyWeight;
	}

	public String getTrueString() {
		return trueString;
	}

	public void setTrueString(String trueString) {
		this.trueString = trueString;
	}

	public String getSubString() {
		return subString;
	}

	public void setSubString(String subString) {
		this.subString = subString;
	}

	public String getCssCls() {
		return cssCls;
	}

	public void setCssCls(String cssCls) {
		this.cssCls = cssCls;
	}
	
}
