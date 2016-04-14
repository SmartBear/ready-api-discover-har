package com.smartbear.ready.plugin.discovery.har;

import com.eviware.soapui.impl.rest.discovery.DiscoveredRequest;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class HarDiscovererTest {

    @Test
    public void harDiscoveryTest() throws Exception {
        File harFile = new File(HarDiscovererTest.class.getResource("dn.har").getFile().replace("%20", " "));

        List<DiscoveredRequest> discoveredRequests = HarDiscoverer.discoverHar(harFile);

        for (DiscoveredRequest discoveredRequest : discoveredRequests) {
            System.out.println("discoveredRequest = " + discoveredRequest);
        }
    }
    @Test
    public void zharDiscoveryTest() throws Exception {
        File harFile = new File(HarDiscovererTest.class.getResource("test.zhar").getFile().replace("%20", " "));

        List<DiscoveredRequest> discoveredRequests = HarDiscoverer.discoverZhar(harFile);

        for (DiscoveredRequest discoveredRequest : discoveredRequests) {
            System.out.println("discoveredRequest = " + discoveredRequest);
        }
    }
}
