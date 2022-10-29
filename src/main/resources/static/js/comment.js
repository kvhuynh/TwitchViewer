function onClick($this) {
  var val = $this.previousElementSibling.value;
  console.log($this.value);
  $.ajax( {
    url: $this.value + "/comment",
    data: {commentBody: val},
    type: "POST",
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

const editComment = ($this) => {
  const commentId = $($this).data("value");
  const channelName = $($this).data("channel");
  $.ajax({
    url: channelName + `/comment/${Number(commentId)}/edit`,
    type: "GET",
    complete: () => {
      console.log("LOADING EDITING COMMENT BOX")
      $("#comment-section").load(`http://localhost:8080/channels/${channelName} #comment-section`)
    }
  })
}

const submitEdit = ($this) => {
  var val = $this.previousElementSibling.value;
  console.log($this.value);
  const commentId = $($this).data("value");
  const channelName = $($this).data("channel");
  console.log(commentId);
  console.log(channelName);
  $.ajax( {
    url: channelName + `/comment/${Number(commentId)}/edit/submit`,
    data: {commentBody: val},
    type: "PUT",
    complete: function() {
      console.log("EDITING COMMENT COMPLETED");
      $("#comment-section").load(`http://localhost:8080/channels/${channelName} #comment-section`);
    }
  })
  console.log(val);
}

const likeComment = ($this) => {
  const commentId = $($this).data("value");
  const channelName = $($this).data("channel");
  $.ajax({
    url: channelName + `/comment/${Number(commentId)}/like`,
    type: "GET",
    complete: () => {
      console.log("LIKING COMMENT")
      $("#comment-section").load(`http://localhost:8080/channels/${channelName} #comment-section`)
    }
  })
}

const dislikeComment = ($this) => {
  console.log("disliking this comment");
}

