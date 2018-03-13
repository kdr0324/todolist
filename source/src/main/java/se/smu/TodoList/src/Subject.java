import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {
	private String subjectName;		//�����
	private String professorName;	//�������̸�
	private String dayOfWeek;			//��������, * ������ �迭 1���� ����
	private int sHour, eHour; //���� ����,���ð�
	private int year;	//����
	private int semester;	//�б�
	ArrayList<WhatTodo> WTD = new ArrayList<WhatTodo>(); //���� TodoList ��ü ����
	private String rowData[] = new String[6];
	private String remark; // ���
	


	//������
	Subject(String sName, String pName, String day,
			int sH,int eH, int year, int semester, String remark){
		subjectName = sName;
		professorName = pName;
		dayOfWeek = day;
		sHour = sH; eHour=eH;
		this.year = year;
		this.semester = semester;
		//���̺� ���� �迭 ����
		setRowData(sName,pName, day, sH, eH, year, semester, remark);
		
	}
	
	//WhatTodo ��ü �����ؼ� WTD ���Ϳ� ����
	void setWhatTodo(String todoName, 
			int d_year, int d_month, int d_date, int d_hourOfDay, int d_minute){
		//WTD�� ������Ʈ ����
		//WTD.addElement(new WhatTodo(todoName, 
			//	d_year, d_month, d_date, d_hourOfDay, d_minute));	  
	}

	
	//�� �ʵ� get, set ����
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
