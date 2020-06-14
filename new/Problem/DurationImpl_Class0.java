public class Class0 {
	private Object signum;
	private Object INDETERMINATE;
	private Object ROUND_DOWN;
	private Object MONTH;
	private Object DAY_OF_MONTH;
	private Object HOUR;
	private Object SECONDS;
	private Object FIELD_UNDEFINED;
	private Object DAYS;
	private Object YEARS;
	private Object MONTHS;
	private Object HOURS;
	private Object YEAR;
	private Object MINUTE;
	private Object SECOND;
	private Object MINUTES;
	private Object MILLISECOND;

	public Class0(Object signum, Object INDETERMINATE, Object ROUND_DOWN, Object MONTH, Object DAY_OF_MONTH, Object HOUR, Object SECONDS, Object FIELD_UNDEFINED, Object DAYS, Object YEARS, Object MONTHS, Object HOURS, Object YEAR, Object MINUTE, Object SECOND, Object MINUTES, Object MILLISECOND){
		this.signum = signum;
		this.INDETERMINATE = INDETERMINATE;
		this.ROUND_DOWN = ROUND_DOWN;
		this.MONTH = MONTH;
		this.DAY_OF_MONTH = DAY_OF_MONTH;
		this.HOUR = HOUR;
		this.SECONDS = SECONDS;
		this.FIELD_UNDEFINED = FIELD_UNDEFINED;
		this.DAYS = DAYS;
		this.YEARS = YEARS;
		this.MONTHS = MONTHS;
		this.HOURS = HOURS;
		this.YEAR = YEAR;
		this.MINUTE = MINUTE;
		this.SECOND = SECOND;
		this.MINUTES = MINUTES;
		this.MILLISECOND = MILLISECOND;
	}
	/**
     * TODO: Javadoc
     * 
     * @param ch char to test.
     * 
     * @return true if ch is a digit, else false.
     */
private static boolean isDigit(char ch) {
    return '0' <= ch && ch <= '9';
}
	/**
     * TODO: Javadoc
     * 
     * @param ch to test.
     * 
     * @return true if ch is a digit or a period, else false.
     */
private static boolean isDigitOrPeriod(char ch) {
    return isDigit(ch) || ch == '.';
}
	/**
	 * Returns the sign of this duration in -1,0, or 1.
	 * 
	 * @return
	 *      -1 if this duration is negative, 0 if the duration is zero,
	 *      and 1 if the duration is postive.
	 */
public int getSign() {
    return signum;
}
	/**
	 * TODO: Javadoc
	 * @param isPositive Sign.
	 * 
	 * @return 1 if positive, else -1.
	 */
private int calcSignum(boolean isPositive) {
    if ((years == null || years.signum() == 0) && (months == null || months.signum() == 0) && (days == null || days.signum() == 0) && (hours == null || hours.signum() == 0) && (minutes == null || minutes.signum() == 0) && (seconds == null || seconds.signum() == 0)) {
        return 0;
    }
    if (isPositive) {
        return 1;
    } else {
        return -1;
    }
}
	/**
	 * TODO: Javadoc
	 * 
	 * @param i int to convert to BigInteger.
	 * 
	 * @return BigInteger representation of int.
	 */
private static BigInteger wrap(final int i) {
    // field may not be set
    if (i == DatatypeConstants.FIELD_UNDEFINED) {
        return null;
    }
    // int -> BigInteger
    return new BigInteger(String.valueOf(i));
}
	/**
     * TODO: Javadoc
     * 
     * @param whole String to parse.
     * @param idx TODO: ???
     * 
     * @return Result of parsing.
     * 
     * @throws IllegalArgumentException If whole cannot be parsed.
     */
private static String parsePiece(String whole, int[] idx) throws IllegalArgumentException {
    int start = idx[0];
    while (idx[0] < whole.length() && isDigitOrPeriod(whole.charAt(idx[0]))) {
        idx[0]++;
    }
    if (idx[0] == whole.length()) {
        // ,idx[0]);
        throw new IllegalArgumentException(whole);
    }
    idx[0]++;
    return whole.substring(start, idx[0]);
}
	/**
     * TODO: Javadoc.
     * 
     * @param whole TODO: ???
     * @param parts TODO: ???
     * @param partsIndex TODO: ???
     * @param len TODO: ???
     * @param tokens TODO: ???
     * 
     * @throws IllegalArgumentException TODO: ???
     */
private static void organizeParts(String whole, String[] parts, int[] partsIndex, int len, String tokens) throws IllegalArgumentException {
    int idx = tokens.length();
    for (int i = len - 1; i >= 0; i--) {
        int nidx = tokens.lastIndexOf(parts[i].charAt(parts[i].length() - 1), idx - 1);
        if (nidx == -1) {
            throw new IllegalArgumentException(whole);
        // ,partsIndex[i]+parts[i].length()-1);
        }
        for (int j = nidx + 1; j < idx; j++) {
            parts[j] = null;
        }
        idx = nidx;
        parts[idx] = parts[i];
        partsIndex[idx] = partsIndex[i];
    }
    for (idx--; idx >= 0; idx--) {
        parts[idx] = null;
    }
}
	/**
     * TODO: Javadoc
     * 
     * @param whole TODO: ???.
     * @param part TODO: ???.
     * @param index TODO: ???.
     * 
     * @return TODO: ???.
     * 
     * @throws IllegalArgumentException TODO: ???.
     */
private static BigInteger parseBigInteger(String whole, String part, int index) throws IllegalArgumentException {
    if (part == null) {
        return null;
    }
    part = part.substring(0, part.length() - 1);
    //        try {
    return new BigInteger(part);
//        } catch( NumberFormatException e ) {
//            throw new ParseException( whole, index );
//        }
}
	/**
     * TODO: Javadoc.
     * 
     * @param whole TODO: ???.
     * @param part TODO: ???.
     * @param index TODO: ???.
     * 
     * @return TODO: ???.
     * 
     * @throws IllegalArgumentException TODO: ???.
     */
private static BigDecimal parseBigDecimal(String whole, String part, int index) throws IllegalArgumentException {
    if (part == null) {
        return null;
    }
    part = part.substring(0, part.length() - 1);
    //        try {
    return new BigDecimal(part);
//        } catch( NumberFormatException e ) {
//            throw new ParseException( whole, index );
//        }
}
	/**
     * Returns a hash code consistent with the definition of the equals method.
     * 
     * @see Object#hashCode() 
     */
public int hashCode() {
    // component wise hash is not correct because 1day = 24hours
    Calendar cal = TEST_POINTS[0].toGregorianCalendar();
    this.addTo(cal);
    return (int) getCalendarTimeInMillis(cal);
}
	/**
     * Obtains the value of the YEARS field as an integer value,
     * or 0 if not present.
     * 
     * <p>
     * This method is a convenience method around the 
     * {@link #getField(DatatypeConstants.Field)} method.
     * 
     * <p>
     * Note that since this method returns <tt>int</tt>, this
     * method will return an incorrect value for {@link Duration}s
     * with the year field that goes beyond the range of <tt>int</tt>.
     * Use <code>getField(YEARS)</code> to avoid possible loss of precision.</p>
     * 
     * @return
     *      If the YEARS field is present, return
     *      its value as an integer by using the {@link Number#intValue()}
     *      method. If the YEARS field is not present, return 0.
     */
public int getYears() {
    return getInt(DatatypeConstants.YEARS);
}
	/**
     * Obtains the value of the MONTHS field as an integer value,
     * or 0 if not present.
     * 
     * This method works just like {@link #getYears()} except
     * that this method works on the MONTHS field.
     * 
     * @return Months of this <code>Duration</code>.
     */
public int getMonths() {
    return getInt(DatatypeConstants.MONTHS);
}
	/**
     * Obtains the value of the DAYS field as an integer value,
     * or 0 if not present.
     * 
     * This method works just like {@link #getYears()} except
     * that this method works on the DAYS field.
     * 
     * @return Days of this <code>Duration</code>.
     */
public int getDays() {
    return getInt(DatatypeConstants.DAYS);
}
	/**
     * Obtains the value of the HOURS field as an integer value,
     * or 0 if not present.
     * 
     * This method works just like {@link #getYears()} except
     * that this method works on the HOURS field.
     * 
     * @return Hours of this <code>Duration</code>.
     * 
     */
public int getHours() {
    return getInt(DatatypeConstants.HOURS);
}
	/**
     * Obtains the value of the MINUTES field as an integer value,
     * or 0 if not present.
     * 
     * This method works just like {@link #getYears()} except
     * that this method works on the MINUTES field.
     * 
     * @return Minutes of this <code>Duration</code>.
     * 
     */
public int getMinutes() {
    return getInt(DatatypeConstants.MINUTES);
}
	/**
     * Obtains the value of the SECONDS field as an integer value,
     * or 0 if not present.
     * 
     * This method works just like {@link #getYears()} except
     * that this method works on the SECONDS field.
     * 
     * @return seconds in the integer value. The fraction of seconds
     *   will be discarded (for example, if the actual value is 2.5,
     *   this method returns 2)
     */
public int getSeconds() {
    return getInt(DatatypeConstants.SECONDS);
}
	/**
     * <p>Returns the length of the duration in milli-seconds.</p>
     * 
     * <p>If the seconds field carries more digits than milli-second order,
     * those will be simply discarded (or in other words, rounded to zero.)  
     * For example, for any Calendar value <code>x<code>,</p>
     * <pre>
     * <code>new Duration("PT10.00099S").getTimeInMills(x) == 10000</code>.
     * <code>new Duration("-PT10.00099S").getTimeInMills(x) == -10000</code>.
     * </pre>
     * 
     * <p>
     * Note that this method uses the {@link #addTo(Calendar)} method,
     * which may work incorectly with {@link Duration} objects with
     * very large values in its fields. See the {@link #addTo(Calendar)}
     * method for details.
     * 
     * @param startInstant
     *      The length of a month/year varies. The <code>startInstant</code> is
     *      used to disambiguate this variance. Specifically, this method
     *      returns the difference between <code>startInstant</code> and
     *      <code>startInstant+duration</code>
     * 
     * @return milliseconds between <code>startInstant</code> and
     *   <code>startInstant</code> plus this <code>Duration</code>
     *
     * @throws NullPointerException if <code>startInstant</code> parameter 
     * is null.
     * 
     */
public long getTimeInMillis(final Calendar startInstant) {
    Calendar cal = (Calendar) startInstant.clone();
    addTo(cal);
    return getCalendarTimeInMillis(cal) - getCalendarTimeInMillis(startInstant);
}
	/**
     * <p>Returns the length of the duration in milli-seconds.</p>
     * 
     * <p>If the seconds field carries more digits than milli-second order,
     * those will be simply discarded (or in other words, rounded to zero.)
     * For example, for any <code>Date</code> value <code>x<code>,</p>   
     * <pre>
     * <code>new Duration("PT10.00099S").getTimeInMills(x) == 10000</code>.
     * <code>new Duration("-PT10.00099S").getTimeInMills(x) == -10000</code>.
     * </pre>
     * 
     * <p>
     * Note that this method uses the {@link #addTo(Date)} method,
     * which may work incorectly with {@link Duration} objects with
     * very large values in its fields. See the {@link #addTo(Date)}
     * method for details.
     * 
     * @param startInstant
     *      The length of a month/year varies. The <code>startInstant</code> is
     *      used to disambiguate this variance. Specifically, this method
     *      returns the difference between <code>startInstant</code> and
     *      <code>startInstant+duration</code>.
     * 
     * @throws NullPointerException
     *      If the startInstant parameter is null.
     * 
     * @return milliseconds between <code>startInstant</code> and
     *   <code>startInstant</code> plus this <code>Duration</code>
     *
     * @see #getTimeInMillis(Calendar)
     */
public long getTimeInMillis(final Date startInstant) {
    Calendar cal = new GregorianCalendar();
    cal.setTime(startInstant);
    this.addTo(cal);
    return getCalendarTimeInMillis(cal) - startInstant.getTime();
}
	/**
     * <p>Computes a new duration whose value is <code>factor</code> times
     * longer than the value of this duration.</p>
     * 
     * <p>This method is provided for the convenience.
     * It is functionally equivalent to the following code:</p>
     * <pre>
     * multiply(new BigDecimal(String.valueOf(factor)))
     * </pre>
     * 
     * @param factor Factor times longer of new <code>Duration</code> to create.
     * 
     * @return New <code>Duration</code> that is <code>factor</code>times longer than this <code>Duration</code>.
     * 
     * @see #multiply(BigDecimal)
     */
public Duration multiply(int factor) {
    return multiply(BigDecimal.valueOf(factor));
}
	/**
     * Computes a new duration whose value is <code>factor</code> times
     * longer than the value of this duration.
     * 
     * <p>
     * For example,
     * <pre>
     * "P1M" (1 month) * "12" = "P12M" (12 months)
     * "PT1M" (1 min) * "0.3" = "PT18S" (18 seconds)
     * "P1M" (1 month) * "1.5" = IllegalStateException
     * </pre>
     *  
     * <p>
     * Since the {@link Duration} class is immutable, this method
     * doesn't change the value of this object. It simply computes
     * a new Duration object and returns it.
     * 
     * <p>
     * The operation will be performed field by field with the precision
     * of {@link BigDecimal}. Since all the fields except seconds are
     * restricted to hold integers,
     * any fraction produced by the computation will be
     * carried down toward the next lower unit. For example,
     * if you multiply "P1D" (1 day) with "0.5", then it will be 0.5 day,
     * which will be carried down to "PT12H" (12 hours).
     * When fractions of month cannot be meaningfully carried down
     * to days, or year to months, this will cause an
     * {@link IllegalStateException} to be thrown. 
     * For example if you multiple one month by 0.5.</p>
     * 
     * <p>
     * To avoid {@link IllegalStateException}, use
     * the {@link #normalizeWith(Calendar)} method to remove the years
     * and months fields.
     * 
     * @param factor to multiply by
     * 
     * @return
     *      returns a non-null valid {@link Duration} object
     *
     * @throws IllegalStateException if operation produces fraction in 
     * the months field.
     *
     * @throws NullPointerException if the <code>factor</code> parameter is 
     * <code>null</code>.
     *
     */
public Duration multiply(BigDecimal factor) {
    BigDecimal carry = ZERO;
    int factorSign = factor.signum();
    factor = factor.abs();
    BigDecimal[] buf = new BigDecimal[6];
    for (int i = 0; i < 5; i++) {
        BigDecimal bd = getFieldAsBigDecimal(FIELDS[i]);
        bd = bd.multiply(factor).add(carry);
        buf[i] = bd.setScale(0, BigDecimal.ROUND_DOWN);
        bd = bd.subtract(buf[i]);
        if (i == 1) {
            if (bd.signum() != 0) {
                // illegal carry-down
                throw new IllegalStateException();
            } else {
                carry = ZERO;
            }
        } else {
            carry = bd.multiply(FACTORS[i]);
        }
    }
    if (seconds != null) {
        buf[5] = seconds.multiply(factor).add(carry);
    } else {
        buf[5] = carry;
    }
    return new DurationImpl(this.signum * factorSign >= 0, toBigInteger(buf[0], null == years), toBigInteger(buf[1], null == months), toBigInteger(buf[2], null == days), toBigInteger(buf[3], null == hours), toBigInteger(buf[4], null == minutes), (buf[5].signum() == 0 && seconds == null) ? null : buf[5]);
}
	/**
     * <p>BigInteger value of BigDecimal value.</p>
     * 
     * @param value Value to convert.
     * @param canBeNull Can returned value be null?
     * 
     * @return BigInteger value of BigDecimal, possibly null.
     */
private static BigInteger toBigInteger(BigDecimal value, boolean canBeNull) {
    if (canBeNull && value.signum() == 0) {
        return null;
    } else {
        return value.unscaledValue();
    }
}
	/**
     * Compute <code>value*signum</code> where value==null is treated as
     * value==0.
     * @param value Value to sanitize.
     * @param signum 0 to sanitize to 0, > 0 to sanitize to <code>value</code>, < 0 to sanitize to negative <code>value</code>.
     *
     * @return non-null {@link BigDecimal}.
     */
private static BigDecimal sanitize(BigInteger value, int signum) {
    if (signum == 0 || value == null) {
        return ZERO;
    }
    if (signum > 0) {
        return new BigDecimal(value);
    }
    return new BigDecimal(value.negate());
}
	/**
     * <p>Compute <code>value*signum</code> where <code>value==null</code> is treated as <code>value==0</code></p>.
     * 
     * @param value Value to sanitize.
     * @param signum 0 to sanitize to 0, > 0 to sanitize to <code>value</code>, < 0 to sanitize to negative <code>value</code>.
     * 
     * @return non-null {@link BigDecimal}.
     */
static BigDecimal sanitize(BigDecimal value, int signum) {
    if (signum == 0 || value == null) {
        return ZERO;
    }
    if (signum > 0) {
        return value;
    }
    return value.negate();
}
	/**
     * Returns a new {@link Duration} object whose
     * value is <code>-this</code>.
     * 
     * <p>
     * Since the {@link Duration} class is immutable, this method
     * doesn't change the value of this object. It simply computes
     * a new Duration object and returns it.
     * 
     * @return
     *      always return a non-null valid {@link Duration} object.
     */
public Duration negate() {
    return new DurationImpl(signum <= 0, years, months, days, hours, minutes, seconds);
}
	/**
     * Returns the sign of this duration in -1,0, or 1.
     * 
     * @return
     *      -1 if this duration is negative, 0 if the duration is zero,
     *      and 1 if the duration is postive.
     */
public int signum() {
    return signum;
}
	/**
     * Adds this duration to a {@link Calendar} object.
     * 
     * <p>
     * Calls {@link java.util.Calendar#add(int,int)} in the
     * order of YEARS, MONTHS, DAYS, HOURS, MINUTES, SECONDS, and MILLISECONDS
     * if those fields are present. Because the {@link Calendar} class
     * uses int to hold values, there are cases where this method
     * won't work correctly (for example if values of fields
     * exceed the range of int.) 
     * </p>
     * 
     * <p>
     * Also, since this duration class is a Gregorian duration, this
     * method will not work correctly if the given {@link Calendar}
     * object is based on some other calendar systems. 
     * </p>
     * 
     * <p>
     * Any fractional parts of this {@link Duration} object
     * beyond milliseconds will be simply ignored. For example, if
     * this duration is "P1.23456S", then 1 is added to SECONDS,
     * 234 is added to MILLISECONDS, and the rest will be unused. 
     * </p>
     * 
     * <p>
     * Note that because {@link Calendar#add(int, int)} is using
     * <tt>int</tt>, {@link Duration} with values beyond the
     * range of <tt>int</tt> in its fields
     * will cause overflow/underflow to the given {@link Calendar}.
     * {@link XMLGregorianCalendar#add(Duration)} provides the same
     * basic operation as this method while avoiding
     * the overflow/underflow issues.
     * 
     * @param calendar
     *      A calendar object whose value will be modified.
     * @throws NullPointerException
     *      if the calendar parameter is null.
     */
public void addTo(Calendar calendar) {
    calendar.add(Calendar.YEAR, getYears() * signum);
    calendar.add(Calendar.MONTH, getMonths() * signum);
    calendar.add(Calendar.DAY_OF_MONTH, getDays() * signum);
    calendar.add(Calendar.HOUR, getHours() * signum);
    calendar.add(Calendar.MINUTE, getMinutes() * signum);
    calendar.add(Calendar.SECOND, getSeconds() * signum);
    if (seconds != null) {
        BigDecimal fraction = seconds.subtract(seconds.setScale(0, BigDecimal.ROUND_DOWN));
        int millisec = fraction.movePointRight(3).intValue();
        calendar.add(Calendar.MILLISECOND, millisec * signum);
    }
}
	/**
     * Adds this duration to a {@link Date} object.
     * 
     * <p>
     * The given date is first converted into
     * a {@link java.util.GregorianCalendar}, then the duration
     * is added exactly like the {@link #addTo(Calendar)} method.
     * 
     * <p>
     * The updated time instant is then converted back into a
     * {@link Date} object and used to update the given {@link Date} object.
     * 
     * <p>
     * This somewhat redundant computation is necessary to unambiguously
     * determine the duration of months and years.
     * 
     * @param date
     *      A date object whose value will be modified.
     * @throws NullPointerException
     *      if the date parameter is null.
     */
public void addTo(Date date) {
    Calendar cal = new GregorianCalendar();
    // this will throw NPE if date==null
    cal.setTime(date);
    this.addTo(cal);
    date.setTime(getCalendarTimeInMillis(cal));
}
	/**
     * Writes {@link Duration} as a lexical representation
     * for maximum future compatibility.
     * 
     * @return
     *      An object that encapsulates the string
     *      returned by <code>this.toString()</code>.
     */
private Object writeReplace() throws IOException {
    return new DurationStream(this.toString());
}
	/**
     * Calls the {@link Calendar#getTimeInMillis} method.
     * Prior to JDK1.4, this method was protected and therefore
     * cannot be invoked directly.
     * 
     * In future, this should be replaced by
     * <code>cal.getTimeInMillis()</code>
     */
private static long getCalendarTimeInMillis(Calendar cal) {
    return cal.getTime().getTime();
}
	/**
     * <p>Turns {@link BigDecimal} to a string representation.</p>
     * 
     * <p>Due to a behavior change in the {@link BigDecimal#toString()}
     * method in JDK1.5, this had to be implemented here.</p>
     * 
     * @param bd <code>BigDecimal</code> to format as a <code>String</code>
     * 
     * @return  <code>String</code> representation of <code>BigDecimal</code> 
     */
private String toString(BigDecimal bd) {
    String intString = bd.unscaledValue().toString();
    int scale = bd.scale();
    if (scale == 0) {
        return intString;
    }
    /* Insert decimal point */
    StringBuffer buf;
    int insertionPoint = intString.length() - scale;
    if (insertionPoint == 0) {
        /* Point goes right before intVal */
        return "0." + intString;
    } else if (insertionPoint > 0) {
        /* Point goes inside intVal */
        buf = new StringBuffer(intString);
        buf.insert(insertionPoint, '.');
    } else {
        /* We must insert zeros between point and intVal */
        buf = new StringBuffer(3 - insertionPoint + intString.length());
        buf.append("0.");
        for (int i = 0; i < -insertionPoint; i++) {
            buf.append('0');
        }
        buf.append(intString);
    }
    return buf.toString();
}
	private int compareResults(int resultA, int resultB) {
    if (resultB == DatatypeConstants.INDETERMINATE) {
        return DatatypeConstants.INDETERMINATE;
    } else if (resultA != resultB) {
        return DatatypeConstants.INDETERMINATE;
    }
    return resultA;
}
}