package com.smartbear.ready.plugin.discovery.har;

import com.eviware.soapui.impl.rest.RestRequestInterface;
import com.eviware.soapui.impl.rest.discovery.DiscoveredRequest;
import com.eviware.soapui.support.UISupport;
import com.eviware.soapui.support.types.StringToStringsMap;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderMode;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarEntry;
import de.sstoehr.harreader.model.HarHeader;
import de.sstoehr.harreader.model.HarPostDataParam;
import de.sstoehr.harreader.model.HarRequest;
import de.sstoehr.harreader.model.HttpMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Converts a HTTP Archive to Ready! API DiscoveredRequest, which can be used for Discovery.
 * <a href="http://www.softwareishard.com/blog/har-12-spec/">HTTP Archive 1.2 specification</a>
 */

public class HarDiscoverer {
    private static final Logger logger = LoggerFactory.getLogger(HarDiscoverer.class);

    public static List<DiscoveredRequest> discoverHar(File harFile) {
        if (!harFile.exists()) {
            UISupport.showErrorMessage("File not found: " + harFile.getAbsolutePath());
            return Collections.emptyList();
        }

        ArrayList<DiscoveredRequest> discoveredRequests = new ArrayList<>();
        try {
            Har har = new HarReader().readFromFile(harFile, HarReaderMode.LAX);
            for (HarEntry entry : har.getLog().getEntries()) {
                try {
                    HarRequest request = entry.getRequest();
                    if(request.getMethod() == HttpMethod.CONNECT){
                        continue;
                    }
                    discoveredRequests.add(
                            new DiscoveredRequest.Builder()
                                    .setBody(extractBody(request))
                                    .setUrl(new URL(request.getUrl()))
                                    .setRequestMethod(RestRequestInterface.HttpMethod.valueOf(request.getMethod().toString()))
                                    .setHeaders(extractHeaders(request))
                                    .setProtocolVersion(request.getHttpVersion())
                                    .createDiscoveredRequest());

                } catch (Exception e) {
                    logger.error("Error discovering HAR entry", e);
                }
            }
        } catch (Exception e) {
            UISupport.showErrorMessage(e);
            return Collections.emptyList();
        }
        return discoveredRequests;
    }

    public static List<DiscoveredRequest> discoverZhar(File harFile) {
        ArrayList<DiscoveredRequest> discoveredRequests = new ArrayList<>();
        try (ZipFile zipFile = new ZipFile(harFile)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.getName().toLowerCase().endsWith(".har")) {
                    File tempFile = File.createTempFile("unpacked_zhar", ".har");
                    try {
                        IOUtils.copy(zipFile.getInputStream(entry), new FileOutputStream(tempFile));
                        discoveredRequests.addAll(discoverHar(tempFile));
                    } finally {
                        tempFile.delete();
                    }
                }
            }
            return discoveredRequests;
        } catch (IOException e) {
            UISupport.showErrorMessage(e);
            logger.error("Error reading zhar file", e);
        }
        return discoveredRequests;
    }

    private static String extractBody(HarRequest request) {
        String body = null;
        if (StringUtils.isNotEmpty(request.getPostData().getText())) {
            body = request.getPostData().getText();
        } else if (!request.getPostData().getParams().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (HarPostDataParam harPostDataParam : request.getPostData().getParams()) {
                if (sb.length() != 0) {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(harPostDataParam.getName())).append("=").append(URLEncoder.encode(harPostDataParam.getValue()));
            }
            body = sb.toString();
        }
        return body;
    }

    private static StringToStringsMap extractHeaders(HarRequest request) {
        StringToStringsMap headers = new StringToStringsMap();
        for (HarHeader header : request.getHeaders()) {
            headers.add(header.getName(), header.getValue());
        }
        return headers;
    }
}
