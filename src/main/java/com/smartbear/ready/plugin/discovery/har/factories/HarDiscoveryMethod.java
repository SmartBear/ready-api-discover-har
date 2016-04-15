package com.smartbear.ready.plugin.discovery.har.factories;

import com.eviware.soapui.impl.actions.DiscoveryMethod;
import com.eviware.soapui.impl.rest.discovery.DiscoveredRequest;
import com.eviware.soapui.model.workspace.Workspace;
import com.eviware.soapui.plugins.auto.PluginDiscoveryMethod;
import com.eviware.soapui.support.UISupport;
import com.eviware.soapui.support.types.StringList;
import com.smartbear.ready.plugin.discovery.har.HarDiscoverer;
import org.apache.commons.lang3.NotImplementedException;

import java.io.File;
import java.util.HashMap;
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
        HashMap<String, StringList> extensions = new HashMap<>();
        extensions.put("HTTP Archive (.har/.zhar)", new StringList(new String[]{".har", ".zhar"}));
        File file = UISupport.getFileDialogs().openFile(null, "Open HTTP Archive file", extensions, "HTTP Archive (.har/.zhar)", null);
        if (file.getName().toLowerCase().endsWith(".zhar")) {
            return HarDiscoverer.discoverZhar(file);
        } else {
            return HarDiscoverer.discoverHar(file);
        }
    }

    public String getLabel() {
        return "Use an HTTP Archive (.har/.zhar) file";
    }

    public String getDescription() {
        return "Discover an API with a saved .har/.zhar file";
    }
}
