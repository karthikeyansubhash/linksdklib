// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.webservices;

import com.hp.workpath.common.annotation.DeviceApi;

import java.util.Map;

/**
 * Class for accessing HttpRequest.
 *
 * This class provides methods for accessing the headers, method type, attributes and the request body.
 * @since API 6
 */
@DeviceApi
public class HttpRequest {

    /**
     * Default constructor
     * @since API 6
     */
    public HttpRequest() {
    }

    /**
     * Keys for Request-Header fields
     * @since API 6
     */
    public enum HeaderKey {
        /**
         * ACCEPT
         * @since API 6
         */
        ACCEPT("Accept"),
        /**
         * ACCEPT_CHARSET
         * @since API 6
         */
        ACCEPT_CHARSET("Accept-charset"),
        /**
         * ACCEPT_ENCODING
         * @since API 6
         */
        ACCEPT_ENCODING("Accept-encoding"),
        /**
         * ACCEPT_LANGUAGE
         * @since API 6
         */
        ACCEPT_LANGUAGE("Accept-language"),
        /**
         * AUTHORIZATION
         * @since API 6
         */
        AUTHORIZATION("Authorization"),
        /**
         * CACHE_CONTROL
         * @since API 6
         */
        CACHE_CONTROL("Cache-control"),
        /**
         * CONTENT_ENCODING
         * @since API 6
         */
        CONTENT_ENCODING("Content-encoding"),
        /**
         * CONTENT_LANGUAGE
         * @since API 6
         */
        CONTENT_LANGUAGE("Content-language"),
        /**
         * CONTENT_LENGTH
         * @since API 6
         */
        CONTENT_LENGTH("Content-length"),
        /**
         * CONTENT_LOCATION
         * @since API 6
         */
        CONTENT_LOCATION("Content-location"),
        /**
         * CONTENT_TYPE
         * @since API 6
         */
        CONTENT_TYPE("Content-Type"),
        /**
         * COOKIE
         * @since API 6
         */
        COOKIE("Cookie"),
        /**
         * DATE
         * @since API 6
         */
        DATE("Date"),
        /**
         * ETAG
         * @since API 6
         */
        ETAG("Etag"),
        /**
         * EXPIRES
         * @since API 6
         */
        EXPIRES("Expires"),
        /**
         * HOST
         * @since API 6
         */
        HOST("Host"),
        /**
         * IF_MATCH
         * @since API 6
         */
        IF_MATCH("If-match"),
        /**
         * IF_MODIFIED_SINCE
         * @since API 6
         */
        IF_MODIFIED_SINCE("If-modified-since"),
        /**
         * IF_NONE_MATCH
         * @since API 6
         */
        IF_NONE_MATCH("If-none-match"),
        /**
         * IF_UNMODIFIED_SINCE
         * @since API 6
         */
        IF_UNMODIFIED_SINCE("If-unmodified-since"),
        /**
         * LAST_MODIFIED
         * @since API 6
         */
        LAST_MODIFIED("Last-modified"),
        /**
         * LOCATION
         * @since API 6
         */
        LOCATION("Location"),
        /**
         * SET_COOKIE
         * @since API 6
         */
        SET_COOKIE("Set-cookie"),
        /**
         * USER_AGENT
         * @since API 6
         */
        USER_AGENT("User-agent"),
        /**
         * VARY
         * @since API 6
         */
        VARY("Vary"),
        /**
         * X_AUTH
         * @since API 6
         */
        X_AUTH("X-auth"),
        /**
         * WWW_AUTHENTICATE
         * @since API 6
         */
        WWW_AUTHENTICATE("Www-authenticate");

        private final String headerKey;

        HeaderKey(final String headerKey) {
            this.headerKey = headerKey;
        }

        /**
         * @hide trivial
         */
        @Override
        public String toString() {
            return headerKey;
        }
    }

    private Map<String, String> header;
    private String method;
    private Map<String, Object> attribute;
    private String body;

    /**
     * <p>Getter for following 4 type of http method names
     * <ul>
     * <li>GET - Used to request data from a specified resource.</li>
     * <li>POST - Used to send data to a server to create/update a resource.</li>
     * <li>DELETE - Method deletes the specified resource.</li>
     * <li>PUT - Used to send data to a server to create/update a resource.</li>
     * </ul>
     * </p>
     *
     * @return It will return the http callback method name. e.g: GET, POST, DELETE & PUT
     *
     * @since API 6
     */
    public String getMethod() {
        return method;
    }

    /**
     * <p>Setter for http method names like GET, POST, DELETE, PUT</p>
     *
     * @param method Name of the method
     *
     * <p>
     * <ul>
     * <li>There is no minimum & maximum length</li>
     * <li>Instance variable assigned as empty for empty input</li>
     * <li>Instance variable assigned as NULL for null input</li>
     * <li>Expected input - GET, POST, DELETE, PUT</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * <p>Retrieves the value to which the specified key is mapped, or null if this map contains no mapping for the key.</p>
     * @param key The key whose associated value is to be returned
     * <p>
     * <ul>
     * <li>There is no minimum & maximum length</li>
     * <li>Expected input - String value</li>
     * </ul>
     * </p>
     *
     * @return <p>The value to which the specified key is mapped, or null if this map contains no mapping for the key
     * More formally, if this map contains a mapping from a key k to a value v such that (key==null ? k==null : key.equals(k)), then this method returns v; otherwise it returns null. (There can be at most one such mapping.)
     * If this map permits null values, then a return value of null does not necessarily indicate that the map contains no mapping for the key; it's also possible that the map explicitly maps the key to null. The containsKey operation may be used to distinguish these two cases.
     * </p>
     *
     * @since API 6
     */
    public Object getAttribute(String key) {
        return attribute.get(key);
    }

    /**
     * Retrieves the map object of all attributes
     *
     * @return attribute - Map of all attributes
     *
     * @since API 6
     */
    public Map<String, Object> getAttributes() {
        return attribute;
    }


    /**
     * <p>Gets header value from the custom key</p>
     *
     * @param key API header key - e.g: X-Auth, User-Agent, Accept-Encoding etc
     *
     * <p>
     * <ul>
     * <li>There is no minimum & maximum length</li>
     * <li>Instance variable assigned as empty for empty key</li>
     * <li>Instance variable assigned as NULL for null key</li>
     * <li>Expected input - String value</li>
     * </ul>
     * </p>
     *
     * @return <p>Header - value for the API header passed
     * <ul>
     * <li>Return can be null if the value for header is null</li>
     * <li>Return can be empty if the value for header is empty</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public String getHeader(String key) {
        return header.get(key);
    }

    /**
     * <p>Gets header value from {@link HeaderKey}</p>
     *
     * @param key API header key - String from {@link HeaderKey}
     *
     * <p>
     * <ul>
     * <li>There is no minimum & maximum length</li>
     * <li>Cannot be empty since the value is from {@link HeaderKey}</li>
     * <li>Cannot be null since the value is from {@link HeaderKey}</li>
     * <li>Expected input - Value from {@link HeaderKey}</li>
     * </ul>
     * </p>
     *
     * @return <p>Header - value for the API header passed
     * <ul>
     * <li>Return can be null if the value for header is null</li>
     * <li>Return can be empty if the value for header is empty</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public String getHeader(HeaderKey key) {
        return header.get(HeaderKey.valueOf(key.name()).toString());
    }

    /**
     * <p>Returns the map object of all headers</p>
     *
     * @return <p>header - Map of all headers
     *
     * @since API 6
     */
    public Map<String, String> getHeaders() {
        return header;
    }

    /**
     * <p>Method to set header</p>
     *
     * @param header Map of header key and value
     *
     * <p>Sets header as a map of header key and header value pair</p>
     *
     * @since API 6
     */
    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    /**
     * <p>Method to set attribute</p>
     *
     * @param attribute Map of attribute key and value
     *
     * <p>Sets header as a map of attribute key and attribute value pair</p>
     *
     * @since API 6
     */
    public void setAttribute(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    /**
     * Method to get request body
     * @return Body of the request
     *
     * <ul>
     * <li>Return can be null if the body is null</li>
     * <li>Return can be null if the body is empty</li>
     * </ul>
     *
     * @since API 6
     */
    public String getBody() {
        return body;
    }

    /**
     * Method to set request body
     * @param body String request body
     *
     * <p>
     * <ul>
     * <li>There is no minimum & maximum length</li>
     * <li>Instance variable assigned as empty string for empty body</li>
     * <li>Expected input - String value</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public void setBody(String body) {
        this.body = body;
    }
}