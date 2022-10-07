// $(document).ready(function() {
//     alert("mo");
//   });

// let button = document.getElementById("submit");
// button.addEventListener("click", () => {
//   alert(button.value);

// });


$('#submit').click(function() {
  let button = document.getElementById("submit");
  $.ajax({
    url: button.value,
    type: "POST",
    success: function(msg) {
      alert("favorite success")
    }
  });
});