resource "kubernetes_namespace" "ms_cliente" {
  metadata {
    name = "ms-cliente"
  }
}
