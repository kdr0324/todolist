import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {
	private String subjectName;		//과목명
	private String professorName;	//교수님이름
	private String dayOfWeek;			//수업요일, * 요일은 배열 1부터 시작
	private int sHour, eHour; //수업 시작,끝시간
	private int year;	//연도
	private int semester;	//학기
	ArrayList<WhatTodo> WTD = new ArrayList<WhatTodo>(); //과목별 TodoList 객체 생성
	private String rowData[] = new String[6];
	private String remark; // 비고
	


	//생성자
	Subject(String sName, String pName, String day,
			int sH,int eH, int year, int semester, String remark){
		subjectName = sName;
		professorName = pName;
		dayOfWeek = day;
		sHour = sH; eHour=eH;
		this.year = year;
		this.semester = semester;
		//테이블 넣을 배열 생성
		setRowData(sName,pName, day, sH, eH, year, semester, remark);
		
	}
	
	//WhatTodo 객체 생성해서 WTD 벡터에 삽입
	void setWhatTodo(String todoName, 
			int d_year, int d_month, int d_date, int d_hourOfDay, int d_minute){
		//WTD에 엘리멘트 삽입
		//WTD.addElement(new WhatTodo(todoName, 
			//	d_year, d_month, d_date, d_hourOfDay, d_minute));	  
	}

	
	//각 필드 get, set 접근
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getsHour() {
		return sHour;
	}

	public void setsHour(int sHour) {
		this.sHour = sHour;
	}

	

	public int geteHour() {
		return eHour;
	}

	public void seteHour(int eHour) {
		this.eHour = eHour;
	}


	public ArrayList<WhatTodo> getWTD() {
		return WTD;
	}

	public void setWTD(ArrayList<WhatTodo> wTD) {
		WTD = wTD;
	}
	
	public String[] getRowData(){
		return rowData;
	}
	
	public void setRowData(String sName, String pName, String day,
			int sH,int eH, int year, int semester, String remark){
		rowData[0]=sName;
		rowData[1]=pName;
		rowData[2]=day;
		rowData[3]=Integer.toString(sH)+"~"+Integer.toString(eH);
		rowData[4]=Integer.toString(year)+"/"+Integer.toString(semester);
		rowData[5]=remark;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
