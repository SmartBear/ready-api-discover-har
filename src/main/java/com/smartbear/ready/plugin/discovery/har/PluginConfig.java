package com.smartbear.ready.plugin.discovery.har;

import com.eviware.soapui.plugins.PluginAdapter;
import com.eviware.soapui.plugins.PluginConfiguration;

@PluginConfiguration(groupId = "com.smartbear.ready.plugins",
        name = "HTTP Archive REST Discovery Plugin", version = "1.0.3-SNAPSHOT",
        description = "Use an HTTP Archive (HAR) in REST Discovery",
        infoUrl = "https://github.com/SmartBear/ready-api-discover-har",
        minimumSoapUIVersion = "99.0.0",
        minimumReadyApiVersion="3.20.1")
public class PluginConfig extends PluginAdapter {
}
