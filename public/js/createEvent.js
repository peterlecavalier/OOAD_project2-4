import '../styles/createEvent.css';
import { Modal } from 'bootstrap';

const tags = ['Academic', 'Social', 'Athletics', 'Engineering', 'Arts and Sciences'];

function loadTags() {
  const menu = document.getElementById('tags');
  for (let tagIndex = 0; tagIndex < tags.length; tagIndex++) {
    const tag = document.createElement('a');
    tag.classList.add('dropdown-item');
    tag.href = '#';
    tag.innerHTML = tags[tagIndex].name;
    menu.appendChild(tag);
  }
}

window.openModal = function () {
  console.log('load');
  loadTags();
  const name = document.getElementById('eventName');
  name.value = '';
  const description = document.getElementById('eventDescription');
  description.value = '';
  const date = document.getElementById('date');
  date.value = '';
};
