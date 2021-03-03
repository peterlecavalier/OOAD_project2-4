// import '../styles/signupstyles.css';

const username = document.querySelector('#username');
const password = document.querySelector('#password');
const confirmPassword = document.querySelector('#passwordConfirm');
const email = document.querySelector('#email');
const submitButton = document.querySelector('#formSubmit');
const messageArea = document.querySelector('#form-error-message');

function createMessage(message) {
  const ele = document.createElement('div');
  ele.innerText = message;
  return ele;
}

function checkForm() {
  const errors = [];
  const emailMatcher = /^.+@.+\..+$/;

  if (username.value.length < 8) {
    errors.push(createMessage('Username must be 8 characters long'));
  }

  if (!emailMatcher.test(email.value)) {
    errors.push(createMessage('Invalid Email'));
  }

  if (password.value.length < 8) {
    errors.push(createMessage('The password must be at least 8 characters long'));
  }

  if (password.value !== confirmPassword.value) {
    errors.push(createMessage('The passwords do not match'));
  }

  messageArea.innerHTML = '';
  if (errors.length !== 0) {
    submitButton.disabled = true;
    for (let i = 0; i < errors.length; i++) {
      messageArea.appendChild(errors[i]);
    }
  } else {
    submitButton.disabled = false;
  }
}

const form = document.querySelector('#user-form');

checkForm();
form.addEventListener('keyup', checkForm);
