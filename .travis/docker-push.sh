#!/usr/bin/env bash

echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
export REPO=jerverless/jerverless
export TAG=`if [ "$TRAVIS_BRANCH" == "master" ]; then echo "latest"; else echo $TRAVIS_TAG ; fi`
docker build -f Dockerfile -t $REPO:$TAG .
docker push $REPO
