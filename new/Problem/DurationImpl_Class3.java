public class Class3 {
	private Object MONTH;
	private Object MONTHS;
	private Object YEAR;
	private Object MINUTE;
	private Object YEARS;
	private Object DAY_OF_YEAR;
	private Object DAY_OF_MONTH;
	private Object DAYS;
	private Object MAX_VALUE;
	private Object HOUR_OF_DAY;
	private Object EQUAL;
	private Object SECONDS;
	private Object MIN_VALUE;
	private Object HOURS;
	private Object SECOND;
	private Object MINUTES;

	public Class3(Object MONTH, Object MONTHS, Object YEAR, Object MINUTE, Object YEARS, Object DAY_OF_YEAR, Object DAY_OF_MONTH, Object DAYS, Object MAX_VALUE, Object HOUR_OF_DAY, Object EQUAL, Object SECONDS, Object MIN_VALUE, Object HOURS, Object SECOND, Object MINUTES){
		this.MONTH = MONTH;
		this.MONTHS = MONTHS;
		this.YEAR = YEAR;
		this.MINUTE = MINUTE;
		this.YEARS = YEARS;
		this.DAY_OF_YEAR = DAY_OF_YEAR;
		this.DAY_OF_MONTH = DAY_OF_MONTH;
		this.DAYS = DAYS;
		this.MAX_VALUE = MAX_VALUE;
		this.HOUR_OF_DAY = HOUR_OF_DAY;
		this.EQUAL = EQUAL;
		this.SECONDS = SECONDS;
		this.MIN_VALUE = MIN_VALUE;
		this.HOURS = HOURS;
		this.SECOND = SECOND;
		this.MINUTES = MINUTES;
	}
	/**
     * Checks if a field is set.
     * 
     * A field of a duration object may or may not be present.
     * This method can be used to test if a field is present.
     * 
     * @param field
     *      one of the six Field constants (YEARS,MONTHS,DAYS,HOURS,
     *      MINUTES, or SECONDS.)
     * @return
     *      true if the field is present. false if not.
     * 
     * @throws NullPointerException
     *      If the field parameter is null.
     */
public boolean isSet(DatatypeConstants.Field field) {
    if (field == null) {
        String methodName = "javax.xml.datatype.Duration" + "#isSet(DatatypeConstants.Field field)";
        throw new NullPointerException(//"cannot be called with field == null"
        DatatypeMessageFormatter.formatMessage(null, "FieldCannotBeNull", new Object[] { methodName }));
    }
    if (field == DatatypeConstants.YEARS) {
        return years != null;
    }
    if (field == DatatypeConstants.MONTHS) {
        return months != null;
    }
    if (field == DatatypeConstants.DAYS) {
        return days != null;
    }
    if (field == DatatypeConstants.HOURS) {
        return hours != null;
    }
    if (field == DatatypeConstants.MINUTES) {
        return minutes != null;
    }
    if (field == DatatypeConstants.SECONDS) {
        return seconds != null;
    }
    String methodName = "javax.xml.datatype.Duration" + "#isSet(DatatypeConstants.Field field)";
    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "UnknownField", new Object[] { methodName, field.toString() }));
}
	/**
     * Gets the value of a field. 
     * 
     * Fields of a duration object may contain arbitrary large value.
     * Therefore this method is designed to return a {@link Number} object.
     * 
     * In case of YEARS, MONTHS, DAYS, HOURS, and MINUTES, the returned
     * number will be a non-negative integer. In case of seconds,
     * the returned number may be a non-negative decimal value.
     * 
     * @param field
     *      one of the six Field constants (YEARS,MONTHS,DAYS,HOURS,
     *      MINUTES, or SECONDS.)
     * @return
     *      If the specified field is present, this method returns
     *      a non-null non-negative {@link Number} object that
     *      represents its value. If it is not present, return null.
     *      For YEARS, MONTHS, DAYS, HOURS, and MINUTES, this method
     *      returns a {@link BigInteger} object. For SECONDS, this
     *      method returns a {@link BigDecimal}. 
     * 
     * @throws NullPointerException
     *      If the field parameter is null.
     */
public Number getField(DatatypeConstants.Field field) {
    if (field == null) {
        String methodName = "javax.xml.datatype.Duration" + "#isSet(DatatypeConstants.Field field) ";
        throw new NullPointerException(DatatypeMessageFormatter.formatMessage(null, "FieldCannotBeNull", new Object[] { methodName }));
    }
    if (field == DatatypeConstants.YEARS) {
        return years;
    }
    if (field == DatatypeConstants.MONTHS) {
        return months;
    }
    if (field == DatatypeConstants.DAYS) {
        return days;
    }
    if (field == DatatypeConstants.HOURS) {
        return hours;
    }
    if (field == DatatypeConstants.MINUTES) {
        return minutes;
    }
    if (field == DatatypeConstants.SECONDS) {
        return seconds;
    }
    /**
		throw new IllegalArgumentException(
			"javax.xml.datatype.Duration"
			+ "#(getSet(DatatypeConstants.Field field) called with an unknown field: "
			+ field.toString()
		);
        */
    String methodName = "javax.xml.datatype.Duration" + "#(getSet(DatatypeConstants.Field field)";
    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "UnknownField", new Object[] { methodName, field.toString() }));
}
	/**
     * <p>Makes sure that the given number is non-negative. If it is not,
     * throw {@link IllegalArgumentException}.</p>
     * 
     * @param n Number to test.
     * @param f Field to test.
     */
private static void testNonNegative(BigInteger n, DatatypeConstants.Field f) {
    if (n != null && n.signum() < 0) {
        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "NegativeField", new Object[] { f.toString() }));
    }
}
	/**
     * <p>Makes sure that the given number is non-negative. If it is not,
     * throw {@link IllegalArgumentException}.</p>
     * 
     * @param n Number to test.
     * @param f Field to test.
     */
private static void testNonNegative(BigDecimal n, DatatypeConstants.Field f) {
    if (n != null && n.signum() < 0) {
        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "NegativeField", new Object[] { f.toString() }));
    }
}
	/**
	 * <p>Partial order relation comparison with this <code>Duration</code> instance.</p>
	 * 
	 * <p>Comparison result must be in accordance with
	 * <a href="http://www.w3.org/TR/xmlschema-2/#duration-order">W3C XML Schema 1.0 Part 2, Section 3.2.7.6.2,
	 * <i>Order relation on duration</i></a>.</p>
	 * 
	 * <p>Return:</p>
	 * <ul>
	 *   <li>{@link DatatypeConstants#LESSER} if this <code>Duration</code> is shorter than <code>duration</code> parameter</li>
	 *   <li>{@link DatatypeConstants#EQUAL} if this <code>Duration</code> is equal to <code>duration</code> parameter</li>
	 *   <li>{@link DatatypeConstants#GREATER} if this <code>Duration</code> is longer than <code>duration</code> parameter</li>
	 *   <li>{@link DatatypeConstants#INDETERMINATE} if a conclusive partial order relation cannot be determined</li>
	 * </ul>
	 *
	 * @param duration to compare
	 * 
	 * @return the relationship between <code>this</code> <code>Duration</code>and <code>duration</code> parameter as
	 *   {@link DatatypeConstants#LESSER}, {@link DatatypeConstants#EQUAL}, {@link DatatypeConstants#GREATER}
	 *   or {@link DatatypeConstants#INDETERMINATE}.
	 * 
	 * @throws UnsupportedOperationException If the underlying implementation
	 *   cannot reasonably process the request, e.g. W3C XML Schema allows for
	 *   arbitrarily large/small/precise values, the request may be beyond the
	 *   implementations capability.
	 * @throws NullPointerException if <code>duration</code> is <code>null</code>. 
	 *
	 * @see #isShorterThan(Duration)
	 * @see #isLongerThan(Duration)
	 */
public int compare(Duration rhs) {
    BigInteger maxintAsBigInteger = BigInteger.valueOf((long) Integer.MAX_VALUE);
    BigInteger minintAsBigInteger = BigInteger.valueOf((long) Integer.MIN_VALUE);
    // check for fields that are too large in this Duration
    if (years != null && years.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.YEARS.toString(), years.toString() }));
    }
    if (months != null && months.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.MONTHS.toString(), months.toString() }));
    }
    if (days != null && days.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.DAYS.toString(), days.toString() }));
    }
    if (hours != null && hours.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.HOURS.toString(), hours.toString() }));
    }
    if (minutes != null && minutes.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.MINUTES.toString(), minutes.toString() }));
    }
    if (seconds != null && seconds.toBigInteger().compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.SECONDS.toString(), seconds.toString() }));
    }
    // check for fields that are too large in rhs Duration
    BigInteger rhsYears = (BigInteger) rhs.getField(DatatypeConstants.YEARS);
    if (rhsYears != null && rhsYears.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.YEARS.toString(), rhsYears.toString() }));
    }
    BigInteger rhsMonths = (BigInteger) rhs.getField(DatatypeConstants.MONTHS);
    if (rhsMonths != null && rhsMonths.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.MONTHS.toString(), rhsMonths.toString() }));
    }
    BigInteger rhsDays = (BigInteger) rhs.getField(DatatypeConstants.DAYS);
    if (rhsDays != null && rhsDays.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.DAYS.toString(), rhsDays.toString() }));
    }
    BigInteger rhsHours = (BigInteger) rhs.getField(DatatypeConstants.HOURS);
    if (rhsHours != null && rhsHours.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.HOURS.toString(), rhsHours.toString() }));
    }
    BigInteger rhsMinutes = (BigInteger) rhs.getField(DatatypeConstants.MINUTES);
    if (rhsMinutes != null && rhsMinutes.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.MINUTES.toString(), rhsMinutes.toString() }));
    }
    BigDecimal rhsSecondsAsBigDecimal = (BigDecimal) rhs.getField(DatatypeConstants.SECONDS);
    BigInteger rhsSeconds = null;
    if (rhsSecondsAsBigDecimal != null) {
        rhsSeconds = rhsSecondsAsBigDecimal.toBigInteger();
    }
    if (rhsSeconds != null && rhsSeconds.compareTo(maxintAsBigInteger) == 1) {
        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[] { this.getClass().getName() + "#compare(Duration duration)" + DatatypeConstants.SECONDS.toString(), rhsSeconds.toString() }));
    }
    // turn this Duration into a GregorianCalendar
    GregorianCalendar lhsCalendar = new GregorianCalendar(1970, 1, 1, 0, 0, 0);
    lhsCalendar.add(GregorianCalendar.YEAR, getYears() * getSign());
    lhsCalendar.add(GregorianCalendar.MONTH, getMonths() * getSign());
    lhsCalendar.add(GregorianCalendar.DAY_OF_YEAR, getDays() * getSign());
    lhsCalendar.add(GregorianCalendar.HOUR_OF_DAY, getHours() * getSign());
    lhsCalendar.add(GregorianCalendar.MINUTE, getMinutes() * getSign());
    lhsCalendar.add(GregorianCalendar.SECOND, getSeconds() * getSign());
    // turn compare Duration into a GregorianCalendar
    GregorianCalendar rhsCalendar = new GregorianCalendar(1970, 1, 1, 0, 0, 0);
    rhsCalendar.add(GregorianCalendar.YEAR, rhs.getYears() * rhs.getSign());
    rhsCalendar.add(GregorianCalendar.MONTH, rhs.getMonths() * rhs.getSign());
    rhsCalendar.add(GregorianCalendar.DAY_OF_YEAR, rhs.getDays() * rhs.getSign());
    rhsCalendar.add(GregorianCalendar.HOUR_OF_DAY, rhs.getHours() * rhs.getSign());
    rhsCalendar.add(GregorianCalendar.MINUTE, rhs.getMinutes() * rhs.getSign());
    rhsCalendar.add(GregorianCalendar.SECOND, rhs.getSeconds() * rhs.getSign());
    if (lhsCalendar.equals(rhsCalendar)) {
        return DatatypeConstants.EQUAL;
    }
    return compareDates(this, rhs);
}
	/**
     * Returns a string representation of this duration object.
     * 
     * <p>
     * The result is formatter according to the XML Schema 1.0
     * spec and can be always parsed back later into the
     * equivalent duration object by
     * the {@link #DurationImpl(String)} constructor.
     * 
     * <p>
     * Formally, the following holds for any {@link Duration}
     * object x. 
     * <pre>
     * new Duration(x.toString()).equals(x)
     * </pre>
     * 
     * @return
     *      Always return a non-null valid String object.
     */
public String toString() {
    StringBuffer buf = new StringBuffer();
    if (signum < 0) {
        buf.append('-');
    }
    buf.append('P');
    if (years != null) {
        buf.append(years + "Y");
    }
    if (months != null) {
        buf.append(months + "M");
    }
    if (days != null) {
        buf.append(days + "D");
    }
    if (hours != null || minutes != null || seconds != null) {
        buf.append('T');
        if (hours != null) {
            buf.append(hours + "H");
        }
        if (minutes != null) {
            buf.append(minutes + "M");
        }
        if (seconds != null) {
            buf.append(toString(seconds) + "S");
        }
    }
    return buf.toString();
}
	/**
     * <p>Return the requested field value as an int.</p>
     * 
     * <p>If field is not set, i.e. == null, 0 is returned.</p>
     * 
     * @param field To get value for.
     * 
     * @return int value of field or 0 if field is not set.
     */
private int getInt(DatatypeConstants.Field field) {
    Number n = getField(field);
    if (n == null) {
        return 0;
    } else {
        return n.intValue();
    }
}
	//    /**
//     * Returns an equivalent but "normalized" duration value.
//     * 
//     * Intuitively, the normalization moves YEARS into
//     * MONTHS (by x12) and moves DAYS, HOURS, and MINUTES fields
//     * into SECONDS (by x86400, x3600, and x60 respectively.)
//     * 
//     * 
//     * Formally, this method satisfies the following conditions:
//     * <ul>
//     *  <li>x.normalize().equals(x)
//     *  <li>!x.normalize().isSet(Duration.YEARS)
//     *  <li>!x.normalize().isSet(Duration.DAYS)
//     *  <li>!x.normalize().isSet(Duration.HOURS)
//     *  <li>!x.normalize().isSet(Duration.MINUTES)
//     * </ul>
//     * 
//     * @return
//     *      always return a non-null valid value. 
//     */
//    public Duration normalize() {
//        return null;
//    }
/**
     * <p>Converts the years and months fields into the days field
     * by using a specific time instant as the reference point.</p>
     * 
     * <p>For example, duration of one month normalizes to 31 days
     * given the start time instance "July 8th 2003, 17:40:32".</p>
     * 
     * <p>Formally, the computation is done as follows:</p>
     * <ol>
     *  <li>The given Calendar object is cloned.
     *  <li>The years, months and days fields will be added to
     *      the {@link Calendar} object
     *      by using the {@link Calendar#add(int,int)} method. 
     *  <li>The difference between two Calendars are computed in terms of days.
     *  <li>The computed days, along with the hours, minutes and seconds
     *      fields of this duration object is used to construct a new
     *      Duration object.
     * </ol>
     * 
     * <p>Note that since the Calendar class uses <code>int</code> to
     * hold the value of year and month, this method may produce
     * an unexpected result if this duration object holds
     * a very large value in the years or months fields.</p>
     *
     * @param startTimeInstant <code>Calendar</code> reference point.
     *  
     * @return <code>Duration</code> of years and months of this <code>Duration</code> as days.
     * 
     * @throws NullPointerException If the startTimeInstant parameter is null.
     */
public Duration normalizeWith(Calendar startTimeInstant) {
    Calendar c = (Calendar) startTimeInstant.clone();
    // using int may cause overflow, but 
    // Calendar internally treats value as int anyways.
    c.add(Calendar.YEAR, getYears() * signum);
    c.add(Calendar.MONTH, getMonths() * signum);
    c.add(Calendar.DAY_OF_MONTH, getDays() * signum);
    // obtain the difference in terms of days
    long diff = getCalendarTimeInMillis(c) - getCalendarTimeInMillis(startTimeInstant);
    int days = (int) (diff / (1000L * 60L * 60L * 24L));
    return new DurationImpl(days >= 0, null, null, wrap(Math.abs(days)), (BigInteger) getField(DatatypeConstants.HOURS), (BigInteger) getField(DatatypeConstants.MINUTES), (BigDecimal) getField(DatatypeConstants.SECONDS));
}
	/**
     * <p>Gets the value of the field as a {@link BigDecimal}.</p>
     * 
     * <p>If the field is unset, return 0.</p>
     * 
     * @param f Field to get value for.
     * 
     * @return  non-null valid {@link BigDecimal}.
     */
private BigDecimal getFieldAsBigDecimal(DatatypeConstants.Field f) {
    if (f == DatatypeConstants.SECONDS) {
        if (seconds != null) {
            return seconds;
        } else {
            return ZERO;
        }
    } else {
        BigInteger bi = (BigInteger) getField(f);
        if (bi == null) {
            return ZERO;
        } else {
            return new BigDecimal(bi);
        }
    }
}
	/**
     * <p>Computes a new duration whose value is <code>this+rhs</code>.</p>
     * 
     * <p>For example,</p>
     * <pre>
     * "1 day" + "-3 days" = "-2 days"
     * "1 year" + "1 day" = "1 year and 1 day"
     * "-(1 hour,50 minutes)" + "-20 minutes" = "-(1 hours,70 minutes)"
     * "15 hours" + "-3 days" = "-(2 days,9 hours)"
     * "1 year" + "-1 day" = IllegalStateException
     * </pre>
     * 
     * <p>Since there's no way to meaningfully subtract 1 day from 1 month,
     * there are cases where the operation fails in
     * {@link IllegalStateException}.</p> 
     * 
     * <p>
     * Formally, the computation is defined as follows.</p>
     * <p>
     * Firstly, we can assume that two {@link Duration}s to be added
     * are both positive without losing generality (i.e.,
     * <code>(-X)+Y=Y-X</code>, <code>X+(-Y)=X-Y</code>,
     * <code>(-X)+(-Y)=-(X+Y)</code>)
     * 
     * <p>
     * Addition of two positive {@link Duration}s are simply defined as  
     * field by field addition where missing fields are treated as 0.
     * <p>
     * A field of the resulting {@link Duration} will be unset if and
     * only if respective fields of two input {@link Duration}s are unset. 
     * <p>
     * Note that <code>lhs.add(rhs)</code> will be always successful if
     * <code>lhs.signum()*rhs.signum()!=-1</code> or both of them are
     * normalized.</p>
     * 
     * @param rhs <code>Duration</code> to add to this <code>Duration</code>
     * 
     * @return
     *      non-null valid Duration object.
     * 
     * @throws NullPointerException
     *      If the rhs parameter is null.
     * @throws IllegalStateException
     *      If two durations cannot be meaningfully added. For
     *      example, adding negative one day to one month causes
     *      this exception.
     * 
     * 
     * @see #subtract(Duration)
     */
public Duration add(final Duration rhs) {
    Duration lhs = this;
    BigDecimal[] buf = new BigDecimal[6];
    buf[0] = sanitize((BigInteger) lhs.getField(DatatypeConstants.YEARS), lhs.getSign()).add(sanitize((BigInteger) rhs.getField(DatatypeConstants.YEARS), rhs.getSign()));
    buf[1] = sanitize((BigInteger) lhs.getField(DatatypeConstants.MONTHS), lhs.getSign()).add(sanitize((BigInteger) rhs.getField(DatatypeConstants.MONTHS), rhs.getSign()));
    buf[2] = sanitize((BigInteger) lhs.getField(DatatypeConstants.DAYS), lhs.getSign()).add(sanitize((BigInteger) rhs.getField(DatatypeConstants.DAYS), rhs.getSign()));
    buf[3] = sanitize((BigInteger) lhs.getField(DatatypeConstants.HOURS), lhs.getSign()).add(sanitize((BigInteger) rhs.getField(DatatypeConstants.HOURS), rhs.getSign()));
    buf[4] = sanitize((BigInteger) lhs.getField(DatatypeConstants.MINUTES), lhs.getSign()).add(sanitize((BigInteger) rhs.getField(DatatypeConstants.MINUTES), rhs.getSign()));
    buf[5] = sanitize((BigDecimal) lhs.getField(DatatypeConstants.SECONDS), lhs.getSign()).add(sanitize((BigDecimal) rhs.getField(DatatypeConstants.SECONDS), rhs.getSign()));
    // align sign
    // Y,M
    alignSigns(buf, 0, 2);
    // D,h,m,s
    alignSigns(buf, 2, 6);
    // make sure that the sign bit is consistent across all 6 fields.
    int s = 0;
    for (int i = 0; i < 6; i++) {
        if (s * buf[i].signum() < 0) {
            throw new IllegalStateException();
        }
        if (s == 0) {
            s = buf[i].signum();
        }
    }
    return new DurationImpl(s >= 0, toBigInteger(sanitize(buf[0], s), lhs.getField(DatatypeConstants.YEARS) == null && rhs.getField(DatatypeConstants.YEARS) == null), toBigInteger(sanitize(buf[1], s), lhs.getField(DatatypeConstants.MONTHS) == null && rhs.getField(DatatypeConstants.MONTHS) == null), toBigInteger(sanitize(buf[2], s), lhs.getField(DatatypeConstants.DAYS) == null && rhs.getField(DatatypeConstants.DAYS) == null), toBigInteger(sanitize(buf[3], s), lhs.getField(DatatypeConstants.HOURS) == null && rhs.getField(DatatypeConstants.HOURS) == null), toBigInteger(sanitize(buf[4], s), lhs.getField(DatatypeConstants.MINUTES) == null && rhs.getField(DatatypeConstants.MINUTES) == null), (buf[5].signum() == 0 && lhs.getField(DatatypeConstants.SECONDS) == null && rhs.getField(DatatypeConstants.SECONDS) == null) ? null : sanitize(buf[5], s));
}
}