package com.smartbear.ready.plugin.discovery.har;

import com.eviware.soapui.impl.rest.discovery.DiscoveredRequest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

class HarDiscovererTest {

    @Test
    void harDiscoveryTest() {
        File harFile = new File(HarDiscovererTest.class.getResource("dn.har").getFile().replace("%20", " "));

        List<DiscoveredRequest> discoveredRequests = HarDiscoverer.discoverHar(harFile);

        for (DiscoveredRequest discoveredRequest : discoveredRequests) {
            System.out.println("discoveredRequest = " + discoveredRequest);
        }
    }
    @Test
    void zharDiscoveryTest() {
        File harFile = new File(HarDiscovererTest.class.getResource("test.zhar").getFile().replace("%20", " "));

        List<DiscoveredRequest> discoveredRequests = HarDiscoverer.discoverZhar(harFile);

        for (DiscoveredRequest discoveredRequest : discoveredRequests) {
            System.out.println("discoveredRequest = " + discoveredRequest);
        }
    }
}
