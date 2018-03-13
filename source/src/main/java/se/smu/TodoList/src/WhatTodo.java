import java.io.Serializable;
import java.util.Calendar;

public class WhatTodo implements Serializable { // 할일 명칭
	private String sName;
	private Calendar sCalendar, eCalendar, rCalendar;
	private boolean success, important; // 완성 여부
	private String content;
	private String rowData[] = new String[6];
	private Thread thread;

	WhatTodo() {
	}

	// 생성자 명칭과 마감기한만 받는다.
	WhatTodo(String sName, /* Calendar sCalendar, */ Calendar eCalendar, Calendar rCalendar, boolean important,
			boolean success, String content) {
		this.sName = sName;
		// this.sCalendar=sCalendar;
		this.eCalendar = eCalendar;
		this.rCalendar = rCalendar;
		this.content = content;
		this.important = important;
		this.success = success; // 생성시 완성 안함으로 표기

		setRowData(sName, /* sCalendar, */eCalendar, rCalendar, important, success, content);

		thread = new Timer(this);

	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public void setRowData(String[] rowData) {
		this.rowData = rowData;
	}

	public Calendar getsCalendar() {
		return sCalendar;
	}

	public void setsCalendar(Calendar sCalendar) {
		this.sCalendar = sCalendar;
	}

	public Calendar geteCalendar() {
		return eCalendar;
	}

	public void seteCalendar(Calendar eCalendar) {
		this.eCalendar = eCalendar;
	}

	public Calendar getrCalendar() {
		return rCalendar;
	}

	public void setrCalendar(Calendar rCalendar) {
		this.rCalendar = rCalendar;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getRowData() {
		return rowData;
	}

	public void setRowData(String sName, /* Calendar sCalendar, */ Calendar eCalendar, Calendar rCalendar,
			boolean important, boolean success, String content) {
		rowData[0] = content;
		rowData[1] = sName;
		rowData[2] = eCalendar.get(Calendar.YEAR) + "년";
		rowData[3] = rCalendar.get(Calendar.YEAR) + "년";
		if (eCalendar.get(Calendar.MONTH) + 1 < 10)
			rowData[2] += "0";
		else
			rowData[2] += "";

		if (rCalendar.get(Calendar.MONTH) + 1 < 10)
			rowData[3] += "0";
		else
			rowData[3] += "";

		// rowData[2]+=sCalendar.get(Calendar.MONTH)+1+"월";
		rowData[2] += eCalendar.get(Calendar.MONTH) + 1 + "월";
		rowData[3] += rCalendar.get(Calendar.MONTH) + 1 + "월";
		/*
		 * if(sCalendar.get(Calendar.DATE) < 10) rowData[2]+="0";
		 */
		if (eCalendar.get(Calendar.DATE) < 10)
			rowData[2] += "0";
		if (rCalendar.get(Calendar.DATE) < 10)
			rowData[3] += "0";
		// rowData[2]+=sCalendar.get(Calendar.DATE);
		rowData[2] += eCalendar.get(Calendar.DATE) + "일";
		rowData[3] += rCalendar.get(Calendar.DATE) + "일";
		if (eCalendar.get(Calendar.HOUR_OF_DAY) < 10)
			rowData[2] += "0";
		if (rCalendar.get(Calendar.HOUR_OF_DAY) < 10)
			rowData[3] += "0";
		rowData[2] += eCalendar.get(Calendar.HOUR_OF_DAY) + "시";
		rowData[3] += rCalendar.get(Calendar.HOUR_OF_DAY) + "시";
		if (eCalendar.get(Calendar.MINUTE) < 10)
			rowData[2] += "0";
		if (rCalendar.get(Calendar.MINUTE) < 10)
			rowData[3] += "0";
		rowData[2] += eCalendar.get(Calendar.MINUTE) + "분";
		rowData[3] += rCalendar.get(Calendar.MINUTE) + "분";
		if (important == true)
			rowData[4] = "★";
		else
			rowData[4] = "";
		if (success == true)
			rowData[5] = "O";
		else
			rowData[5] = "";
	}

	public void startThread() {
		thread.start();
	}

	public void killThread() {
		thread.interrupt();
	}

}
