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
    type: "GET",
    success: function(msg) {
      console.log("success");
    },
    complete: function() {
      console.log("COMPLETED");
      $("#favorites").load("sidebar.tag #favorites");
    }
  });

});