## example script when using [Kubernetes](https://github.com/GoogleCloudPlatform/kubernetes)

To use any of these scripts you should [install Kubernetes](https://github.com/GoogleCloudPlatform/kubernetes#getting-started-with-a-vagrant-cluster-on-your-host) on your box.  Vagrant image might take a couple of goes to install.

* only need to work with one minion for now

`export KUBERNETES_NUM_MINIONS=1`

* after following the setup instructions and ensuring that docker in running in the minion container you should now be able to  import the example script.

`cluster/kubecfg.sh -c ../fabric8-devops/kubernetes/examples/fabric8-master.json create pods`

* Get the host name of the minion container

`cluster/kubecfg.sh list /pods`

* connect to hawtio using the configured forwarded port

`http://${MINION_HOST}:48181/hawtio/`