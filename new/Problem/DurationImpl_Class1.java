public class Class1 {
	private Object ROUND_UP;

	public Class1(Object ROUND_UP){
		this.ROUND_UP = ROUND_UP;
	}
	private static void alignSigns(BigDecimal[] buf, int start, int end) {
    // align sign
    boolean touched;
    do {
        // repeat until all the sign bits become consistent
        touched = false;
        // sign of the left fields
        int s = 0;
        for (int i = start; i < end; i++) {
            if (s * buf[i].signum() < 0) {
                // this field has different sign than its left field.
                touched = true;
                // compute the number of unit that needs to be borrowed.
                BigDecimal borrow = buf[i].abs().divide(FACTORS[i - 1], BigDecimal.ROUND_UP);
                if (buf[i].signum() > 0) {
                    borrow = borrow.negate();
                }
                // update values
                buf[i - 1] = buf[i - 1].subtract(borrow);
                buf[i] = buf[i].add(borrow.multiply(FACTORS[i - 1]));
            }
            if (buf[i].signum() != 0) {
                s = buf[i].signum();
            }
        }
    } while (touched);
}
	private Object readResolve() throws ObjectStreamException {
    //            try {
    return new DurationImpl(lexical);
//            } catch( ParseException e ) {
//                throw new StreamCorruptedException("unable to parse "+lexical+" as duration");
//            }
}
}