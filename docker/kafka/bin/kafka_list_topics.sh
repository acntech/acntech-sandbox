#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

source ${SCRIPT_DIR}/kafka_env.sh

if ! docker ps | grep -q "${KAFKA_NODE}"; then
    echo "Kafka Docker container does not exist, cannot continue"
    exit 1
fi

echo "Listing Kafka topics..."

docker exec -it ${KAFKA_NODE} ./bin/kafka-topics.sh --list --bootstrap-server ${BOOTSTRAP_SERVER}

echo "Completed Kafka topics listing"
