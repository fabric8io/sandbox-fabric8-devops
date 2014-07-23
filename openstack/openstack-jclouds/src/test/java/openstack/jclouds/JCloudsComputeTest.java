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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jclouds.compute.domain.NodeMetadata;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JCloudsComputeTest {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private List<String> createdNodeIds = new ArrayList<String>();
	private JCloudsCompute compute = new JCloudsCompute();
	private List<String> publicIps = new ArrayList<String>();
	private List<String> privateIps = new ArrayList<String>();
	
	
	@Before
	public void before () throws Exception {
		
		Set<? extends NodeMetadata> nodeMetadatas = compute.createNode();
		
		for (NodeMetadata nodeMetadata : nodeMetadatas) {
			createdNodeIds.add(nodeMetadata.getId());
			publicIps.addAll(nodeMetadata.getPublicAddresses());
			privateIps.addAll(nodeMetadata.getPrivateAddresses());
		}
	}
	

	@Test
	public void testCreateNode() throws Exception {
		
		assertNotNull("No nodes were created", createdNodeIds);
		assertTrue("No nodes were created", createdNodeIds.size() > 0);
		
	}
	
	@Test
	public void testCanPingViaPublicNetwork() throws Exception {
		assertNotNull("No Public Ip's found", publicIps);
		assertTrue("No Public Ip's found", publicIps.size() > 0);
		
		for (String ipAddress : publicIps) {
			assertTrue("Host "+ipAddress+" is NOT reachable", isIpAddressReachable(ipAddress));
		}
	}
	
	@Test
	public void testCanPingViaPrivateNetwork() throws Exception {
		assertNotNull("No Privte Ip's found", privateIps);
		assertTrue("No Privte Ip's found", privateIps.size() > 0);
		
		for (String ipAddress : privateIps) {
			assertTrue("Host "+ipAddress+" is NOT reachable", isIpAddressReachable(ipAddress));
		}
	}
	
	private boolean isIpAddressReachable(String ipAddress) throws Exception {
	    InetAddress inet = InetAddress.getByName(ipAddress);
	    
	    LOG.info("Sending Ping Request to " + ipAddress);
	    return inet.isReachable(5000);

	}
	
	@After
	public void after () throws Exception {
		
		compute.destroyNodes(createdNodeIds);
	}
}
