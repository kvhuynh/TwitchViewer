$('#submit').click(function() {
  let button = document.getElementById("submit");
  $.ajax({
    url: button.value,
    type: "GET",
    complete: function(button) {
      console.log("COMPLETED");
      $("#favorites").load(`${button.value} #favorites`);
    }
  });
});