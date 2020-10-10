#!/bin/bash

rm /home/zhbert/cfc/logs/log
nohup java -jar /home/zhbert/cfc/bin/ru.zhbert.corporatefoodchecker-0.0.1.jar > /home/zhbert/cfc/logs/log &

