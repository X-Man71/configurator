	var stompClient = null;

	function connectToWebsocket() {
		var socket = new SockJS('/hello');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/greetings', function(orderPosition) 
			{				
				showOrderPosition(JSON.parse(orderPosition.body));
			});
		});
	}

	function disconnect() {
		stompClient.disconnect();
		console.log("Disconnected");
	}

	function showOrderPosition(orderPosition) {
		
			var id = orderPosition.id
			var productname = orderPosition.productname;
			var size = orderPosition.size;
			var username = orderPosition.username;
			var comment = orderPosition.comment;
			var createdDateString = orderPosition.createdDateString;
			var registered = orderPosition.registered;
			var provided = orderPosition.provided;
			var done = orderPosition.done;		
		
			addTableRow(id);
		
		

			var productnameVar = document.getElementById('productname');
			var sizeVar = document.getElementById('size');
			var usernameVar = document.getElementById('username');
			var commentVar = document.getElementById('comment');
			var createdDateStringVar = document.getElementById('createdDateString');
			var registeredVar = document.getElementById('registered');
			var providedVar = document.getElementById('provided');
			var doneVar = document.getElementById('done');

			var tdproductname = document.createElement('td');
			var tdsize = document.createElement('td');
			var tdusername = document.createElement('td');
			var tdcomment = document.createElement('td');
			var tdcreatedDateString = document.createElement('td');
			var tdregistered = document.createElement('td');
			var tdprovided = document.createElement('td');
			var tddone = document.createElement('td');

			tdproductname.appendChild(document.createTextNode(productname));
			tdsize.appendChild(document.createTextNode(size));
			tdusername.appendChild(document.createTextNode(username));
			tdcomment.appendChild(document.createTextNode(comment));
			tdcreatedDateString.appendChild(document.createTextNode(createdDateString));
			tdregistered.appendChild(document.createTextNode(registered));
			tdprovided.appendChild(document.createTextNode(provided));
			tddone.appendChild(document.createTextNode(done));

			productnameVar.appendChild(tdproductname);
			sizeVar.appendChild(tdsize);
			usernameVar.appendChild(tdusername);
			commentVar.appendChild(tdcomment);
			createdDateStringVar.appendChild(tdcreatedDateString);
			registeredVar.appendChild(tdregistered);
			providedVar.appendChild(tdprovided);
			doneVar.appendChild(tddone);
	}

	function addTableRow(id) {

		var oldParent = document.getElementById('template');

		document.getElementById("nextRow").removeAttribute("id");

		var table = document.getElementById("body");
		var row = table.insertRow(0);

		var att = document.createAttribute("id");
		att.value = "nextRow";
		row.setAttributeNode(att);

		var newParent = document.getElementById('nextRow');
		
		newParent.innerHTML = oldParent.innerHTML;
								
		document.getElementById("idDoneFormInput").value = id;		
		
		document.getElementById("idDoneButton").type = "submit";
		

	}