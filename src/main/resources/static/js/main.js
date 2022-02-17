$(document).ready(function () {
    var flag = 0;
    $("li.list-group-item").click(function(){
            $(this).toggleClass('clicked',this.checked);
})})