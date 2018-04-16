const fs = require('fs');
const path=require('path');
// read
const services = JSON.parse(fs.readFileSync(path.join(__dirname, "../config/services.json")));
const exApis = JSON.parse(fs.readFileSync(path.join(__dirname, "../config/exApis.json")));

module.exports = {
    'services': services,
    'exApis': exApis
}