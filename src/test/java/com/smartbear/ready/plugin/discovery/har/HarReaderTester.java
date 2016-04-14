package com.smartbear.ready.plugin.discovery.har;

import com.eviware.soapui.impl.rest.discovery.DiscoveredRequest;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class HarReaderTester {

    @Test
    public void sazParseTest() throws Exception {
        File sazFile = new File(HarReaderTester.class.getResource("test.har").getFile());

        List<DiscoveredRequest> discoveredRequests = HarReader.discoverHar(sazFile);

        for (DiscoveredRequest discoveredRequest : discoveredRequests) {
            System.out.println("discoveredRequest = " + discoveredRequest);
        }
    }
}
