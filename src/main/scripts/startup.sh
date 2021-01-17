#!/bin/bash
nohup java -classpath './lib/*:./conf/' com.questglobal.ratelimitedservice.RateLimitedServiceApplication >run.log 2>&1 &