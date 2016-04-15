package com.smartbear.ready.plugin.discovery.har;

import com.eviware.soapui.plugins.PluginAdapter;
import com.eviware.soapui.plugins.PluginConfiguration;

@PluginConfiguration(groupId = "com.smartbear.ready.plugins",
        name = "Ready! API HTTP Archive REST Discovery Plugin", version = "1.0",
        description = "Use an HTTP Archive in REST Discovery",
        infoUrl = "https://github.com/joeljons/ready-api-discover-har")
public class PluginConfig extends PluginAdapter {
}
