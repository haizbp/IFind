$(function(){

	$( window ).resize(function() {
		var height = $( window ).height();
		var width = $( window ).width();
		
		$("#googleMap").width(width);
		$("#googleMap").height(height);
	});
	
	$(".closeBox").click(function(){
		$("#nav").hide(500);
	});
	
	$(".mnAct").click(function(){
		var type = $(this).attr("id");
		showItm(type);
		return false;
	});
});

function showItm(type){
	$("#nav .itmAct").hide();
			
	if(type == 'searchMn'){
		$(".searchMn").show();
	}
	
	$("#nav").show(500);
}

var map;
var infoWindow;
var markers = [];
function mapInit(){
	var height = $( window ).height();
	var width = $( window ).width();
	
	$("#googleMap").width(width);
	$("#googleMap").height(height);
	
	var mapProp= {
		center:new google.maps.LatLng(10.762622,106.660172),
		zoom:5,
		fullscreenControl: true,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		mapTypeControl: true,
		mapTypeControlOptions: { 
				style: google.maps.MapTypeControlStyle.DROPDOWN_MENU , 
			 position: google.maps.ControlPosition.RIGHT_TOP 
		}, 
		  zoomControl: true, 
		  zoomControlOptions: { 
			 style: google.maps.ZoomControlStyle.DEFAULT, 
			 position: google.maps.ControlPosition.RIGHT_BOTTOM 
		  }, 
      streetViewControl: true, 
      streetViewControlOptions: { 
         style: google.maps.ZoomControlStyle.DEFAULT, 
			 position: google.maps.ControlPosition.RIGHT_BOTTOM 
      }, 
      panControl: true, 
      panControlOptions: { 
         position: google.maps.ControlPosition.RIGHT_BOTTOM 
      },
      scaleControl: true, 
      scaleControlOptions: { 
         position: google.maps.ControlPosition.RIGHT_BOTTOM 
      } 
	};
	map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
	
	// for search
	var input = document.getElementById('searchBox');
	var searchBox = new google.maps.places.SearchBox(input);
	
	map.addListener('bounds_changed', function() {
          searchBox.setBounds(map.getBounds());
        });
		
	var searchForm = document.getElementById('searchForm');

	searchForm.onsubmit = function () {

	  var input = document.getElementById('searchBox');

		google.maps.event.trigger(input, 'focus')
		google.maps.event.trigger(input, 'keydown', { keyCode: 13 });
	  
	  return false;
	}
	
	searchBox.addListener('places_changed', function() {
          var places = searchBox.getPlaces();

          if (places.length == 0) {
            return;
          }

         

          // For each place, get the icon, name and location.
          var bounds = new google.maps.LatLngBounds();
          places.forEach(function(place) {
            if (!place.geometry) {
              console.log("Returned place contains no geometry");
              return;
            }

            // Create a marker for each place.
            markers.push(new google.maps.Marker({
              map: map,
              position: place.geometry.location
            }));

            if (place.geometry.viewport) {
              // Only geocodes have viewport.
              bounds.union(place.geometry.viewport);
            } else {
              bounds.extend(place.geometry.location);
            }
          });
          map.fitBounds(bounds);
        });
	
	// for current location
	if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
			
            // Create a marker for each place.
            markers.push(new google.maps.Marker({
              map: map,
              position: pos
            }));
			
			map.setCenter(pos);
			
          }, function() {
            
          });
        }
	
	// for streetview

	var thePanorama = map.getStreetView();

	google.maps.event.addListener(thePanorama, 'visible_changed', function() {

		if (thePanorama.getVisible()) {

			var panorama = new google.maps.StreetViewPanorama(
				document.getElementById('googleMap'), {
				 
				  addressControlOptions: {
					position: google.maps.ControlPosition.TOP_CENTER
				  },
				  linksControl: true,
				  panControl: true,
				  enableCloseButton: true,
				  fullscreenControl: true
			});
			
			map.setStreetView(panorama);

		} else {

			// Display your original UI

		}

	});
}
