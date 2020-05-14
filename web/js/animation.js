/**
 * By Santiago Naranjo Marcillo
 */
function myFunction() {
  var x = document.querySelectorAll(".item");
  for(var i = 0; i < x.length; i++){
      if(x[i].classList.contains("active")){
          x[i].classList.remove("active");
          document.getElementById("icon").className = "fas fa-bars";
      } else {
          x[i].classList.add("active");
          document.getElementById("icon").className = "fas fa-times";
      }
  }
}