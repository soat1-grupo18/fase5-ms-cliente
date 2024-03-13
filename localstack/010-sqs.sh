#!/bin/bash

set -v

awslocal sqs create-queue \
  --queue-name microsservico-cliente-evento-pagamento-aprovado-dlq

awslocal sqs create-queue \
  --queue-name microsservico-cliente-evento-pagamento-aprovado \
  --attributes '{ "RedrivePolicy": "{\"deadLetterTargetArn\":\"arn:aws:sqs:us-east-1:000000000000:microsservico-cliente-evento-pagamento-aprovado-dlq\",\"maxReceiveCount\":\"3\"}" }'

awslocal sqs create-queue \
  --queue-name microsservico-cliente-evento-pagamento-recusado-dlq

awslocal sqs create-queue \
  --queue-name microsservico-cliente-evento-pagamento-recusado \
  --attributes '{ "RedrivePolicy": "{\"deadLetterTargetArn\":\"arn:aws:sqs:us-east-1:000000000000:microsservico-cliente-evento-pagamento-recusado-dlq\",\"maxReceiveCount\":\"3\"}" }'
