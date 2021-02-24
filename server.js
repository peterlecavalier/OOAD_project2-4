const express = require('express');
const webpack = require('webpack');
const webpackDevMiddleware = require('webpack-dev-middleware');

// Admin: http://127.0.0.1:50500/browser/

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
