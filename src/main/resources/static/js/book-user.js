$(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
            });
        });


$(document).ready(function() {
  $('#rateMe1').mdbRate();
});    
        
function rowClicked(user,bookcode) {
        location.href = "/Library/"+ user + "/bookdetail/" + bookcode;
    }