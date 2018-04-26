#!/bin/bash

# 清空并移动
# 
# webServer token bff
#

sudo rm -rf /var/www/webServer/
sudo rm -rf /var/www/apiVisible/
sudo rm -rf /var/www/services/
sudo rm -rf /var/www/bff/

sudo mv -i /tmp/campusShops/src/webServer/ /var/www/
sudo mv -i /tmp/campusShops/src/apiVisible/ /var/www/
sudo mv -i /tmp/campusShops/src/services/ /var/www/
sudo mv -i /tmp/campusShops/src/bff/ /var/www/
sudo mv -i /tmp/campusShops/dist/pm2.json /var/www/

# npm install & redeploy pm2
# webServer 3000
cd /var/www/webServer/
sudo npm install
# apiVisible
cd /var/www/apiVisible/
sudo npm install
# services 5000
cd /var/www/services/token/
sudo npm install

# bff
# 4001
cd /var/www/bff/admin
sudo npm install
# 4000
cd /var/www/bff/auth
sudo npm install
# 4002
cd /var/www/bff/product
sudo npm install
# 4003
cd /var/www/bff/shop
sudo npm install
# 4004
cd /var/www/bff/user
sudo npm install
# 4005
cd /var/www/bff/order
sudo npm install

# pm2
cd /var/www/
pm2 start pm2.json
pm2 save
sudo env PATH=$PATH:/usr/bin /usr/lib/node_modules/pm2/bin/pm2 startup systemd -u rjxz --hp /home/rjxz

# clear tmp files
cd /tmp/
sudo rm -rf campusShops/
sudo rm -rf shop.tar