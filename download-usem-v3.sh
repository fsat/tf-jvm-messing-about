#!/bin/bash

URL="https://tfhub.dev/google/universal-sentence-encoder-multilingual/3?tf-hub-format=compressed"

MODEL_DOWNLOAD_DIR="model-downloads/tf-hub/usem/v3"
MODEL_DOWNLOAD_FILE="${MODEL_DOWNLOAD_DIR}/usem-v3.tar.gz"

if [ ! -f "${MODEL_DOWNLOAD_FILE}" ]; then
  mkdir -p $MODEL_DOWNLOAD_DIR
  curl -v -L -o $MODEL_DOWNLOAD_DIR/usem-v3.tar.gz $URL
fi


MODEL_DIR="model/tf-hub/usem/v3"
mkdir -p $MODEL_DIR
tar -xzvf $MODEL_DOWNLOAD_FILE -C $MODEL_DIR
