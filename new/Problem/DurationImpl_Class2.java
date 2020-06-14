public class Class2 {
	private Object INDETERMINATE;

	public Class2(Object INDETERMINATE){
		this.INDETERMINATE = INDETERMINATE;
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
}