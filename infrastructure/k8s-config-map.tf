resource "kubernetes_config_map" "application_config" {
  metadata {
    name      = "application-config"
    namespace = "ms-cliente"
  }

  data = {
    aws_region                           = data.aws_region.current.name
    aws_sqs_endpoint                     = "https://sqs.${data.aws_region.current.name}.amazonaws.com"
    aws_sqs_queue_pagamento_aprovado_arn = aws_sqs_queue.ms_cliente_evento_pagamento_aprovado.arn
    aws_sqs_queue_pagamento_recusado_arn = aws_sqs_queue.ms_cliente_evento_pagamento_recusado.arn
    spring_data_source_url               = "jdbc:postgresql://${aws_db_instance.fiap_ms_cliente.endpoint}/${aws_db_instance.fiap_ms_cliente.db_name}"
  }

  depends_on = [kubernetes_namespace.ms_cliente]
}
