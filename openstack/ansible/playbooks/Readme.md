## Playbooks

You can use these playbooks to provision your servers with Fabric8.

The _hosts_ file is where you put your server IP/DNS catalog. For example, the machines that will make up your ensemble will go in the _[ensemble]_ section.

You can edit the [environment](setenv.sh) file to specify which versions of fabric8 to use and where they should be installed on the machine.

You can run the playbooks with the [run script](run.sh) provided. You'll want to use your private keys (see the [run.sh](run.sh) file as well as the [files for fabric8-ensemble](provision/roles/fabric8-ensemble/files/). You'll want to use the files you specify in [setenv.sh](setenv.sh) as well as your private key.
