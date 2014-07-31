## example scripts when using [Kubernetes](https://github.com/GoogleCloudPlatform/kubernetes)

These examples run fabric8 docker images using the Kubernetes project and a Fedora base image. 
To use any of these scripts you should [set up and install Kubernetes](https://github.com/GoogleCloudPlatform/kubernetes#getting-started-with-a-vagrant-cluster-on-your-host).  

There's a Vagrant image which makes things easy to get up and running.  It might take a couple of goes to install and watch out for docker taking a while to start the first time you run the VM.

* we only need to work with one minion as Fabric will take care of autoscaling and replication.

		export KUBERNETES_NUM_MINIONS=1

* after following the setup instructions and ensuring that docker is running in the minion container you should now be able to  import the example script.

		cluster/kubecfg.sh -c ../fabric8-devops/kubernetes/examples/fabric8-master.json create pods

* Get the host name of the minion container

		cluster/kubecfg.sh list /pods

* after a minute or two connect to hawtio using the configured forwarded port

		http://${MINION_HOST}:48181/hawtio/