

This example demonstrates how to connect to a cloud provider using [jclouds](https://jclouds.apache.org/) to create instances so we can provision using fabric in order to run effective integration and non-functional tests in a true CD manor.

As this will normally be triggered from a CI or CD tool we need to pass environment specific details.  Below is a list of parameters required in order to integrate with a cloud provider like OpenStack.


Therefore an integration test can be run using the traditional 'mvn test' or 'mvn integration-test ' whilst setting the following as -D args.


    FABRIC8_CD_PROVIDER
    FABRIC8_CD_IDENTITY
    FABRIC8_CD_CREDENTIAL
    FABRIC8_CD_ENDPOINT
    FABRIC8_CD_IMAGE
    FABRIC8_CD_GROUP
    FABRIC8_CD_PRIVATE_NETWORK
    FABRIC8_CD_PUBLIC_NETWORK


Example:

    mvn clean install -DFABRIC8_CD_PROVIDER=openstack-nova -DFABRIC8_CD_IDENTITY=demo:demo -DFABRIC8_CD_CREDENTIAL=12345678910 -DFABRIC8_CD_ENDPOINT=http://somehost:5000/v2.0 -DFABRIC8_CD_IMAGE=cirros -DFABRIC8_CD_GROUP=jclouds -DFABRIC8_PRIVATE_NETWORK= 89cbaefb-0e5f-433d-a4a7-c4a6e85f6379 -DFABRIC8_PUBLIC_NETWORK=36be73d8-9b10-46c1-8e46-0a0f76604c7c


