var tags = ["Academic", "Social", "Athletics", "Engineering", "Arts and Sciences"];

function loadTags(){
  var menu = document.getElementById("tags");
  for (let tagIndex = 0; tagIndex < tags.length; tagIndex++){
    var tag = document.createElement("a");
    tag.classList.add("dropdown-item");
    tag.href="#";
    tag.innerHTML = tags[tagIndex].name;
    menu.appendChild(tag);
  }
}

function openModal(){
  loadTags();
  var name = document.getElementById("eventName");
  name.value = "";
  var description = document.getElementById("eventDescription");
  description.value = "";
  var date = document.getElementById("date");
  date.value = "";
}
