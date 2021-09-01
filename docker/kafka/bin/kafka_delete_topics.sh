#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

source ${SCRIPT_DIR}/kafka_env.sh

if ! docker ps | grep -q "${KAFKA_NODE}"; then
    echo "Kafka Docker container is not running, cannot continue"
    exit 1
fi

echo "Purging Kafka topics..."

for topic in "${TOPICS[@]}"
do
    if docker exec -it ${KAFKA_NODE} ./bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --list | grep -q "${topic}"; then
        docker exec -it ${KAFKA_NODE} ./bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --delete --topic ${topic}
        echo -e "\t- Deleted Kafka topic ${topic}"
    else
        echo -e "\t- Kafka topic ${topic} does not exist"
    fi
done

echo "Completed Kafka topics purging"
