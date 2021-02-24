const express = require('express');
const authController = require('./controllers/auth');

const router = express.Router();

router.get('/register', (req, res) => {
  res.render('register');
});

router.get('/login', (req, res) => {
  res.render('login');
});

router.post('/register', authController.register);

module.exports = router;
