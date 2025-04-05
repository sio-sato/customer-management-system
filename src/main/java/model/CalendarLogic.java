package model;

import java.util.Calendar;

import model.entity.CalendarBean;

public class CalendarLogic {
	//int...は可変長引数
	public CalendarBean createCalendar(int... args) {

		CalendarBean cb = new CalendarBean();
		//現在日時でカレンダーインスタンス生成
		Calendar cal = Calendar.getInstance();
		//2つの引数が来ていたら
		if (args.length == 2) {
			//最初の引数で年を設定
			cal.set(Calendar.YEAR, args[0]);
			//次の引数で月を設定
			cal.set(Calendar.MONTH, args[1] - 1);
		}
		//CalendarBeanに年を設定
		cb.setYear(cal.get(Calendar.YEAR));
		//CalendarBeanの元号の設定
		if (cb.getYear() > 2018) {
			cb.setGengou("令和" + (cb.getYear() - 2018));
		} else if (cb.getYear() > 1988) {
			cb.setGengou("平成" + (cb.getYear() - 1988));
		} else if (cb.getYear() > 1925) {
			cb.setGengou("昭和" + (cb.getYear() - 1925));
		} else if (cb.getYear() > 1911) {
			cb.setGengou("大正" + (cb.getYear() - 1911));
		} else {
			cb.setGengou("" + cb.getYear());
		}
		//マイカレンダーに月の設定
		cb.setMonth(cal.get(Calendar.MONTH) + 1);
		//その月の1日が何曜日かを調べる為に日付を1日にする
		cal.set(Calendar.DATE, 1);
		//カレンダーの最初の空白の数
		int before = cal.get(Calendar.DAY_OF_WEEK) - 1;
		//カレンダーの日付の数
		int daysCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//その月の最後の日が何曜日かを調べるために日付を最終日にする
		cal.set(Calendar.DATE, daysCount);
		//最後の日後の空白の数
		int after = 7 - cal.get(Calendar.DAY_OF_WEEK);
		//すべての要素数
		int total = before + daysCount + after;
		//その要素数を幅7個の配列に入れていった場合何行になるか
		int rows = total / 7;
		//その行数で2次元配列を生成
		String[][] data = new String[rows][7];
		//今見ているカレンダーが今月かどうかを調べるために、この瞬間の日付情報をもつもう一つのインスタンス作成しておく
		Calendar now = Calendar.getInstance();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < 7; j++) {
				if (i == 0 && j < before || i == rows - 1 && j >= (7 - after)) {
					//カレンダーの前後に入る空白の部分は空文字
					data[i][j] = "";
				} else {
					//カウンター変数と実際の日付の変換
					int date = i * 7 + j + 1 - before;
					//配列に日付を入れる
					data[i][j] = String.valueOf(date);
					//今作業しているカレンダーが今月のカレンダーだったら今日の日付の先頭に*を付与する
					if (now.get(Calendar.DATE) == date && now.get(Calendar.MONTH) == cb.getMonth() - 1
							&& now.get(Calendar.YEAR) == cb.getYear()) {
						cb.setTodayRow(i);
						cb.setTodayCol(j);
					}

				}
			}
		}
		//作成した2次元配列をマイカレンダーにセットする。
		cb.setData(data);
		return cb;
	}
}
