# ReadyAPI HAR Discovery Plugin

This repository contains the source code for the HAR Discovery plugin for [ReadyAPI](https://smartbear.com/product/ready-api/overview/). 

You can use this plugin to create ReadyAPI projects from HTTP Archive format (.har) files. Files of this format store the information about performed HTTP transactions.

## Quick Guide

To create a project from a .har file:

### 1. Install the Plugin

1. Start ReadyAPI and click **Plugins** on the toolbar.

2. In the Plugin Manager, select *HTTP Archive REST Discovery Plugin* and click **Install/Upgrade Plugin**. ReadyAPI will download the plugin and install it.

### 2. Create a Project

1. Select **File | New Project**.

2. In the New Project dialog, switch to the REST Discovery tab and select **Use an HTTP Archive (.har/.zhar) file**. 

3. Click OK and select the .har file to create a project from. ReadyAPI will import the file and create a new project. It will then prompt you to save the created project.

## Installing the Plugin Manually

You can also build the plugin manually from the source code in this repository:

1. Download the repository.

2. Run `mvn clean install` command in the repository root folder, for example:

	```
	C:\Git\ready-api-discover-har-master>mvn clean install
	```

3. Wait for the build to finish. Copy the *\ready-api-discover-har-master\target\ready-api-discover-har-1.0.4-dist.jar* file to the *<user folder>\\.soapui\plugins* folder (for example, on a Windows machine: C:\Users\readyuser\\.soapui\plugins).

4. Start ReadyAPI. If it is already running, restart it to load and use the plugin.

## Learn More About ReadyAPI

[ReadyAPI](http://readyapi.smartbear.com/start)

[About Using Plugins in ReadyAPI](http://readyapi.smartbear.com/readyapi/plugins/start)

[SmartBear Plugins](https://smartbear.com/plugins/)
