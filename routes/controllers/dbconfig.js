require('dotenv').config();

const { Pool } = require('pg');

// uses environment variables to create connect
// READ: https://node-postgres.com/features/connecting
// important variables:
// PGUSER
// PGHOST
// PGDATABASE
const pool = new Pool();

module.exports = { pool };
