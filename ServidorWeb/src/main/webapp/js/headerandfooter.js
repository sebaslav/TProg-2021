var i;

function w3_open() {
	/* if (x.matches) {
  document.getElementById("sesion").style.display = 'none';
  } */
  document.getElementById("topnav").style.marginLeft = "25%";
  document.getElementById("topnav").style.width = "75%";
  document.getElementById("main").style.marginLeft = "25%";
  document.getElementById("mySidebar").style.width = "25%";
  document.getElementById("mySidebar").style.display = "block";
  document.getElementById("openNav").style.display = 'none';
  
}
function w3_close() {
	/*if (x.matches) {
   document.getElementById("sesion").style.display = 'block';
   } */
  document.getElementById("topnav").style.marginLeft = "0%";
   document.getElementById("topnav").style.width = "100%";
  document.getElementById("main").style.marginLeft = "0%";
  document.getElementById("mySidebar").style.display = "none";
  document.getElementById("openNav").style.display = "inline-block";
 
  }


/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */

var dropdown = document.getElementsByClassName("dropdown-btn");


for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
} 