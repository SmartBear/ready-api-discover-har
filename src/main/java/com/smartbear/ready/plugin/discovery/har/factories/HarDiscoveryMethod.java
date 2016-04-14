package com.smartbear.ready.plugin.discovery.har.factories;

import com.eviware.soapui.impl.actions.DiscoveryMethod;
import com.eviware.soapui.impl.rest.discovery.DiscoveredRequest;
import com.eviware.soapui.model.workspace.Workspace;
import com.eviware.soapui.plugins.auto.PluginDiscoveryMethod;
import com.eviware.soapui.support.UISupport;
import com.smartbear.ready.plugin.discovery.har.HarReader;
import org.apache.commons.lang3.NotImplementedException;

import java.io.File;
import java.util.List;

@PluginDiscoveryMethod
public class HarDiscoveryMethod implements DiscoveryMethod {
    public boolean isSynchronous() {
        return true;
    }

    public void discoverResources(Workspace workspace) {
        throw new NotImplementedException("discoverResources not implemented");
    }

    public List<DiscoveredRequest> discoverResourcesSynchronously(Workspace workspace) {
        File file = UISupport.getFileDialogs().open(null, "Open HTTP Archive file", ".har", "HTTP Archive (.har)", null);
        return HarReader.discoverHar(file);
    }

    public String getLabel() {
        return "Use an HTTP Archive (.har) file";
    }

    public String getDescription() {
        return "Discover an API with a saved .har file";
    }
}
