module.exports = {
    encode: content => {
        let b = new Buffer(content);
        return b.toString('base64');
    },
    decode: code => {
        let b = new Buffer(code, 'base64');
        return b.toString();
    }
}