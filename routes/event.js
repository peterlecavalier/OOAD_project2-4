const express = require('express');

const { pool } = require('./controllers/dbConfig');

const router = express.Router();

router.get('/list', (req, res) => {
  res.render('event/list');
});

router.get('/create', (req, res) => {
  res.render('event/create');
});

router.post('/create', async (req, res, next) => {
  const {
    summary,
    description,
    startTime,
    allDay,
    tags,
  } = req.body;

  try {
    const queryString = 'INSERT INTO events (summary, description, time_start, all_day, tags) VALUES ($1, $2, $3, $4, $5) RETURNING event_id';
    const values = [summary, description, startTime, allDay, tags];
    const result = await pool.query(queryString, values);
    console.log(result);
    const eventId = result.rows[0].event_id;
    res.redirect(`/event/${eventId}`);
  } catch (err) {
    next(err);
  }
});

router.get('/:eventId', (req, res) => {
  res.render('event/detail', { id: req.params.eventId });
});

module.exports = router;
