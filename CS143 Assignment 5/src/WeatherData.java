import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * A class representing weather data including temperatures, snowfall, and
 * precipitation over a period of time.
 * 
 * TODO: PLEASE PUT YOUR NAME HERE TO INDICATE THAT THIS IS YOUR WORK!
 * 
 * Veasna Bun CS143 11-19-2021
 * 
 * PLEASE DO NOT COPY THIS FILE TO OTHER STUDENTS OR TO WEBSITES LIKE CHEGG,
 * GITHUB, ETC WHERE OTHERS MAY VIEW IT!!! IT IS YOUR WORK AND YOU SHOULD BE
 * PROUD OF WHAT YOU HAVE ACCOMPLISHED! IN ADDITION, THIS FILE CONTAINS THE
 * COPYRIGHTED WORK OF MARTIN HOCK AND IS ONLY LICENSED FOR USE BY INDIVIDUAL
 * STUDENTS FOR NONPROFIT EDUCATIONAL PURPOSES.
 */
public class WeatherData {
	int count = 0;
	int mostFreq = 0;
	Set<Integer> wasHighTemp = new HashSet<>();
	Set<Integer> wasLowTemp = new HashSet<>();
	Map<String, Double> totalSnowfallPerYear = new HashMap<>();
	Map<String, Double> totalPrcPerMonth = new HashMap<>();
	Map<String, Double> averagePrcPerMonth = new HashMap<>();
	Map<Integer, Integer> monthJan = new HashMap<>();
	Map<Integer, Integer> monthFeb = new HashMap<>();
	Map<Integer, Integer> monthMar = new HashMap<>();
	Map<Integer, Integer> monthApr = new HashMap<>();
	Map<Integer, Integer> monthMay = new HashMap<>();
	Map<Integer, Integer> monthJun = new HashMap<>();
	Map<Integer, Integer> monthJul = new HashMap<>();
	Map<Integer, Integer> monthAug = new HashMap<>();
	Map<Integer, Integer> monthSep = new HashMap<>();
	Map<Integer, Integer> monthOct = new HashMap<>();
	Map<Integer, Integer> monthNov = new HashMap<>();
	Map<Integer, Integer> monthDec = new HashMap<>();
	Map<Integer, Integer> mostFrequentTemp = new HashMap<>();
	Map<Integer, Integer> highestRange = new HashMap<>();

	/**
	 * Load the data provided by the Scanner into your WeatherData class. The data
	 * should be loaded one line of text at a time using the Scanner's nextLine
	 * method. The first line of text should be discarded as it is a header. Each
	 * line of text, stored in a String, should be split using the .split(",")
	 * method. This will create an array of Strings. The relevant Strings are index
	 * 1, which indicates the date for the data on this line, index 2, which will
	 * indicate if the day contains multiple days of precipitation (or will be blank
	 * if it is just a single day of data), index 3, which indicates a multiple day
	 * precipitation total (or will be blank if it is a single day of
	 * precipitation), index 4, which indicates the single day precipitation total
	 * (or will be blank if the day contains multiple days of precipitation), index
	 * 7 which indicates the day's high temperature (and may be blank if this was
	 * not recorded), and index 8 which indicates the day's low temperature (and may
	 * be blank if this was not recorded). All other indexes may be ignored. All
	 * non-blank indexes in the array will contain a string surrounded by quotes -
	 * the substring command is the easiest way to get rid of them. Temperatures are
	 * always integers but precipitation and snowfall totals may contain decimal
	 * points. Dates are always of the form YYYY-MM-DD (a four digit year followed
	 * by a dash followed by a two digit month followed by a dash followed by a two
	 * digit day).
	 * 
	 * @param file Scanner connected to a weather data file
	 */
	public WeatherData(Scanner file) {
		file.nextLine();

		while (file.hasNextLine()) {
			String line = file.nextLine();
			String[] row = line.split(",");
			String[] date = row[1].substring(1, row[1].length() - 1).split("-");
			// String split date to year month day in a array
// ================================================================================================================
			if (row.length >= 9 && !row[7].equals("") && !row[8].equals("")) {
				// check if index contain temperature data
				int highTemp = Integer.parseInt(row[7].substring(1, row[7].length() - 1));
				// remove subString and store data as an integer
				int lowTemp = Integer.parseInt(row[8].substring(1, row[8].length() - 1));
				wasHighTemp.add(highTemp);
				// store the data in the file to the Set for low/high temperature
				wasLowTemp.add(lowTemp);
			}
// ================================================================================================================
			String year = date[0];
			if (row.length >= 6 && !row[5].equals("")) {
				// check if index contain snow fall data
				double snowfall = Double.parseDouble(row[5].substring(1, row[5].length() - 1));
				// remove subString and store data as a Double
				if (totalSnowfallPerYear.containsKey(year)) {
					// if the year is the same update the current snow fall data to the total.
					double prevSnowfall = totalSnowfallPerYear.get(year);
					double newSnowfallTotal = prevSnowfall + snowfall;
					totalSnowfallPerYear.put(year, newSnowfallTotal);
				} else {
					// this month does not yet exist in the map, add a new mapping
					totalSnowfallPerYear.put(year, snowfall);
				}
			}
// ================================================================================================================
			String month = date[1];// adding row 3 and 4 to get the total Precipitation
			if (row.length >= 5 && !row[4].equals("")) {
				double precipitationFour = Double.parseDouble(row[4].substring(1, row[4].length() - 1));
				if (totalPrcPerMonth.containsKey(month)) {
					double prePreciptiation = totalPrcPerMonth.get(month);
					double newPreciptitationTotal = prePreciptiation + precipitationFour;
					totalPrcPerMonth.put(month, newPreciptitationTotal);
				} else {
					totalPrcPerMonth.put(month, precipitationFour);
				}
			}
			if (row.length >= 4 && !row[3].equals("")) {
				double precipitationThree = Double.parseDouble(row[3].substring(1, row[3].length() - 1));
				if (totalPrcPerMonth.containsKey(month)) {
					double prePreciptiation = totalPrcPerMonth.get(month);
					double newPreciptitationTotal = prePreciptiation + precipitationThree;
					totalPrcPerMonth.put(month, newPreciptitationTotal);
				} else {
					totalPrcPerMonth.put(month, precipitationThree);
				}
			}
// ================================================================================================================
			// store the key highTemp in the month in a Map and keep track of its count
			if (row.length >= 8 && !row[7].equals("")) {
				int highTemp = Integer.parseInt(row[7].substring(1, row[7].length() - 1));
				if (monthJan.containsKey(highTemp) && month.equals("01")) {
					count = monthJan.get(highTemp) + 1;
					monthJan.put(highTemp, count);
				}
				if (!monthJan.containsKey(highTemp) && month.equals("01")) {
					monthJan.put(highTemp, count + 1);
					count = 0;
				}
				if (monthFeb.containsKey(highTemp) && month.equals("02")) {
					count = monthFeb.get(highTemp) + 1;
					monthFeb.put(highTemp, count);
				}
				if (!monthFeb.containsKey(highTemp) && month.equals("02")) {
					monthFeb.put(highTemp, count + 1);
					count = 0;
				}
				if (monthMar.containsKey(highTemp) && month.equals("03")) {
					count = monthMar.get(highTemp) + 1;
					monthMar.put(highTemp, count);
				}
				if (!monthMar.containsKey(highTemp) && month.equals("03")) {
					monthMar.put(highTemp, count + 1);
					count = 0;
				}
				if (monthApr.containsKey(highTemp) && month.equals("04")) {
					count = monthApr.get(highTemp) + 1;
					monthApr.put(highTemp, count);
				}
				if (!monthApr.containsKey(highTemp) && month.equals("04")) {
					monthApr.put(highTemp, count + 1);
					count = 0;
				}
				if (monthMay.containsKey(highTemp) && month.equals("05")) {
					count = monthMay.get(highTemp) + 1;
					monthMay.put(highTemp, count);
				}
				if (!monthMay.containsKey(highTemp) && month.equals("05")) {
					monthMay.put(highTemp, count + 1);
					count = 0;
				}
				if (monthJun.containsKey(highTemp) && month.equals("06")) {
					count = monthJun.get(highTemp) + 1;
					monthJun.put(highTemp, count);
				}
				if (!monthJun.containsKey(highTemp) && month.equals("06")) {
					monthJun.put(highTemp, count + 1);
					count = 0;
				}
				if (monthJul.containsKey(highTemp) && month.equals("07")) {
					count = monthJul.get(highTemp) + 1;
					monthJul.put(highTemp, count);
				}
				if (!monthJul.containsKey(highTemp) && month.equals("07")) {
					monthJul.put(highTemp, count + 1);
					count = 0;
				}
				if (monthAug.containsKey(highTemp) && month.equals("08")) {
					count = monthAug.get(highTemp) + 1;
					monthAug.put(highTemp, count);
				}
				if (!monthAug.containsKey(highTemp) && month.equals("08")) {
					monthAug.put(highTemp, count + 1);
					count = 0;
				}
				if (monthSep.containsKey(highTemp) && month.equals("09")) {
					count = monthSep.get(highTemp) + 1;
					monthSep.put(highTemp, count);
				}
				if (!monthSep.containsKey(highTemp) && month.equals("09")) {
					monthSep.put(highTemp, count + 1);
					count = 0;
				}
				if (monthOct.containsKey(highTemp) && month.equals("10")) {
					count = monthOct.get(highTemp) + 1;
					monthOct.put(highTemp, count);
				}
				if (!monthOct.containsKey(highTemp) && month.equals("10")) {
					monthOct.put(highTemp, count + 1);
					count = 0;
				}
				if (monthNov.containsKey(highTemp) && month.equals("11")) {
					count = monthNov.get(highTemp) + 1;
					monthNov.put(highTemp, count);
				}
				if (!monthNov.containsKey(highTemp) && month.equals("11")) {
					monthNov.put(highTemp, count + 1);
					count = 0;
				}
				if (monthDec.containsKey(highTemp) && month.equals("12")) {
					count = monthDec.get(highTemp) + 1;
					monthDec.put(highTemp, count);
				}
				if (!monthDec.containsKey(highTemp) && month.equals("12")) {
					monthDec.put(highTemp, count + 1);
					count = 0;
				}
			}
// ================================================================================================================
			if (row.length >= 9 && !row[7].equals("") && !row[8].equals("")) {
				int highTemp = Integer.parseInt(row[7].substring(1, row[7].length() - 1));
				int lowTemp = Integer.parseInt(row[8].substring(1, row[8].length() - 1));
				int range = highTemp - lowTemp;
				if (highestRange.containsKey(lowTemp) && highestRange.get(lowTemp) - lowTemp < range) {
					highestRange.put(lowTemp, highTemp);
				}
				if (!highestRange.containsKey(lowTemp)) {
					highestRange.put(lowTemp, highTemp);
				}
			}
		}
// ================================================================================================================	
		// literate through the month and divide it by 100 and place it in the
		// averagePrcPerMonth month
		for (String month : totalPrcPerMonth.keySet()) {
			double averagePrecipitation = totalPrcPerMonth.get(month) / 100.0;
			averagePrcPerMonth.put(month, averagePrecipitation);
		}
		// literate through each highTemp for that month and store the most frequentTemp
		// if the frequents for highTemp is the same; store the lowest highTemp
		mostFreq = 0;
		for (Integer i : monthJan.keySet()) {
			if (mostFreq < monthJan.get(i)) {
				mostFreq = monthJan.get(i);
				mostFrequentTemp.put(1, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthFeb.keySet()) {
			if (mostFreq < monthFeb.get(i)) {
				mostFreq = monthFeb.get(i);
				mostFrequentTemp.put(2, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthMar.keySet()) {
			if (mostFreq < monthMar.get(i)) {
				mostFreq = monthMar.get(i);
				mostFrequentTemp.put(3, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthApr.keySet()) {
			if (mostFreq < monthApr.get(i)) {
				mostFreq = monthApr.get(i);
				mostFrequentTemp.put(4, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthMay.keySet()) {
			if (mostFreq < monthMay.get(i)) {
				mostFreq = monthMay.get(i);
				mostFrequentTemp.put(5, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthJun.keySet()) {
			if (mostFreq < monthJun.get(i)) {
				mostFreq = monthJun.get(i);
				mostFrequentTemp.put(6, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthJul.keySet()) {
			if (mostFreq < monthJul.get(i)) {
				mostFreq = monthJul.get(i);
				mostFrequentTemp.put(7, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthAug.keySet()) {
			if (mostFreq < monthAug.get(i)) {
				mostFreq = monthAug.get(i);
				mostFrequentTemp.put(8, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthSep.keySet()) {
			if (mostFreq < monthSep.get(i)) {
				mostFreq = monthSep.get(i);
				mostFrequentTemp.put(9, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthOct.keySet()) {
			if (mostFreq < monthOct.get(i)) {
				mostFreq = monthOct.get(i);
				mostFrequentTemp.put(10, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthNov.keySet()) {
			if (mostFreq < monthNov.get(i)) {
				mostFreq = monthNov.get(i);
				mostFrequentTemp.put(11, i);
			}
		}
		mostFreq = 0;
		for (Integer i : monthDec.keySet()) {
			if (mostFreq < monthDec.get(i)) {
				mostFreq = monthDec.get(i);
				mostFrequentTemp.put(12, i);
			}
		}
// ================================================================================================================

	}

	/**
	 * Determine whether the given temperature was ever seen as a high temperature
	 * in the data provided to the constructor. (10 points)
	 * 
	 * (HINT: This is a membership question. What data structure have we seen that
	 * can help us answer this question?)
	 * 
	 * @param degrees Temperature (same units as data file)
	 * @return true if high temp, false otherwise
	 */
	public boolean highTemp(int degrees) {
		// check to see if these degree are present in the set for HighTemp
		return wasHighTemp.contains(degrees);
	}

	/**
	 * Determine whether the given temperature was ever seen as a low temperature in
	 * the data provided to the constructor. (10 points)
	 * 
	 * @param degrees Temperature (same units as data file)
	 * @return true if low temp, false otherwise
	 */
	public boolean lowTemp(int degrees) {
		// check to see if these degree are present in the set for LowTemp
		return wasLowTemp.contains(degrees);
	}

	/**
	 * Determine the total amount of snowfall recorded in the given year. (20
	 * points)
	 * 
	 * (HINT: What data structure would allow us to correspond an amount of snowfall
	 * with a year? How much snowfall is recorded in a year not found in the file?)
	 * 
	 * @param year
	 * @return
	 */
	public double totalSnowfallForYear(int year) {
		// check the year and output total snow fall
		if (totalSnowfallPerYear.get(String.valueOf(year)) == null) {
			return 0.0;
		}
		return totalSnowfallPerYear.get(String.valueOf(year));
	}

	/**
	 * Determine the average (mean) total precipitation recorded for the given
	 * month. Be sure to include multi-day precipitation amounts. (Assume that all
	 * of the precipitation occurs on the date of the multi-date range - never
	 * divide it across months.) (20 points)
	 * 
	 * @param month
	 * @return
	 */
	public double averagePrecipitationForMonth(int month) {
		// check the month for the average Precipitation
		if (month <= 9 && month >= 1) {
			String input = "0" + month;
			return averagePrcPerMonth.get((String.valueOf(input)));
		}
		if (month <= 12 && month >= 10) {
			return averagePrcPerMonth.get((String.valueOf(month)));
		}
		return 0.0;
	}

	/**
	 * Return the most common (most often observed) high temperature seen in the
	 * given month. If there are two or more temperatures that are both seen the
	 * most number of times, return the lowest high temperature. (20 points)
	 * 
	 * @param month Month
	 * @return highest most common high temperature seen in that month
	 */
	public int lowestMostCommonHighForMonth(int month) {
		return mostFrequentTemp.get(month);
	}

	/**
	 * For the given low temperature, find the highest high temperature seen with
	 * that low. (20 points)
	 * 
	 * @param degrees Low temperature
	 * @return Highest high ever seen for that low temperature
	 */
	public int highestHighForLow(int degrees) {
		return highestRange.get(degrees); 
	}

}
