var express = require('express');
var router = express.Router();
var path = require('path');

/* GET users listing. */
router.get('/signInUp', function(req, res, next) {
  res.sendFile(path.join(__dirname, '../views/pages/signInUp.html')); 
});

module.exports = router;
