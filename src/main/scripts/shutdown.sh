#!/bin/bash
ps -ef | grep RateLimitedServiceApplication | grep java | awk '{print $2}' | xargs -l -t kill -9