/**
 *  Copyright 2005-2014 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package openstack.jclouds;
import java.util.List;
import java.util.Set;

import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.compute.domain.Template;
import org.jclouds.compute.options.TemplateOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JCloudsCompute {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
    private final ComputeService compute;
    
    private final String PROVIDER = System.getProperty("FABRIC8_CD_PROVIDER");
    private final String IDENTITY = System.getProperty("FABRIC8_CD_IDENTITY"); // tenantName:userName
    private final String CREDENTIAL = System.getProperty("FABRIC8_CD_CREDENTIAL");
    private final String ENDPOINT = System.getProperty("FABRIC8_CD_ENDPOINT");
    private final String IMAGE = System.getProperty("FABRIC8_CD_IMAGE");
    private final String GROUP = System.getProperty("FABRIC8_CD_GROUP");
    private final String PUBLIC_NETWORK = System.getProperty("FABRIC8_PUBLIC_NETWORK");
    private final String PRIVATE_NETWORK = System.getProperty("FABRIC8_PRIVATE_NETWORK");
	
	/**
	 * Creates compute object using provider and credentials set as system properties
	 */
    public JCloudsCompute() {
    	
        compute = ContextBuilder.newBuilder(PROVIDER)
        		.endpoint(ENDPOINT)
        	    .credentials(IDENTITY, CREDENTIAL)
        	    .buildView(ComputeServiceContext.class)
        	    .getComputeService();
    }

    /**
     * Create nodes on the cloud provider
     * 
     * @return a List of node ID's
     */
    public Set<? extends NodeMetadata> createNode() throws Exception {
    	
    	TemplateOptions options = TemplateOptions.Builder.networks(PRIVATE_NETWORK, PUBLIC_NETWORK); 
    			
    	Template template = compute.templateBuilder()
        	    .options(options)
        	    .imageNameMatches(IMAGE)
        	    .build();
    	
    	Set<? extends NodeMetadata> nodeMetadatas = compute.createNodesInGroup(GROUP, 1, template);
    	
    	if (LOG.isInfoEnabled()){
    		for (NodeMetadata nodeMetadata : nodeMetadatas) {
    			LOG.info("Node created with host name: " + nodeMetadata.getHostname());
    			LOG.info("PublicIPs: " + nodeMetadata.getPublicAddresses());
    			LOG.info("PrivateIPs: " + nodeMetadata.getPrivateAddresses());
    		}
    	}
    		
    	return nodeMetadatas;
    	
    }

    /**
     * Destroy's the cloud providers nodes
     * 
     * @param nodeIds
     * @throws Exception
     */
    public void destroyNodes(List<String> nodeIds) throws Exception {
    	
    	for (String nodeId : nodeIds) {
    		compute.destroyNode(nodeId);
		}
    	
    }
}
