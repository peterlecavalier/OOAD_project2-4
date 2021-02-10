const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");

module.exports = {
  mode: 'development',
  entry: {
    main: './src/main.js',
    index: './src/index.js',
  },
  devtool: 'inline-source-map',
  devServer: {
    contentBase: '/dist',
  },
  plugins: [
    new CleanWebpackPlugin(),
    new CopyPlugin({
      patterns: [
        { from: 'assets', to: 'assets' },
      ],
    }),
    new HtmlWebpackPlugin({
      title: 'Home',
      filename: 'index.html',
      template: 'templates/home.hbs',
      chunks: ['main', 'index'],
    }),
    new HtmlWebpackPlugin({
      title: 'About',
      filename: 'about.html',
      template: 'templates/about.hbs',
      chunks: ['main'],
    }),
    new HtmlWebpackPlugin({
      title: 'Login',
      filename: 'login.html',
      template: 'templates/login.hbs',
      chunks: ['main'],
    }),
    new HtmlWebpackPlugin({
      title: 'Register',
      filename: 'register.html',
      template: 'templates/register.hbs',
      chunks: ['main'],
    }),
  ],
  output: {
    filename: '[name].bundle.js',
    path: path.resolve(__dirname, 'dist'),
    publicPath: '/',
  },
  module: {
    rules: [
      {
        test: /\.css$/i,
        use: ['style-loader', 'css-loader'],
      },
      {
        test: /\.hbs$/i,
        use: 'handlebars-loader',
      },
      {
        test: /\.(png|svg|jpg|jpeg|gif)/i,
        type: 'asset/resource',
      },
    ],
  }
}
