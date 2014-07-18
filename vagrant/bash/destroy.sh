#!/bin/sh

echo "destroying the vm boxes integration1 and integration2"

vagrant destroy -f integration1 integration2

echo "integration1 and integration2 now destroyed"
