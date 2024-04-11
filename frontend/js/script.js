import apiService from './api-service.js'

$(document).ready(function () {
  var imgs = [
    'img/001-img-rosa-schaufenster.jpg',
    'img/002-img-moldy-horizon.jpg',
    'img/003-img-bw-plants-grass.jpg',
    'img/004-img-rinde-grass.jpg',
    'img/005-img-zweige-grass.jpg',
    'img/006-img-regal-knete.jpg',
    'img/007-scheibe-sommer.jpg',
    'img/008-nixe-stuhl.jpg',
    'img/009-img-orange-rosa.jpg',
    'img/010-img-dinonugget-psychedelic.jpg',
    'img/011-img-croissant-worm.jpg',
    'img/012-img-decken-swirls.jpg',
    'img/013-img-lego-netz.jpg',
    'img/014-img-schleim-lava.jpg',
    'img/015-img-fenster-schuppen.jpg',
    'img/016-img-torte-altar.jpg',
    'img/017-img-schaum-schuppen.jpg',
    'img/018-img-ballon-licht.jpg'
  ];

  // Nächste Slide-Funktion
  function nextSlide() {
    var activeSlide = $('.slide.active');
    activeSlide.removeClass('active').next().addClass('active');

    // Initialisiere das Bild auf Slide 5 nur, wenn die Seite neu geladen wird
    if (activeSlide.hasClass('slide4')) {
      var num1 = Math.floor(Math.random() * (imgs.length - 1)) + 0;
      $('.slide5 img').attr('src', imgs[num1]);
      setTimeout(function () {
        nextSlide();
      }, 20000);
    }


    // Wenn auf Slide 12 gewechselt wird, setze den Text und das Bild ein
    if (activeSlide.hasClass('slide11')) {
      $('.slide12 .slide11-text').text($('.slide11 textarea').val());
      $('.slide12 img').attr('src', $('.slide5 img').attr('src'));
    }
  }

  // Event-Handler für Klicks auf die Slides und den rechten Pfeil
  $(document).on('click', '.slide', function (event) {
    console.log(event.keyCode);
    if (event.type === 'click') {
      nextSlide();
    }
  });
 

  $(document).on('click', 'textarea', function (event) {
    event.stopPropagation()
  });

  $(document).on('keydown', function (event) {
    if (event.keyCode === 39) {
      nextSlide();
    }
  });

  $('textarea').val('')

  $('#main-title').hover(
    function () {
      $('#about-text').addClass('show');
    },
    function () {
      $('#about-text').removeClass('show');
    }
  );
  
 
  
 




});
