#!/bin/sh

echo "creating the vm boxes (this can take a while!!!)"
vagrant up

echo "snapshotting the local vm boxes"
vagrant snapshot take kube1 cleanstart

echo "Done! boxes ready for use"
echo "Use the ./reset.sh script if you ever want to reset these boxes to their clean state"
