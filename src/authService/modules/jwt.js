const b64 = require('./b64');
const sha256 = require('./sha256');

module.exports = {
    create: (header, payload, secret) => {
        header  = b64.encode(JSON.stringify(header));
        payload = b64.encode(JSON.stringify(payload));
        secret = sha256.encode(`${header}.${payload}`, secret);
        return `${header}.${payload}.${secret}`;
    }
}