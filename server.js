const express = require('express');
const webpack = require('webpack');
const webpackDevMiddleware = require('webpack-dev-middleware');

<<<<<<< HEAD
// Admin: http://127.0.0.1:50500/browser/
=======
// --- Postgres Database //

const Client = require("pg").Client;
const client = new Client();
await client.connect();

const res = await client.query("SELECT $1::text as message", ["Hello world!"])
console.log(res.rows[0].message);
await client.end()



//  --- MySQL Database Code ---
const mysql = require('mysql');
const dotenv = require('dotenv');

dotenv.config({ path: './passwords.env' });

const db = mysql.createConnection({
  host: process.env.DATABASE_HOST,
  user: process.env.DATABASE_USER,
  password: process.env.DATABASE_PASSWORD,
  database: process.env.DATABASE,
});

db.connect((error) => {
  if (error) {
    console.log(error);
  } else {
    console.log('MySQL Connected...');
  }
});

// Go to this link: http://localhost/phpmyadmin/

// --- End Database Code ---
>>>>>>> 435b5347d3d3bde6c7f3be53c67d3f353d621ea0

const app = express();
const config = require('./webpack.config');

const compiler = webpack(config);

const port = process.env.PORT || 8000;

// const publicDirectory = path.join(__dirname, "./styles");
// app.use(express.static(publicDirectory));
//

app.set('view engine', 'hbs');

// Postgres Stuff:
app.use(express.urlencoded({ extended: false }));




app.use(express.json());

app.use(
  webpackDevMiddleware(compiler, {
    publicPath: config.output.publicPath,
  }),
);

app.use(express.static('public'));

app.use('/auth', require('./routes/auth'));

// The 404 Route (ALWAYS Keep this as the last route)
app.get('*', (req, res) => {
  res.redirect('assets/404-page.html');
});

app.listen(port, () => {
  console.log(`CU app listening at http://localhost:${port}`);
});
