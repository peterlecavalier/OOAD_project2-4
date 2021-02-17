# CU App
[![Lint Code Base](https://github.com/CSCI-3308-CU-Boulder/3308SP21_section014_7/workflows/Lint%20Code%20Base/badge.svg)](https://github.com/marketplace/actions/super-linter)
![tests](https://github.com/CSCI-3308-CU-Boulder/3308SP21_section014_7/workflows/Node.js%20CI/badge.svg)


## Description
This application will have many features that students at CU can use to improve their lives. The main feature will be a schedule planner which will allow students to add their classes, clubs, tests, homework, and other activities to their personal schedule. They can also link their calendar to their phone so they can get updates about important events coming up.

The application’s schedule feature will include reminders of to-do’s, ability to add tasks to the calendar, and campus event updates. The application will also allow users to create accounts so they access their personal schedule.

The application will also have map features which will show where their classes are and detailed maps of the buildings around campus.

## Development
### Including frontend javascript
For frontend code, certain files are used as entry points and compiled into chunks.
Those are listed in `webpack.config.js`.
To configure what javascript files should be used, edit `webpack.config.js`.
What chunks are used for which views can be edited in `webpack.config.js`.

### Including css
If you want to use a style sheet, include them in the relevent javascript file, rather than the view files.

```javascript
import '../styles/style.css';
```

This is due to how webpack is setup.

### Editing html
Edit the view templates in the `views/` directory to edit html.
Which views correspond to which file is viewable in `webpack.config.js`.

The `home.hbs` file acts as the body of `index.html`.

The contents of `.hbs` files should correspond to the body of a normal `.html` file. Make sure `{{#> layout}}` is at the start and `{{/layout}}` is at the end of the file.

A html development server can be run using `yarn start`. This will recompile the html files when they are changed.
Those changes are also automatically show up in the browser.

### yarn
`yarn start` will start a development server for frontend code.

`yarn server` will run server side code.

`yarn lint` will check the formatting for javascript code.

`yarn test` will run javascript tests.

## Architecture
Postgresql will be used as the database. The cu class api will be used for getting class information. The backend server will use nodejs and be hosted on heroku. The frontend will be written in html, javascript, and css.

![Architecture Diagram](./assets/architecture.png)
