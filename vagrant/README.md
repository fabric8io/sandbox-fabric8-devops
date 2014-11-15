## vagrant example scripts

To use any of these scripts you should [install vagrant](http://www.vagrantup.com/downloads.html) on your box

* [bash](bash/Vagrantfile) uses plain bash so doesn't require anything else to be installed other than vagrant.
* ansible using Ansible with vagrant
* chef using Chef

### Logins

The usual login/pwd of the VMs created is usually:

* user: **root**
* password: **vagrant**

### Creating and destroying containers

* to create the vms type

    vagrant up
    
* to destroy the vms use

    vagrant destroy -f integration1 integration2 
        
### Snapshots

To speed up the reset of the VMs to clean snapshots you can use the snapshot plugin.

Install it via:

    vagrant plugin install vagrant-vbox-snapshot

Now once you've created the vms you can snapshot them...

    vagrant snapshot take integration1 cleanstart
    vagrant snapshot take integration2 cleanstart
