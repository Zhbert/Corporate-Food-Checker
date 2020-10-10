#!/bin/bash

mvn clean package

echo 'Copy files...'

scp target/ru.zhbert.corporatefoodchecker-0.0.1.jar \
    zhbert@cfc.zhbert.ru:/home/zhbert/cfc/bin

echo 'Please restart server...'
echo 'Bye'