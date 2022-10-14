$('#submit').click(function() {
    let button = document.getElementById("submit-comment");
    $.ajax({
      url: button.value,
      type: "POST",
      complete: function(button) {
        console.log("COMPLETED");
        $("#comments").load(`${button.value} #comments`);
      }
    });
  });