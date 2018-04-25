#!/bin/bash

# 清空并移动
# 
# webServer token bff
#

sudo rm -rf /var/www/webServer/
sudo rm -rf /var/www/services/
sudo rm -rf /var/www/bff/

sudo mv -i /tmp/campusShops/src/services/ /var/www/
sudo mv -i /tmp/campusShops/src/webServer/ /var/www/
sudo mv -i /tmp/campusShops/src/bff/ /var/www/

# npm install