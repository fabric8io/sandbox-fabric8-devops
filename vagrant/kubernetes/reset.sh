#!/bin/sh
echo "resetting to the cleanstart snapshots"
vagrant snapshot go kube1 cleanstart

echo "Done!"
