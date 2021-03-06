package net.benc.export.sql.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FieldTypeParser {
	
	/**
	 * Date format : yyyy-MM-dd'T'HH:mm:ssz
	 */
	private final static SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );

	/**
	 * Convert sql date to string forward format yyyy-MM-dd'T'HH:mm:ssz
	 * @param o
	 * @return
	 */
	public static String dateToString(Object o ) {
		Date date = (Date) o;
		return formatDate(date);
	}
	
	/**
	 * Convert to double
	 * @param o
	 * @return
	 */
	public static double getDouble(Object o) {
		return new Double(o.toString());
	}
	
	/**
	 * Convert to Integer
	 * @param o
	 * @return
	 */
	public static int getInt(Object o) {
		return new Integer(o.toString());
	}
	
	/**
	 * Convert to Long
	 * @param o
	 * @return
	 */
	public static long getLong(Object o) {
		return new Long(o.toString());
	}
	
	/**
	 * Convert to String
	 * @param o
	 * @return
	 */
	public static String getString(Object o) {
		return String.valueOf(o);
	}
	
	/**
	 * Convert to boolean
	 * @param o
	 * @return
	 */
	public static boolean getBoolean(Object o) {
		return new Boolean(o.toString());
	}

	/**
	 * Convert binary to string
	 * @param o
	 * @return
	 */
	public static String getBinary(Object o) {
		return new String((byte[]) o);
	}

	/**
	 * Convert from long to timestamp (format 101567489)
	 * @param o
	 * @return
	 */
	public static Long timestampAsLong(Object o ) {
		Long timestamp = (Long) o;
		return timestamp*1000;
	}
	
	/**
	 * Convert from int to timestamp (format 101567489)
	 * @param o
	 * @return
	 */
	public static Object timestampAsInt(Object o) {
		return timestampAsLong(new Long((Integer)o));
	}
	
	/**
	 * Convert from long to timestamp (format yyyy-MM-dd'T'HH:mm:ssz)
	 * @param o
	 * @return
	 */
	public static String timestampAsLongToHumanFormat(Object o ) {
		return formatDate(new Date(((Long)o)*1000));
	}
	
	/**
	 * Convert from Long to timestamp (format yyyy-MM-dd'T'HH:mm:ssz)
	 * @param o
	 * @return
	 */
	public static String timestampAsIntToHumanFormat(Object o ) {
		return formatDate(new Date(new Long((Integer)o)*1000));
	}	
	
	
	private static String formatDate(Date date) {
		df.setTimeZone( TimeZone.getTimeZone( "UTC" ) );

		String output = df.format( date );

		int inset0 = 9;

		String s0 = output.substring( 0, output.length() - inset0 );
		String s1 = output.substring( output.length() - inset0, output.length()- 6) + "UTC";

		String result = s0 + s1;

		result = result.replaceAll( "UTC", ":00Z" );

		return result;
	}
}
