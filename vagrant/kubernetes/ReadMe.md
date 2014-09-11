## Create a local kubernetes cluster

This vagrant script creates a new VM and installs a local kubernetes environment on it so you can run fabric8 in kube.

To try it out do:

    ./create.sh

This takes a while! Then you'll have a box kube1 which you can ssh into and run kube.

### Using a linux box instead of vagrant

If you wish to use a separate Fedora image, then just run the **install-kube.sh** script as root on the box to install it all.
Note that after you have run the install script you need to log out and log back in again so that you have karma to run docker.

Then add these to your ~/.bashrc

    export GOPATH=/opt/go
    export PATH=$GOPATH/bin:/usr/bin:/opt/go/src/github.com/GoogleCloudPlatform/kubernetes/_output/go/bin:/opt/go/src/github.com/GoogleCloudPlatform/kubernetes/hack:$PATH

## Running kube

Now you can now boot up a kube via

    local-up-cluster.sh

### If you get this error:

If it barfs saying:

    Starting etcd
    ERROR: timed out for http://localhost:4001/v2/keys/

then edit the etcd startup line in hack/util.sh to:

    vi /opt/go/src/github.com/GoogleCloudPlatform/kubernetes/hack/util.sh

and change the etcd line to be:

    etcd -data-dir ${ETCD_DIR} -l ${host}:${port} >/tmp/etcd.log 2>/tmp/etcd.log &

### Running fabric8 in kube

If you are on a linux box you'll need to grab the json:

    curl https://raw.githubusercontent.com/fabric8io/fabric8-devops/master/vagrant/kubernetes/fabric8-master.json > fabric8-master.json

Otherwise do:

    cd /vagrant

Then create the fabric8 pod via:

    kubecfg -c fabric8-master.json create pods

You can then check on the pods via

    kubecfg list /pods

Once it starts up you should now be able to access the hawtio web console via: http://kube1:48181/hawtio/

### Working on Kube from your Mac

You should now be able to poll the REST API for kube via: http://kube1:8080/api/v1beta1/pods

    curl http://kube1:8080/api/v1beta1/pods

To access using a kube build on your mac or using fabric8 you need to point to the master via:

    export KUBERNETES_MASTER=http://kube1:8080/
