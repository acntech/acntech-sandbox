#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

source ${SCRIPT_DIR}/kafka_env.sh

TOPIC=acntech.sandbox.file.data

if ! docker ps | grep -q "${KAFKA_NODE}"; then
    echo "Kafka Docker container does not exist, cannot continue"
    exit 1
fi

echo "Starting Kafka console consumer on topic ${TOPIC}..."

docker exec -it ${KAFKA_NODE} ./bin/kafka-console-consumer.sh --bootstrap-server ${BOOTSTRAP_SERVER} --topic ${TOPIC}

echo "Aborted Kafka console consumer"
