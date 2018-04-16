const fs = require('fs');
const services = JSON.parse(fs.readFileSync(__dirname + "/config/services.json"));
let exApis = JSON.parse(fs.readFileSync(__dirname + "/config/exApis.json"));

module.exports = {
    'services': services,
    'exApis': exApis
}