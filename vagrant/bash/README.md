## vagrant example scripts

To use these scripts you should [install vagrant](http://www.vagrantup.com/downloads.html) on your box.

Then install the snapshot plugin:

    vagrant plugin install vagrant-vbox-snapshot

### Helper scripts

We've a few shell scripts in this directory to help you:

* ./create.sh creates the vms and creates an initial clean snapshot
* ./reset.sh resets all the vms to their clean snapshot
* ./destroy.sh destroys all the vms

### Logins

The usual login/pwd of the VMs created is usually:

* user: **root**
* password: **vagrant**
