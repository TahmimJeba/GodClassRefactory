public class Class1 {

	public Class1(){
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
}