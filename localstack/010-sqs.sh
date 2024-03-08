#!/bin/bash

set -v

awslocal sqs create-queue \
  --queue-name notificarCliente
