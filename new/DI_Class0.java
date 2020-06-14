public class Class0 {
	private Object MONTH;
	private Object MONTHS;
	private Object YEAR;
	private Object signum;
	private Object ROUND_DOWN;
	private Object DAY_OF_YEAR;
	private Object DAY_OF_MONTH;
	private Object DAYS;
	private Object MAX_VALUE;
	private Object SECONDS;
	private Object MILLISECOND;
	private Object FIELD_UNDEFINED;
	private Object MINUTE;
	private Object YEARS;
	private Object HOUR;
	private Object HOUR_OF_DAY;
	private Object INDETERMINATE;
	private Object EQUAL;
	private Object MIN_VALUE;
	private Object HOURS;
	private Object SECOND;
	private Object MINUTES;

	public Class0(Object MONTH, Object MONTHS, Object YEAR, Object signum, Object ROUND_DOWN, Object DAY_OF_YEAR, Object DAY_OF_MONTH, Object DAYS, Object MAX_VALUE, Object SECONDS, Object MILLISECOND, Object FIELD_UNDEFINED, Object MINUTE, Object YEARS, Object HOUR, Object HOUR_OF_DAY, Object INDETERMINATE, Object EQUAL, Object MIN_VALUE, Object HOURS, Object SECOND, Object MINUTES){
		this.MONTH = MONTH;
		this.MONTHS = MONTHS;
		this.YEAR = YEAR;
		this.signum = signum;
		this.ROUND_DOWN = ROUND_DOWN;
		this.DAY_OF_YEAR = DAY_OF_YEAR;
		this.DAY_OF_MONTH = DAY_OF_MONTH;
		this.DAYS = DAYS;
		this.MAX_VALUE = MAX_VALUE;
		this.SECONDS = SECONDS;
		this.MILLISECOND = MILLISECOND;
		this.FIELD_UNDEFINED = FIELD_UNDEFINED;
		this.MINUTE = MINUTE;
		this.YEARS = YEARS;
		this.HOUR = HOUR;
		this.HOUR_OF_DAY = HOUR_OF_DAY;
		this.INDETERMINATE = INDETERMINATE;
		this.EQUAL = EQUAL;
		this.MIN_VALUE = MIN_VALUE;
		this.HOURS = HOURS;
		this.SECOND = SECOND;
		this.MINUTES = MINUTES;
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
     * Compares 2 given durations. (refer to W3C Schema Datatypes "3.2.6 duration")
     *
     * @param duration1  Unnormalized duration
     * @param duration2  Unnormalized duration
     * @return INDETERMINATE if the order relationship between date1 and date2 is indeterminate.
     * EQUAL if the order relation between date1 and date2 is EQUAL.
     * If the strict parameter is true, return LESS_THAN if date1 is less than date2 and
     * return GREATER_THAN if date1 is greater than date2.
     * If the strict parameter is false, return LESS_THAN if date1 is less than OR equal to date2 and
     * return GREATER_THAN if date1 is greater than OR equal to date2
     */
private int compareDates(Duration duration1, Duration duration2) {
    int resultA = DatatypeConstants.INDETERMINATE;
    int resultB = DatatypeConstants.INDETERMINATE;
    XMLGregorianCalendar tempA = (XMLGregorianCalendar) TEST_POINTS[0].clone();
    XMLGregorianCalendar tempB = (XMLGregorianCalendar) TEST_POINTS[0].clone();
    //long comparison algorithm is required
    tempA.add(duration1);
    tempB.add(duration2);
    resultA = tempA.compare(tempB);
    if (resultA == DatatypeConstants.INDETERMINATE) {
        return DatatypeConstants.INDETERMINATE;
    }
    tempA = (XMLGregorianCalendar) TEST_POINTS[1].clone();
    tempB = (XMLGregorianCalendar) TEST_POINTS[1].clone();
    tempA.add(duration1);
    tempB.add(duration2);
    resultB = tempA.compare(tempB);
    resultA = compareResults(resultA, resultB);
    if (resultA == DatatypeConstants.INDETERMINATE) {
        return DatatypeConstants.INDETERMINATE;
    }
    tempA = (XMLGregorianCalendar) TEST_POINTS[2].clone();
    tempB = (XMLGregorianCalendar) TEST_POINTS[2].clone();
    tempA.add(duration1);
    tempB.add(duration2);
    resultB = tempA.compare(tempB);
    resultA = compareResults(resultA, resultB);
    if (resultA == DatatypeConstants.INDETERMINATE) {
        return DatatypeConstants.INDETERMINATE;
    }
    tempA = (XMLGregorianCalendar) TEST_POINTS[3].clone();
    tempB = (XMLGregorianCalendar) TEST_POINTS[3].clone();
    tempA.add(duration1);
    tempB.add(duration2);
    resultB = tempA.compare(tempB);
    resultA = compareResults(resultA, resultB);
    return resultA;
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
	/**
     * <p>Computes a new duration whose value is <code>this-rhs</code>.</p>
     * 
     * <p>For example:</p>
     * <pre>
     * "1 day" - "-3 days" = "4 days"
     * "1 year" - "1 day" = IllegalStateException
     * "-(1 hour,50 minutes)" - "-20 minutes" = "-(1hours,30 minutes)"
     * "15 hours" - "-3 days" = "3 days and 15 hours"
     * "1 year" - "-1 day" = "1 year and 1 day"
     * </pre>
     * 
     * <p>Since there's no way to meaningfully subtract 1 day from 1 month,
     * there are cases where the operation fails in {@link IllegalStateException}.</p> 
     * 
     * <p>Formally the computation is defined as follows.
     * First, we can assume that two {@link Duration}s are both positive
     * without losing generality.  (i.e.,
     * <code>(-X)-Y=-(X+Y)</code>, <code>X-(-Y)=X+Y</code>,
     * <code>(-X)-(-Y)=-(X-Y)</code>)</p>
     *  
     * <p>Then two durations are subtracted field by field.
     * If the sign of any non-zero field <tt>F</tt> is different from
     * the sign of the most significant field,
     * 1 (if <tt>F</tt> is negative) or -1 (otherwise)
     * will be borrowed from the next bigger unit of <tt>F</tt>.</p>
     * 
     * <p>This process is repeated until all the non-zero fields have
     * the same sign.</p> 
     * 
     * <p>If a borrow occurs in the days field (in other words, if
     * the computation needs to borrow 1 or -1 month to compensate
     * days), then the computation fails by throwing an
     * {@link IllegalStateException}.</p>
     * 
     * @param rhs <code>Duration</code> to substract from this <code>Duration</code>.
     *  
     * @return New <code>Duration</code> created from subtracting <code>rhs</code> from this <code>Duration</code>.
     * 
     * @throws IllegalStateException
     *      If two durations cannot be meaningfully subtracted. For
     *      example, subtracting one day from one month causes
     *      this exception.
     * 
     * @throws NullPointerException
     *      If the rhs parameter is null.
     * 
     * @see #add(Duration)
     */
public Duration subtract(final Duration rhs) {
    return add(rhs.negate());
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
}