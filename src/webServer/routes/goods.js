var express = require('express');
var router = express.Router();
var path = require('path');

/* GET users listing. */
router.get('/goodsIndex', function(req, res, next) {
    res.sendFile(path.join(__dirname, '../views/pages/goodsIndex.html')); 
});
module.exports = router;
