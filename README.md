amqpspawn: Simple client to consume messages in queue on AMQP server.
===========================

* Author: rinrinne
* Repository: https://github.com/rinrinne/amqpspawn

Synopsis
---------------------------

amqpspawn is a client application for consuming messages in queue on AMQP server.
Now only RabbitMQ is supported.

This is Java application.

Usage
--------------------------
Build:

```console
$ mvn package
```

Run:

```console
$ mvn exec:java -Dexec.mainClass=jp.glassmoon.app.amqpspawn.App -Dexec.args="OPTIONS"
```

`amqpspawn` is convenient shell script:

```console
$ ./amqpspawn
```

This script can execute in current directory only.


Command help:

```console
$ ./amqpspawn -h
Usage: amqpspawn [options] 
  Options:
    -e, --exchange
       Exchange name
       Default: gerrit.event
    -h, --help
       Display this help
       Default: false
    -n, --nospawn
       Create queue only then exit
       Default: false
    -p, --passive
       Do not create the queue if it doesn't exist
       Default: false
    -q, --queue
       Queue name
       Default: ger
    -k, --routingkey
       Routing key
       Default: gerrit.event.*
    -t, --tag
       Consumer tag
       Default: rabbitmq-client
    -u, --uri
       URI to AMQP
       Default: amqp://localhost
```

License
---------------------------

MIT License

Copyright
---------------------------

Copyright (c) 2013 rinrinne a.k.a. rin_ne
