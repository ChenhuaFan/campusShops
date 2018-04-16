const request = require('../common/request');
const token = require('../common/readServices').services.token;
const conf = require('../config/default');

module.exports = {
    'get': async req => {
        // use myReq send a request to /token/get to get result
        const header = req.header == undefined ? conf.requestHeader : req.header;
        const body = req.body;
        // here is request fun;
        const result = await request.post(`${token.url}:${token.port}`, header, body);
        return result;
    },
    'update': async req => {
        
    },
    'getSecret': async req => {

    }
}