$(document).ready(function() {
    $("body").fadeIn(400);

$('#myCarousel').carousel()
$('#newProductCar').carousel()

/* Home page item price animation */
$('.thumbnail').mouseenter(function() {
   $(this).children('.zoomTool').fadeIn();
});

$('.thumbnail').mouseleave(function() {
	$(this).children('.zoomTool').fadeOut();
});

// Show/Hide Sticky "Go to top" button
	$(window).scroll(function(){
		if($(this).scrollTop()>200){
			$(".gotop").fadeIn(200);
		}
		else{
			$(".gotop").fadeOut(200);
		}
	});
// Scroll Page to Top when clicked on "go to top" button
	$(".gotop").click(function(event){
		event.preventDefault();

		$.scrollTo('#gototop', 1500, {
        	easing: 'easeOutCubic'
        });
	});
	
	
	$("#check-out-btn").on('click', () => {
		console.log("check out button has been clicked.");
		var items = $('.items-num').first().text();
		console.log("items: " + items);
		if (items === "0") {
			alert("Your shopping cart is empty");
		}
		else {
//			$.get('confirmation');
			window.location.href = "confirmation";
		}
		
	})
	
	
	
//	$('#register-form').submit((e) => {
//		console.log("form has been submitted.");
//		e.preventDefault();
//		if ($.validator) {
//			console.log("jQuery validator has been loaded.");
//		}		
//		
//	})
	
	
	$.validator.addMethod( "alphabetic", function( value, element ) {
		return this.optional( element ) || /^[a-z A-Z]+$/.test( value );
	}, "Letters, and spaces only please" );
	
	
	$.validator.addMethod( "emailvalidator", function( value, element ) {
		return this.optional( element ) || /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test( value );
	}, "Input valid email address please" );
	
	$.validator.addMethod( "pwdvalidator", function( value, element ) {
		return this.optional( element ) ||   /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/i.test( value );
	}, "Minimum 8 charcters ,Please enter at least one letter and one number" );	
	
	
	$('#register-form').validate({
		
		rules: {
			firstName: {
				required: true,
				alphabetic:true
			},
			lastName: {
				required: true,
				alphabetic:true
			},
			email: {
				required: true,
				emailvalidator:true
			},
			password: {
				required: true,
				pwdvalidator: true
			}
		},
		
		messages: {
			
			firstName: {
				required: "First name mandatory",
				alphabetic: "Letters, and spaces only please"
			},
			lastName: {
				required: "Last name mandatory",
				alphabetic: "Letters, and spaces only please"
			},
			email: {
				required: "Email mandatory",
				emailvalidator: "Input valid email please"
			},
			password: {
				required: "Password mandatory",
				pwdvalidator: "Minimum 8 charcters ,Please enter at least one letter and one number"
			}			
			
			
		},
		
		submitHandler: () => {
//			alert("register successfully!");
//			window.location.href = "register";
			
			$('#register-form')[0].submit();
			
		}
	})	
	
	
	
	
	
	$('#search-bar').on('click', () => {
		console.log("search bar has been clicked.");
		
		$.get('getproducts', (res) => {
			console.log(res);
			$('#search-bar').autocomplete({
				source: res.split('_')
			})
			
		})
	})
	
	$('#search-bar').on('keyup', (e) => {
		console.log("some key entered.");
		if (e.keyCode === 13) {
			console.log("enter key has been pressed.");
//			console.log($('#search-bar').val());
			var name = $('#search-bar').val();
			console.log(name);
			window.location.href = "search_product?name=" + name;
			
		}
	})
	
//	$('#search-bar').on('input', (e) => {
//		console.log("something happened.");
//		if (e.keyCode === 13) {
//			console.log("enter key has been pressed.");
//		}
//	})
	
	
	
	
	
	
//	$("#register-form").on('click', (e) => {
//		e.preventDefault();
//		console.log("you sumbitted register form.");
//		
//		if ($.validator) {
//			console.log("jQuery validator has been loaded.");
//		}
//		
//	})
//	.validate({
//		
//		rules: {
//			firstName: {
//				required: true
//			}
//		},
//		
//		messages: {
//			
//			firstName: {
//				required: "First name mandatory"
//			}
//		}
//	});
	
	
//	$('#register-submit').validate({
//		
//	})
	
	
	
	// add event listeners to increase, decrease and delete buttons.
	for (let i = 0; i < 10; i++) {
		(function(i){
			var btn1 = "#btn" + i + 1;
			var btn2 = "#btn" + i + 2;
			var btn3 = "#btn" + i + 3;
			var iidId = "#iid" + i;
			
			var unitId = "#unit" + i;
			var totalId = "#total" + i;	
			var totalsId = "#totals";
			
			
			$(btn1).on('click', () => {
//				console.log("btn 1 has been clicked.");
				var quantityId = "#quantity" + i;
//				console.log("quantity id: " + quantityId);
				var c = $(quantityId).val();
//				console.log("c: " + c);
				console.log("hidden input: " + $(iidId).val());
				
				
//				$.get('update_cart?iid=' + $(iidId).val(), (resp) => {
//					console.log("update cart is called.");
//					console.log(resp);
//				})
				
				if (c > 1) {
					$(quantityId).val(c - 1);
					
					let unitPrice = $(unitId).text().substring(1);
					console.log("unit price: " + unitPrice);
					
					let totalPrice = $(totalId).text().substring(1);
					console.log("total price: " + totalPrice);

					
					let newTotal = parseFloat(totalPrice) - parseFloat(unitPrice);
					$(totalId).html('$' + newTotal);
					
					let totalsPrice = $(totalsId).text().substring(1);
					console.log("totals price: " + totalsPrice);
					
					let newTotals = parseFloat(totalsPrice) - parseFloat(unitPrice);
					$(totalsId).html('$' + newTotals);
					
					$.get('decrease_item?iid=' + $(iidId).val(), () => {
						console.log("item has been decreased.");
					})
				}
				
			})
			$(btn2).on('click', () => {
//				console.log("btn 2 has been clicked.");
				
				$.get('increase_item?iid=' + $(iidId).val(), (resp) => {
					console.log("resp: " + resp);
					if (resp === "T") {
						var quantityId = "#quantity" + i;
						var c = $(quantityId).val();
						$(quantityId).val(parseInt(c) + 1);
						
						let unitPrice = $(unitId).text().substring(1);
						console.log("unit price: " + unitPrice);
						
						let totalPrice = $(totalId).text().substring(1);
						console.log("total price: " + totalPrice);

						
						let newTotal = parseFloat(totalPrice) + parseFloat(unitPrice);
						$(totalId).html('$' + newTotal);				

						let totalsPrice = $(totalsId).text().substring(1);
						console.log("totals price: " + totalsPrice);
						
						let newTotals = parseFloat(totalsPrice) + parseFloat(unitPrice);
						$(totalsId).html('$' + newTotals);							
					}
					else {
						alert("Out of stock, you can't add this type any more");
					}
					
				})				
				
			
				

				
			})		
			$(btn3).on('click', () => {
//				console.log("btn 3 has been clicked.");
				$.get('delete_item?iid=' + $(iidId).val(), () => {
					console.log("item has been deleted.");
				})
				
				let totalPrice = $(totalId).text().substring(1);
				console.log("total price: " + totalPrice);
				
				let totalsPrice = $(totalsId).text().substring(1);
				console.log("totals price: " + totalsPrice);				
				
				let newTotals = parseFloat(totalsPrice) - parseFloat(totalPrice);
				$(totalsId).html('$' + newTotals);					
				
				var trId = "#tr" + i;
				$(trId).remove();
				
				var itemsNum = ".items-num";
				let nums = $(itemsNum).first().text();
				console.log("nums: " + nums);
				$(itemsNum).html(parseInt(nums) - 1);
				
				
			})			
		}(i))
		
		
		// TODO: Figure out a reason why and find a solution.
//		let btn1 = "#btn" + i + 1;
//		let btn2 = "#btn" + i + 2;
//		let btn3 = "#btn" + i + 3;
//		$(btn1).on('click', () => {
////			console.log("btn " + i + 1 +  " has been clicked.");
//			let quantityId = "#quantity" + i;
////			console.log("quantity id: " + quantityId);
//			let c = $(quantityId).val();
////			console.log("c: " + c);
//			if (c >= 1) {
//				$(quantityId).val(c - 1);
//			}
//			
//		})
//		$(btn2).on('click', () => {
//			console.log("btn 2 has been clicked.");
//			let quantityId = "#quantity" + i;
//			let c = $(quantityId).val();
//			$(quantityId).val(parseInt(c) + 1);			
//			
//		})		
//		$(btn3).on('click', () => {
//			console.log("btn 3 has been clicked.");
//			let trId = "#tr" + i;
//			$(trId).remove();			
//			
//		})		
		
		
		
	}
	

});


