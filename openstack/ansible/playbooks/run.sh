# !/bin/bash

if [ -r "./setenv.sh" ]; then
    . setenv.sh
fi

ansible-playbook $1/site.yml -f 10 -i hosts --private-key=~/.ssh/redhat_rsa 
