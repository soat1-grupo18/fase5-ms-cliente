// Bad practice. It should be an IAM Role.
resource "aws_iam_user" "ms_cliente" {
  name = "ms-cliente"
  path = "/fiap-store/"
}

resource "aws_iam_access_key" "ms_cliente" {
  user = aws_iam_user.ms_cliente.name
}

data "aws_iam_policy_document" "ms_cliente" {
  statement {
    effect = "Allow"
    actions = [
      "sqs:ChangeMessageVisibility",
      "sqs:DeleteMessage",
      "sqs:GetQueueAttributes",
      "sqs:GetQueueUrl",
      "sqs:ReceiveMessage",
      "sqs:SendMessage",
    ]
    resources = [
      aws_sqs_queue.ms_cliente_evento_pagamento_aprovado.arn,
      aws_sqs_queue.ms_cliente_evento_pagamento_recusado.arn,
    ]
  }
}

resource "aws_iam_user_policy" "ms_cliente" {
  name   = "application"
  user   = aws_iam_user.ms_cliente.name
  policy = data.aws_iam_policy_document.ms_cliente.json
}
