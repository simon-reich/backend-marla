import apiService from './api-service.js';
import imgsData from './data/imgDtos.js';

let timeoutActive = false;

async function storeAndFetchImgs() {
    const imgDtos = imgsData;
    for (const dto of imgDtos) {
        await apiService.postItems(dto);
    }
	return await apiService.getItems();
}

async function sendTextHandler(img) {
    const textDto = { text: $('#img-text').val() };

    if (textDto.text) {
        await apiService.postText(textDto, img.id);
    } else {
        console.log("Description text is empty");
    }
}

async function nextSlide(img) {
    if (timeoutActive) return;

    var activeSlide = $('.slide.active');

    if (activeSlide.hasClass('slide4')) {
        $('.slide5 img').attr('src', img.path);

        timeoutActive = true;

        setTimeout(function () {
            timeoutActive = false;
            nextSlide(img);
        }, 2000);
    }

    if (activeSlide.hasClass('slide11')) {
        const imgText = $('#img-text').val();
        $('.slide12 .slide11-text').text(imgText);
        $('.slide12 img').attr('src', img.path);
    }

    if (activeSlide.hasClass('slide13')) {
        img = await apiService.getItem(img.id);

        const allTextContainer = document.getElementById('all-text-container');

        img.texts.reverse().forEach(text => {
            const paragraph = document.createElement("p");
            paragraph.textContent = text.text;
            allTextContainer.appendChild(paragraph);
        });
    }

    activeSlide.removeClass('active').next().addClass('active');
}


$(document).ready(async function () {
    const imgs = (await apiService.getItems()).length 
		? await apiService.getItems() 
		: await storeAndFetchImgs(); 
    const num = Math.floor(Math.random() * (imgs.length));
    var img = imgs[num];

    $('#send-text-button').on('click', async function () {
        await sendTextHandler(img);
    });

    $(document).on('click', '.slide', function (event) {
        nextSlide(img);
    });

    $(document).on('click', 'textarea', function (event) {
        event.stopPropagation();
    });

    $(document).on('keydown', function (event) {
        if (event.keyCode === 39) {
            nextSlide(img);
        }
    });

    $('#main-title').hover(
        function () {
            $('#about-text').addClass('show');
        },
        function () {
            $('#about-text').removeClass('show');
        }
    );

    $('textarea').val('');
    
});
