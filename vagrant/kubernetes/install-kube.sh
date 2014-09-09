#!/bin/bash

# install kubernetes
echo adding epel-release
sudo yum install -y epel-release

echo installing docker
sudo yum install -y docker-io
sudo service docker start
sudo chkconfig docker on
sudo chgrp docker /var/run/docker.sock
sudo usermod -a -G docker fedora

echo "Pulling some common fabric8 docker images down. This takes a while!"

# lets pull some docker images
docker pull fabric8/fabric8

echo installaing go and make
sudo yum install -y make git mercurial which
sudo yum install -y golang
export GOPATH="/opt/go"
export PATH=$GOPATH/bin:$PATH:/usr/bin

go get github.com/tools/godep
go get github.com/coreos/etcd
go install github.com/coreos/etcd

echo cloning kube
sudo mkdir -p {$GOPATH/src/github.com/GoogleCloudPlatform,/opt/kube}
sudo chown -R fedora:fedora $GOPATH /opt/kube
cd $GOPATH/src/github.com/GoogleCloudPlatform

git clone https://github.com/GoogleCloudPlatform/kubernetes
cd kubernetes
echo building kube....
make
cp -f /opt/go/src/github.com/GoogleCloudPlatform/kubernetes/_output/go/bin/* /opt/kube
chmod +x /opt/kube/*
echo done!


