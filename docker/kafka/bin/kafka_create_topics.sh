#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

source ${SCRIPT_DIR}/kafka_env.sh

if ! docker ps | grep -q "${KAFKA_NODE}"; then
    echo "Kafka Docker container is not running, cannot continue"
    exit 1
fi

echo "Creating Kafka topics..."

for topic in "${TOPICS[@]}"
do
    if docker exec -it ${KAFKA_NODE} ./bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --list | grep -q "${topic}"; then
        echo -e "\t- Kafka topic ${topic} already exists"
    else
        docker exec -it ${KAFKA_NODE} ./bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --create --topic ${topic} --partitions ${PARTITIONS} --replication-factor ${REPLICATION_FACTOR} > /dev/null
        echo -e "\t- Created Kafka topic ${topic} with partitions ${PARTITIONS} and replication factor ${REPLICATION_FACTOR}"
    fi
done

echo "Completed Kafka topics creation"
