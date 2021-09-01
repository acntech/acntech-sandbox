#!/bin/bash

KAFKA_NODE=sandbox_kafka
BOOTSTRAP_SERVER=${KAFKA_NODE}:9092

TOPICS=(
    "acntech.sandbox.file.data"
    )

PARTITIONS=1
REPLICATION_FACTOR=1
