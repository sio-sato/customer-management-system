package model.entity;

import java.io.Serializable;

public class CalendarBean implements Serializable {

	//元号表記
	private String gengou;
	//カレンダーの年
	private int year;
	//カレンダーの月
	private int month;
	//カレンダーの日付を保持する配列
	private String[][] data;

	private int todayRow = -1;
	private int todayCol = -1;

	public CalendarBean() {

	}

	public String getGengou() {
		return gengou;
	}

	public void setGengou(String gengou) {
		this.gengou = gengou;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public int getTodayRow() {
		return todayRow;
	}

	public void setTodayRow(int todayRow) {
		this.todayRow = todayRow;
	}

	public int getTodayCol() {
		return todayCol;
	}

	public void setTodayCol(int todayCol) {
		this.todayCol = todayCol;
	}

}
