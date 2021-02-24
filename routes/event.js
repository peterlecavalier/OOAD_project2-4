const express = require('express');
const { Client } = require('pg');

const client = new Client();
let router = express.Router();

router.get('/create', function(req, res, next) {
  res.render('addEvent');
});

router.post('/create', async (req, res) => {
  let summary;
  let description;
  let startTime;
  let allDay;
  let tags;

  try {
    const queryString = 'INSERT INTO events (summary, description, time_start, all_day, tags) VALUES($1, $2, $3, $4, $5) RETURNING event_id'
    const values = [summary, description, startTime, allDay, tags];
    const result = await client.query(queryString, values);
    const { event_id } = result.rows[0];
    res.redirect(`/events/${event_id}`);
  } catch (err) {
    console.log(err);
  }
});

module.exports = router;
