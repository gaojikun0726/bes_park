package com.core.common.ppcl;

public class Instruction {

	public int seq;
	
	public String nam;
	
	public String oprand1 = "";
	
	public String oprand2 = "";
	
	public String oprand3 = "";
	
	public String oprand4 = "";
	
	public String oprand5 = "";
      	

	public Instruction(int seq, String nam) {
		super();
		this.seq = seq;
		this.nam = nam;
	}

	public Instruction(int seq, String nam, String oprand1) {
		this(seq, nam);
		this.oprand1 = oprand1;
	}

	public Instruction(int seq, String nam, String oprand1, String oprand2) {
		this(seq, nam, oprand1);
		this.oprand2 = oprand2;
	}
	
	public Instruction(int seq, String nam, String oprand1, String oprand2, String oprand3) {
		this(seq, nam, oprand1, oprand2);
		this.oprand3 = oprand3;
	}
	
	
	public Instruction(int seq, String nam, String oprand1, String oprand2, String oprand3, String oprand4) {
		this(seq, nam, oprand1, oprand2, oprand3);
		this.oprand4 = oprand4;
	}

    public Instruction(int seq, String nam, String oprand1, String oprand2, String oprand3, String oprand4, String oprand5) {
		this(seq, nam, oprand1, oprand2, oprand3, oprand4);
		this.oprand5 = oprand5;
	}
    
	public String getNam() {
		return nam;
	}

	public void setNam(String nam) {
		this.nam = nam;
	}

	public String getOprand1() {
		return oprand1;
	}

	public void setOprand1(String oprand1) {
		this.oprand1 = oprand1;
	}

	public String getOprand2() {
		return oprand2;
	}

	public void setOprand2(String oprand2) {
		this.oprand2 = oprand2;
	}

	public String getOprand3() {
		return oprand3;
	}

	public void setOprand3(String oprand3) {
		this.oprand3 = oprand3;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getOprand4() {
		return oprand4;
	}

	public void setOprand4(String oprand4) {
		this.oprand4 = oprand4;
	}

	public String getOprand5() {
		return oprand5;
	}

	public void setOprand5(String oprand5) {
		this.oprand5 = oprand5;
	}	

	public String toString() {		
		return seq + " " + nam + " " + oprand1+ " " + oprand2+ " " + oprand3+ " " + oprand4+ " " + oprand5  ;
	}
}
