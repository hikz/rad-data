package com.munir.jxls.hip;

import java.util.ArrayList;
import java.util.List;

public class HipconverterFinalInput {

	List<HipconverterInput> hipconverterInputList = new ArrayList<HipconverterInput>();
	private int numberOfBits;
	
	public List<HipconverterInput> getHipconverterInputList() {
		return hipconverterInputList;
	}

	public void setHipconverterInputList(List<HipconverterInput> hipconverterInputList) {
		this.hipconverterInputList = hipconverterInputList;
	}

	public int getNumberOfBits() {
		return numberOfBits;
	}

	public void setNumberOfBits(int numberOfBits) {
		this.numberOfBits = numberOfBits;
	}	
}
