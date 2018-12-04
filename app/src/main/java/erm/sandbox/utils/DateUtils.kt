package erm.sandbox.utils

import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import java.util.concurrent.TimeUnit

class CalendarRange(
  var start: Calendar,
  var end: Calendar
) {
  fun isDayBetween(day: Calendar): Boolean {
    return (day.timeInMillis >= start.timeInMillis && day.timeInMillis <= end.timeInMillis)
  }

  fun toDateRange(): DateRange {
    return DateRange(
        DateUtils.calendarToDate(start), DateUtils.calendarToDate(end)
    )
  }

}

class DateRange(
  var start: Date,
  var end: Date
) {

  fun topCalendarRange(): CalendarRange {
    return CalendarRange(
        DateUtils.dateToCalendar(start), DateUtils.dateToCalendar(end)
    )
  }
}

/**
 * Date/Calendar Util
 */
object DateUtils {
  private val SECOND_MILLIS = TimeUnit.SECONDS.toMillis(1)
  private val MINUTE_MILLIS = TimeUnit.MINUTES.toMillis(1)
  private val HOUR_MILLIS = TimeUnit.HOURS.toMillis(1)
  private val DAY_MILLIS = TimeUnit.DAYS.toMillis(1)
  const val DATE_PARSE_FORMAT_YMD_HM = "yyyy-MM-dd HH:mm"
  const val DATE_PARSE_FORMAT_NO_TIME_DASHES = "yyyy-MM-dd"
  const val DATE_PARSE_TIME_ONLY = "h:mm a"
  const val DATE_PARSE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
  const val DATE_PARSE_FORMAT_TZ = "yyyy-MM-dd'T'HH:mm:ssZZ"
  const val DATE_PARSE_MILITARY_TIME_FORMAT = "MM/dd/yyyy HH:mm"
  const val DATE_PARSE_DISPLAY = "MMM dd, yyyy"
  const val DATE_PARSE_M_D_Y = "MM-dd-yyyy"
  const val DATE_PARSE_M_D_Y_SLASHES = "MM/dd/yyyy"
  const val DATE_PARSE_DAY_ONLY = "EEEE"
  const val DATE_PARSE_MONTH_ONLY = "MMMM"

  @JvmStatic
  fun getTrendsDefaultRange(): CalendarRange {
    val threeYearsAgoJan = Calendar.getInstance()
    threeYearsAgoJan.add(Calendar.YEAR, -3)
    threeYearsAgoJan.set(Calendar.MONTH, Calendar.JANUARY)
    threeYearsAgoJan.set(Calendar.DAY_OF_MONTH, 1)
    val today = Calendar.getInstance()
    today.add(Calendar.DAY_OF_YEAR, 1)
    return CalendarRange(threeYearsAgoJan, today)
  }

  @JvmStatic
  fun getNotesDefaultRange(): CalendarRange {
    val threeYearsAgoJan = Calendar.getInstance()
    threeYearsAgoJan.add(Calendar.YEAR, -3)
    threeYearsAgoJan.set(Calendar.MONTH, Calendar.JANUARY)
    threeYearsAgoJan.set(Calendar.DAY_OF_MONTH, 1)
    val today = Calendar.getInstance()
    today.add(Calendar.DAY_OF_YEAR, 6) //5+ 1 day (inclusive)
    return CalendarRange(threeYearsAgoJan, today)
  }

  @JvmStatic
  fun getTodayAt0(): Calendar {
    val today = Calendar.getInstance()
    today.set(Calendar.SECOND, 0)
    today.set(Calendar.HOUR_OF_DAY, 0)
    today.set(Calendar.MINUTE, 0)
    today.set(Calendar.MILLISECOND, 0)
    return today
  }

  @JvmStatic
  fun getYesterdayAt0(): Calendar {
    val yesterday = getTodayAt0()
    yesterday.add(Calendar.DAY_OF_YEAR, -1)
    return yesterday
  }

  @JvmStatic
  fun getCalendarRangeOfMonthThatContains(day: Calendar): CalendarRange {
    val beginningOfMonth = Calendar.getInstance()
    beginningOfMonth.set(Calendar.MONTH, day.get(Calendar.MONTH))
    beginningOfMonth.set(Calendar.DAY_OF_MONTH, 1)
    beginningOfMonth.set(Calendar.YEAR, day.get(Calendar.YEAR))

    val endOfMonth = Calendar.getInstance()
    endOfMonth.set(Calendar.MONTH, day.get(Calendar.MONTH))
    endOfMonth.set(Calendar.DAY_OF_MONTH, endOfMonth.getActualMaximum(Calendar.DAY_OF_MONTH))
    endOfMonth.set(Calendar.YEAR, day.get(Calendar.YEAR))
    return CalendarRange(beginningOfMonth, endOfMonth)
  }

  @JvmStatic
  fun getDateRangeOfMonthThatContainsDay(day: Date): DateRange {
    val calRange = getCalendarRangeOfMonthThatContains(dateToCalendar(day))
    return DateRange(calendarToDate(calRange.start), calendarToDate(calRange.end))
  }

  @JvmStatic
  fun getCalendarRangeOfDayForDay(day: Calendar): CalendarRange {
    val beginningOfDay: Calendar = day.clone() as Calendar
    beginningOfDay.set(Calendar.HOUR_OF_DAY, 0)
    beginningOfDay.set(Calendar.MINUTE, 0)
    beginningOfDay.set(Calendar.SECOND, 0)
    beginningOfDay.set(Calendar.MILLISECOND, 0)

    val endOfDay: Calendar = day.clone() as Calendar
    endOfDay.set(Calendar.HOUR_OF_DAY, 23)
    endOfDay.set(Calendar.MINUTE, 59)
    endOfDay.set(Calendar.SECOND, 59)
    endOfDay.set(Calendar.MILLISECOND, 59)
    return CalendarRange(beginningOfDay, endOfDay)
  }

  @JvmStatic
  fun getDateRangeOfDayForDay(day: Date): DateRange {
    return getCalendarRangeOfDayForDay(dateToCalendar(day)).toDateRange()
  }

  @JvmStatic
  fun now(): Date {
    return Date()
  }

  @JvmStatic
  fun nowStringForApi(): String {
    return formatDate(
        now(),
        DATE_PARSE_FORMAT,
        false
    )
  }

  @JvmStatic
  fun parseDate(
    dateToParse: String?,
    format: String? = DATE_PARSE_FORMAT,
    timeZone: TimeZone? = null
  ): Date? {
    return try {
      val parsedDateFormat = SimpleDateFormat(format)
      timeZone?.let {
        parsedDateFormat.timeZone = it
      }
      parsedDateFormat.parse(dateToParse)
    } catch (parseException: ParseException) {
      null
    }
  }

  @JvmStatic
  fun parseDateAndFormat(
    dateToParse: String,
    formatToReturn: String,
    trimEmptySeconds: Boolean
  ): String {
    try {
      val parsedDateFormat = SimpleDateFormat(DATE_PARSE_FORMAT)
      val date = parsedDateFormat.parse(dateToParse)
      return formatDate(date, formatToReturn, trimEmptySeconds)
    } catch (parseException: ParseException) {
      return dateToParse
    }

  }

  @JvmStatic
  fun formatDate(
    dateToFormat: Date,
    formatToReturn: String,
    trimEmptySeconds: Boolean
  ): String {
    val requestedFormat = SimpleDateFormat(formatToReturn)
    var returnDate = requestedFormat.format(dateToFormat)
    if (trimEmptySeconds) {
      returnDate = returnDate.replace(" 00:00", "")
    }
    return returnDate
  }

  @JvmStatic
  fun getTimeAgo(
    time: Long,
    context: Context //TODO use this for resources
  ): String? {

    val now = System.currentTimeMillis()
    //TODO: use context to get resource strings here
    val diff = now - time
    return when {
      diff < MINUTE_MILLIS -> "${diff / SECOND_MILLIS} seconds ago"
      diff < 2 * MINUTE_MILLIS -> "a minute ago"
      diff < 50 * MINUTE_MILLIS -> (diff / MINUTE_MILLIS).toString() + " minutes ago"
      diff < 90 * MINUTE_MILLIS -> "an hour ago"
      diff < 24 * HOUR_MILLIS -> (diff / HOUR_MILLIS).toString() + " hours ago"
      diff < 48 * HOUR_MILLIS -> "yesterday"
      else -> (diff / DAY_MILLIS).toString() + " days ago"
    }
  }

  //Convert Date to Calendar
  @JvmStatic
  fun dateToCalendar(date: Date): Calendar {

    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar
  }

  //Convert Calendar to Date
  @JvmStatic
  fun calendarToDate(calendar: Calendar): Date {
    return calendar.time
  }

  @JvmStatic
  fun isWeekend(calendar: Calendar): Boolean {
    return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(
        Calendar.DAY_OF_WEEK
    ) == Calendar.SUNDAY
  }

  @JvmStatic
  fun isWeekend(date: Date): Boolean {
    return isWeekend(dateToCalendar(date))
  }

  @JvmStatic
  fun isSameDay(
    calendar1: Calendar,
    calendar2: Calendar
  ): Boolean {
    return (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
        && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
        && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(
        Calendar.DAY_OF_MONTH
    ))
  }

  @JvmStatic
  fun getTimeFromMillis(timeInMillis: Long): String {
    var hours = timeInMillis / HOUR_MILLIS
    val minutes = (timeInMillis % HOUR_MILLIS) / MINUTE_MILLIS
    val amPm = if (hours >= 12) "PM" else "AM"
    if (hours == 0L) {
      hours = 12
    } else if (hours > 12) {
      hours -= 12
    }
    val minutesAsFormattedString = if (minutes.toString().count() > 1) "$minutes" else "0$minutes"
    return "$hours:$minutesAsFormattedString $amPm"
  }

}
