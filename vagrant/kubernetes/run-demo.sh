#!/bin/sh
echo "running a fabric8 demo pod"
KUBECFG=${KUBECFG:-kubecfg}

${KUBECFG} -c fabric8-master.json create pods
