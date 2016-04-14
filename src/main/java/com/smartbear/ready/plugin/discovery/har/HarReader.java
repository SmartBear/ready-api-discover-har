package com.smartbear.ready.plugin.discovery.har;

import com.eviware.soapui.impl.rest.RestRequestInterface;
import com.eviware.soapui.impl.rest.discovery.DiscoveredRequest;
import com.eviware.soapui.support.UISupport;
import com.eviware.soapui.support.types.StringToStringsMap;
import com.google.gson.Gson;
import com.smartbear.ready.plugin.discovery.har.json.Entry;
import com.smartbear.ready.plugin.discovery.har.json.Har;
import com.smartbear.ready.plugin.discovery.har.json.Param;
import com.smartbear.ready.plugin.discovery.har.json.Request;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Converts a HTTP Archive to Ready! API DiscoveredRequest, which can be used for Discovery.
 * <a href="http://www.softwareishard.com/blog/har-12-spec/">HTTP Archive 1.2 specification</a>
 */

public class HarReader {

    public static List<DiscoveredRequest> discoverHar(File harFile) {
        if (!harFile.exists()) {
            UISupport.showErrorMessage("File not found: " + harFile.getAbsolutePath());
            return Collections.emptyList();
        }

        ArrayList<DiscoveredRequest> discoveredRequests = new ArrayList<>();
        try (FileReader reader = new FileReader(harFile)) {
            try {
                Har har = new Gson().fromJson(reader, Har.class);

                for (Entry entry : har.log.entries) {
                    Request request = entry.request;
                    StringToStringsMap headers = new StringToStringsMap();
                    for (Param header : request.headers) {
                        headers.add(header.name, header.value);
                    }
                    discoveredRequests.add(new DiscoveredRequest.Builder()
                            .setUrl(new URL(request.url))
                            .setRequestMethod(RestRequestInterface.HttpMethod.valueOf(request.method))
                            .setHeaders(headers)
                            .setProtocolVersion(request.httpVersion)
                            .createDiscoveredRequest());
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            UISupport.showErrorMessage(e);
            return Collections.emptyList();
        }


        return discoveredRequests;

    }
}
