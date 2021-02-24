const express = require('express');
const { Client } = require('pg');

const client = new Client();
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
    const queryString = 'INSERT INTO events (summary, description, time_start, all_day, tags) VALUES($1, $2, $3, $4, $5) RETURNING event_id';
    const values = [summary, description, startTime, allDay, tags];
    const result = await client.query(queryString, values);
    const { eventId } = result.rows[0];
    res.redirect(`/events/${eventId}`);
  } catch (err) {
    next(err);
  }
});

router.get('/:eventId', (req, res) => {
  res.render('event/detail', { id: req.params.eventId });
});

module.exports = router;
