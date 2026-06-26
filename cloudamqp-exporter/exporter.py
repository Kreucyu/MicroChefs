"""
CloudAMQP Prometheus Exporter
=============================
Consulta a Management HTTP API do CloudAMQP e expõe métricas
no formato Prometheus para scraping.

Métricas expostas:
  - rabbitmq_queue_messages_ready       (gauge por fila)
  - rabbitmq_queue_messages_unacknowledged (gauge por fila)
  - rabbitmq_queue_consumers            (gauge por fila)
  - rabbitmq_queue_messages_total       (gauge por fila)
"""

import os
import time
import logging
import requests
from urllib.parse import quote
from prometheus_client import start_http_server, Gauge

# ---------------------------------------------------------------------------
# Configuração via variáveis de ambiente
# ---------------------------------------------------------------------------
CLOUDAMQP_API_URL = os.getenv("CLOUDAMQP_API_URL", "https://jackal.rmq.cloudamqp.com/api")
CLOUDAMQP_USER = os.getenv("CLOUDAMQP_USER", "voowobdz")
CLOUDAMQP_PASSWORD = os.getenv("CLOUDAMQP_PASSWORD", "")
CLOUDAMQP_VHOST = os.getenv("CLOUDAMQP_VHOST", "voowobdz")
SCRAPE_INTERVAL = int(os.getenv("SCRAPE_INTERVAL", "10"))  # segundos
EXPORTER_PORT = int(os.getenv("EXPORTER_PORT", "9419"))

# ---------------------------------------------------------------------------
# Logging
# ---------------------------------------------------------------------------
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s [%(levelname)s] %(message)s"
)
logger = logging.getLogger("cloudamqp-exporter")

# ---------------------------------------------------------------------------
# Métricas Prometheus
# ---------------------------------------------------------------------------
MESSAGES_READY = Gauge(
    "rabbitmq_queue_messages_ready",
    "Número de mensagens prontas para consumo",
    ["queue", "vhost"]
)

MESSAGES_UNACKED = Gauge(
    "rabbitmq_queue_messages_unacknowledged",
    "Número de mensagens entregues mas não confirmadas (unacked)",
    ["queue", "vhost"]
)

QUEUE_CONSUMERS = Gauge(
    "rabbitmq_queue_consumers",
    "Número de consumidores conectados à fila",
    ["queue", "vhost"]
)

MESSAGES_TOTAL = Gauge(
    "rabbitmq_queue_messages_total",
    "Número total de mensagens na fila (ready + unacked)",
    ["queue", "vhost"]
)

# ---------------------------------------------------------------------------
# Coleta de métricas
# ---------------------------------------------------------------------------
def fetch_queues():
    """Consulta todas as filas do vhost via Management HTTP API."""
    vhost_encoded = quote(CLOUDAMQP_VHOST, safe="")
    url = f"{CLOUDAMQP_API_URL}/queues/{vhost_encoded}"

    try:
        response = requests.get(
            url,
            auth=(CLOUDAMQP_USER, CLOUDAMQP_PASSWORD),
            timeout=15
        )
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        logger.error(f"Erro ao consultar CloudAMQP API: {e}")
        return []


def update_metrics():
    """Atualiza as métricas Prometheus com dados das filas."""
    queues = fetch_queues()

    for queue in queues:
        name = queue.get("name", "unknown")
        vhost = queue.get("vhost", CLOUDAMQP_VHOST)

        ready = queue.get("messages_ready", 0)
        unacked = queue.get("messages_unacknowledged", 0)
        consumers = queue.get("consumers", 0)
        total = queue.get("messages", 0)

        MESSAGES_READY.labels(queue=name, vhost=vhost).set(ready)
        MESSAGES_UNACKED.labels(queue=name, vhost=vhost).set(unacked)
        QUEUE_CONSUMERS.labels(queue=name, vhost=vhost).set(consumers)
        MESSAGES_TOTAL.labels(queue=name, vhost=vhost).set(total)

        logger.debug(
            f"Fila '{name}': ready={ready}, unacked={unacked}, "
            f"consumers={consumers}, total={total}"
        )

    if queues:
        logger.info(f"Métricas atualizadas para {len(queues)} fila(s)")
    else:
        logger.warning("Nenhuma fila encontrada ou erro na API")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------
def main():
    logger.info(f"Iniciando CloudAMQP Exporter na porta {EXPORTER_PORT}")
    logger.info(f"API URL: {CLOUDAMQP_API_URL}")
    logger.info(f"VHost: {CLOUDAMQP_VHOST}")
    logger.info(f"Intervalo de scrape: {SCRAPE_INTERVAL}s")

    start_http_server(EXPORTER_PORT)
    logger.info(f"Servidor Prometheus rodando em http://0.0.0.0:{EXPORTER_PORT}/metrics")

    while True:
        update_metrics()
        time.sleep(SCRAPE_INTERVAL)


if __name__ == "__main__":
    main()
