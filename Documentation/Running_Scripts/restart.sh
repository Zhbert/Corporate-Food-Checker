#!/bin/bash

rm /home/zhbert/cfc/logs/log
pgrep java | xargs kill -9
nohup java -jar /home/zhbert/cfc/bin/ru.zhbert.corporatefoodchecker-0.0.1.jar > /home/zhbert/cfc/logs/log &

