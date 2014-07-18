#!/bin/sh

echo "creating the local vm boxes integration1 and integration2"
vagrant up
vagrant snapshot take integration1 cleanstart
vagrant snapshot take integration2 cleanstart

echo "Done! integration1 and integration2 ready for use"
echo "Use the ./reset.sh script if you ever want to reset these boxes to their clean state"
