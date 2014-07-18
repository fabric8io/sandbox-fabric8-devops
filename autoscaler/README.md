## fabric8 autoscaler examples

This folder containers a bunch of JSON files you can use with the [fabric8 autoscaler](http://fabric8.io/gitbook/requirements.html).

To install a template its easiest to use the CLI in fabric8. e.g. 

    requirements-import https://raw.githubusercontent.com/fabric8io/fabric8-devops/master/autoscaler/mq-demo.json

Or if you are using [vagrant](http://www.vagrantup.com/downloads.html) or have some boxes configured you can run an ssh demo like this...

    requirements-import https://raw.githubusercontent.com/fabric8io/fabric8-devops/master/autoscaler/ssh-mq-demo.json

Then to view the requirements:

    requirements-list

To view the current status of the autoscaler type:

    autoscale-status
    
You can use local file system access to these templates if you want to checkout this repository and play with them.

Alternatively you can use the REST API:

    