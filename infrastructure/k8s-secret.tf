resource "kubernetes_secret" "example" {
  metadata {
    name      = "aws-credentials"
    namespace = "ms-cliente"
  }

  data = {
    aws_access_key = aws_iam_access_key.ms_cliente.id     # Bad practice. It should use IAM Role and OIDC.
    aws_secret_key = aws_iam_access_key.ms_cliente.secret # Bad practice. It should use IAM Role and OIDC.
  }

  type = "opaque"

  depends_on = [kubernetes_namespace.ms_cliente]
}
