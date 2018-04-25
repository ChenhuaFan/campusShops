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
sudo mv -i /tmp/campusShops/dist/pm2.json /var/www/

# npm install & redeploy pm2
cd /var/www/webServer/
sudo npm install

cd /var/www/services/token/
sudo npm install

cd /var/www/bff/
sudo npm install

cd /var/www/
pm2 start pm2.json