/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* This is out callback function for when a rating is submitted
 */
function setBookRatingByUser(id, rating)
{            
    callBeanMethod([{name:'bookRating',value:id + "-" + rating}]);    
}

function initializeRatebox() {
    $(function() {    
        $( '.ratebox' ).raterater( { 
            submitFunction: 'setBookRatingByUser', 
            allowChange: true,
            starWidth: 20,
            spaceWidth: 5,
            numStars: 5
        });    
    });
}

/* Here we initialize raterater on our rating boxes */
$(function() {    
    $( '.ratebox' ).raterater( { 
        submitFunction: 'setBookRatingByUser', 
        allowChange: true,
        starWidth: 20,
        spaceWidth: 5,
        numStars: 5
    });    
});