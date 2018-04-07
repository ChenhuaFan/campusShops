const crypto = require('crypto');

module.exports = {
    encode: (str, key) => {
        let sha256 = crypto.createHash('sha256');
        let code = (sha256.update(`${str}>${key}`)).digest('hex');
        return code;
    }
}