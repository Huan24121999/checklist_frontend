package org.example.model;

/**
 * Type of Check
 * SERVER_CHECK: using command line
 * API_BOOLEAN_CHECK: using RestApi, format response: {isPassed: boolean; message: String}
 * API_DATA_CHECK: using RestApi, format response: {data: String}, using this response to compare
 *
 * @author huannt14
 */
public class CheckType {
    public static final int SERVER_CHECK = 1;
    public static final int API_BOOLEAN_CHECK = 2;
    public static final int API_DATA_CHECK = 3;
}
