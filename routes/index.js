const express = require('express');

const router = express.Router();

/* GET home page. */
router.get('/', (req, res) => {
  res.render('index');
});

/* GET about page */
router.get('/about', (req, res) => {
  res.render('about');
});

/* GET sitemap */
router.get('/sitemap', (req, res) => {
  res.render('sitemap');
});

/* GET map */
router.get('/map', (req, res) => {
  res.render('map');
});

module.exports = router;
