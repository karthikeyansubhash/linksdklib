// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.webservices;

import com.hp.workpath.common.annotation.DeviceApi;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for accessing HttpResponse.
 *
 * This class provides methods for accessing the response status code, headers, the response body, and the HttpRequest corresponding to this response.
 * @since API 6
 */
@DeviceApi
public class HttpResponse {

    /**
     * Default constructor
     * @since API 6
     */
    public HttpResponse() {
    }
    private Map<String, String> header;
    private String method;
    private int statusCode;
    private String statusDescription;
    private Object body;

    /**
     * <p>Gets header value from the custom key</p>
     *
     * @return <p>Header - value for the API header passed
     * <ul>
     * <li>Return can't be null since the method creates an empty hashmap for null header</li>
     * <li>Return can be empty if the value for header is empty</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public Map<String, String> getHeader() {
        if (header == null) header = new HashMap<>();
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
     * Method to get request body
     * @return Body of the request
     *
     * <p>
     * <ul>
     * <li>Return can be null if the body is null</li>
     * <li>Return can be null if the body is empty</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public Object getBody() {
        return body;
    }

    /**
     * Method to set request body
     * @param body request body
     * <p>Input should be the the body for the HttpResponse</p>
     *
     * @since API 6
     */
    public void setBody(Object body) {
        this.body = body;
    }

    /**
     *  Method to get status code of response
     *
     * @return status code of the http response e.g: 200, 404, 400, etc
     *
     * <p>
     * <ul>
     * <li>Return values conforming to the standards of http status code</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Method to set status code for http response
     *
     * @param statusCode status code for the http response
     *
     *  <p> No minimum or maximum values. It should be based on the HTTP response status code standard.</p>
     *
     * @since API 6
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     *  Method to get status description
     *
     * @return status description of the http response e.g: OK, Not Found, Bad Request, etc
     *
     * <p>
     * <ul>
     * <li>Return can be null if the description is null</li>
     * <li>Return can be null if the description is empty</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public String getStatusDescription() {
        return statusDescription;
    }

    /**
     * Method to set status description for http response
     *
     * @param statusDescription Description for the http response
     *
     * <p>
     * <ul>
     * <li>There is no minimum & maximum length</li>
     * <li>Instance variable assigned as empty string for empty input</li>
     * <li>Instance variable assigned as NULL for null input</li>
     * <li>Expected input - String value</li>
     * </ul>
     * </p>
     *
     *
     * @since API 6
     */
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
} 