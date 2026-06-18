// IServicesToLibraryInterface.aidl
package com.hp.jetadvantage.link.svcmgt.services;

interface ISvcmgtServicesToLibraryInterface {
    String callService(String request, String requestHeaders, String requestBody);
}