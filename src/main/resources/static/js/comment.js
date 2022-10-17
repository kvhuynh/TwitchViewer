// $('#submit').click(function() {
//     let button = document.getElementById("post-comment");
//     $.ajax({
//       url: button.value,
//       type: "POST",
//       complete: function(button) {
//         console.log("COMPLETED");
//         $("#comment-section").load(`${button.value} #comment-section`);
//       }
//     });
//   });

function onClick($this) {
  var val = $this.previousElementSibling.value;
  console.log($this.value);
  $.ajax( {
    url: $this.value + "/comment",
    data: {commentBody: val},
    type: "GET",
    complete: function() {
      console.log("COMMENT COMPLETED");
      $("#comment-section").load(`http://localhost:8080/channels/${$this.value} #comment-section`);
    }
  })
  console.log(val);
}