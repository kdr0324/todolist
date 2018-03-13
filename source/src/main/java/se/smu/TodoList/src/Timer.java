
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class Timer extends Thread implements Serializable {

	private WhatTodo wtd;
	private Calendar todoDate;
	private Calendar date;

	public Timer(WhatTodo wtd) {
		this.wtd = wtd;
		todoDate = wtd.geteCalendar();
		// date = new Date();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		int a = 0;
		
		while (true) {
			date= Calendar.getInstance();
			if (date.get(Calendar.HOUR_OF_DAY) == todoDate.get(Calendar.HOUR_OF_DAY)
					&& date.get(Calendar.YEAR) == todoDate.get(Calendar.YEAR)
					&& date.get(Calendar.MONTH) == todoDate.get(Calendar.MONTH)
					&& date.get(Calendar.DATE) == todoDate.get(Calendar.DATE)
					&& date.get(Calendar.MINUTE) == todoDate.get(Calendar.MINUTE)) {
				JOptionPane.showMessageDialog(null, wtd.getContent() + " 의 기간이 만료되었습니다.");
				a = 1;
			} else if (date.after(todoDate)) a=1;
			
			if (a == 1 || wtd.getContent().isEmpty())
				break;

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
	}

}
