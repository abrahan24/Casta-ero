$(document).ready( function() {
    $('#data').DataTable();
});

function validatePassword(){
    var password = document.getElementById("password"), confirm_password = document.getElementById("confirm_password");
    if(password.value !== confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
    } else {
        confirm_password.setCustomValidity('');
    }
}

function activateUserReservations() {
    activateMenu('user_reservations');
}

function activateCars() {
    activateMenu('cars');
}

function activateUsers() {
    activateMenu('users');
}

function activateReservations() {
    activateMenu('reservations');
}

function activateLogin() {
    activateMenu('login');
}

function activateRegister() {
    activateMenu('register');
}

function activatePrueba() {
    activateMenu('prueba');
}

function activateAlert() {
    activateMenu('alert');
}

function activateMenu(active) {
    document.getElementById(active).classList.add('active');
}


$(document).ready(function () {

    $('.navbar .dropdown-item').on('click', function (e) {
        var $el = $(this).children('.dropdown-toggle');
        var $parent = $el.offsetParent(".dropdown-menu");
        $(this).parent("li").toggleClass('open');

        if (!$parent.parent().hasClass('navbar-nav')) {
            if ($parent.hasClass('show')) {
                $parent.removeClass('show');
                $el.next().removeClass('show');
                $el.next().css({"top": -999, "left": -999});
            } else {
                $parent.parent().find('.show').removeClass('show');
                $parent.addClass('show');
                $el.next().addClass('show');
                $el.next().css({"top": $el[0].offsetTop, "left": $parent.outerWidth() - 4});
            }
            e.preventDefault();
            e.stopPropagation();
        }
    });

    $('.navbar .dropdown').on('hidden.bs.dropdown', function () {
        $(this).find('li.dropdown').removeClass('show open');
        $(this).find('ul.dropdown-menu').removeClass('show open');
    });

});

$('button').click(function(e){
        e.preventDefault();
        $(this).addClass("draw").delay(400).queue(function(){ $(this).addClass("drawhover").dequeue(); })
     });
 
 