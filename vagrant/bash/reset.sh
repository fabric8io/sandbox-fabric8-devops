#!/bin/sh
echo "resetting integration1 and integration2 to the cleanstart snapshots"
vagrant snapshot go integration1 cleanstart
vagrant snapshot go integration2 cleanstart

echo "Done! integration1 and integration2 are now all nice and clean!"
