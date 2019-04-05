package server.servermodel;

/**
 * Provides data fields and methods to create a Java data-type, representing a Date
 * in the Tool Shop application.
 *
 * @author Wenjia Yang and Joel Wong
 * @version 1.1
 * @since April 5, 2019
 */
public class Date {
    /**
     * The month of the date
     */
    private String month;
    /**
     * The day of the date
     */
    private int day;
    /**
     * The year of the date
     */
    private int year;

    /**
     * Constructs a Date object with the specified values of the month, day, and year.
     *
     * @param month is the Date's month
     * @param day   is the Date's day
     * @param year  is the Date's year
     */
    public Date(String month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Converts the Date object to a String that contains the Date details in a
     * specific format.
     */
    public String toString() {
        return month + " " + day + ", " + year;
    }

    /**
     * Gets the Date's month.
     *
     * @return the month
     */
    String getMonth() {
        return month;
    }

    /**
     * Gets the Date's day.
     *
     * @return the day
     */
    int getDay() {
        return day;
    }

    /**
     * Gets the Date's year.
     *
     * @return the year
     */
    int getYear() {
        return year;
    }

    /**
     * Sets the Date's month to the specified month.
     *
     * @param month is the new month
     */
    void setMonth(String month) {
        this.month = month;
    }

    /**
     * Sets the Date's day to the specified day.
     *
     * @param day is the new day
     */
    void setDay(int day) {
        this.day = day;
    }

    /**
     * Sets the Date's year to the specified year.
     *
     * @param year is the new year
     */
    void setYear(int year) {
        this.year = year;
    }
}
