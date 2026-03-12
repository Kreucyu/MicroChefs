Micro-serviço de pedidos, responsável por processar pagamento, enviar informações do pedido/entrega.

Está sendo feito em Java, Spring e PostegreSQL.


Regras de negócio do Pedido

Gerar identificadores únicos para cada pedido

Armazenar pedidos no banco de dados

Associar cliente e produtos ao pedido

Controlar transição de estados do pedido (não permitir pular estados)

Gerenciar forma de pagamento (liberar para preparo apenas depois da devida confirmação)

Pedido só pode ser criado se o cliente for válido

Pedido deve conter pelo menos um produto

Produtos devem estar em estoque


Infraestrutura

Múltiplas instâncias do serviço de pedidos podem existir

Utilização do algoritmo de Bully para definir um serviço lider dos pedidos

O coordenador gerencia operações principais do sistema

Se houver falha, o Bully irá eleger o novo coordenador