import '../styles/createEvent.css';
import { Modal as _ } from 'bootstrap';

<<<<<<< HEAD
window.openModal = function () {
=======
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

window.openModal = () => {
>>>>>>> 8e945e27a326e07ea8c1866232bfece998b8e903
  console.log('load');
  const name = document.getElementById('eventName');
  name.value = '';
  const description = document.getElementById('eventDescription');
  description.value = '';
  const date = document.getElementById('date');
  date.value = '';
  date.style.visibility= 'visible';
  date.style.height = 'auto';
  const startTime = document.getElementById('startTime');
  startTime.value = '';
  startTime.style.visibility = 'visible';
  startTime.style.height = 'auto';
  document.getElementById('startLabel').innerHTML = 'Start Time:';
  const endTime = document.getElementById('endTime');
  endTime.value = '';
  endTime.style.visibility = 'visible';
  endTime.style.height = 'auto';
  document.getElementById('endLabel').innerHTML = 'End Time:';
  const allDay = document.getElementById('allDay');
  allDay.checked = false;
  allDay.onclick = function() {
      if (allDay.checked){
        document.getElementById('startLabel').innerHTML = '';
        document.getElementById('endLabel').innerHTML = '';
        startTime.style.visibility = 'hidden';
        startTime.style.height = 0;
        endTime.style.visibility = 'hidden';
        endTime.style.height = 0;
      }
      else{
        document.getElementById('startLabel').innerHTML = 'Start Time:';
        document.getElementById('endLabel').innerHTML = 'End Time:';
        startTime.style.visibility = 'visible';
        startTime.style.height = 'auto';
        endTime.style.visibility = 'visible';
        endTime.style.height = 'auto';
      }
  }
};
