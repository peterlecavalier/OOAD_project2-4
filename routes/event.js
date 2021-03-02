const express = require('express');

const { pool } = require('./controllers/dbConfig');

const router = express.Router();

router.get('/list', async (req, res, next) => {
  try {
    const queryString = 'SELECT event_id, summary, description, time_start FROM events';
    const results = await pool.query(queryString);
    res.render('event/list', {eventList: results.rows});
  } catch (err) {
    next(err);
  }
});

router.get('/', (req, res) => {
  res.redirect('/event/list');
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
    const eventId = result.rows[0].event_id;
    res.redirect(`/event/${eventId}`);
  } catch (err) {
    next(err);
  }
});

router.get('/:eventId', async (req, res, next) => {
  let id = req.params.eventId;
  if (isNaN(id)) {
    next();
    return;
  }

  try {
    const queryString = 'SELECT summary, description, time_start, all_day, tags FROM events WHERE event_id = $1';
    const results = await pool.query(queryString, [id]);
    const rows = results.rows;
    if (rows.length === 0) {
      next();
      return;
    }
    let event = rows[0];
    res.render('event/detail', {event});
  } catch (err) {
    next(err);
  }
});

module.exports = router;
