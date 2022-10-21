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

const deleteComment = ($this) => {
  const commentId = $($this).data("value");
  const channelName = $($this).data("channel");
  $.ajax({
    url: channelName + `/comment/${Number(commentId)}/delete`,
    type: "DELETE",
    complete: () => {
      console.log("DELETING COMMENT COMPLETED");
      $("#comment-section").load(`http://localhost:8080/channels/${channelName} #comment-section`);
    }
  })
}

