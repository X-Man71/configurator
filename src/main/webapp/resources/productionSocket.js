	var stompClient = null;

	function setConnected(connected) {
		document.getElementById('connect123').disabled = connected;
		document.getElementById('disconnect').disabled = !connected;
		document.getElementById('conversationDiv').style.visibility = connected ? 'visible'
				: 'hidden';
		document.getElementById('response').innerHTML = '';
	}

	function connect123() {
		var socket = new SockJS('/hello');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/greetings', function(orderPosition) {

				var arr = JSON.parse(orderPosition.body);

				showGreeting(arr);

			});
		});
	}

	function disconnect() {
		stompClient.disconnect();
		setConnected(false);
		console.log("Disconnected");
	}

	function sendName() {
		var name = document.getElementById('name').value;
		stompClient.send("/app/hello", {}, JSON.stringify({
			'name' : name
		}));
	}

	function showGreeting(arr) {

		var arrayLength = arr.length;
		
		  for (var i = 0; i < arrayLength; i++) 
          {
		
			var productname = arr[i].productname;
			var size = arr[i].size;
			var username = arr[i].username;
			var comment = arr[i].comment;
			var createdDateString = arr[i].createdDateString;
			var registered = arr[i].registered;
			var provided = arr[i].provided;
			var done = arr[i].done;

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

			addTableRow();
		 }

	}

	function addTableRow() {

		var oldParent = document.getElementById('row');

		document.getElementById("nextRow").removeAttribute("id");

		var table = document.getElementById("body");
		var row = table.insertRow(0);

		var att = document.createAttribute("id");
		att.value = "nextRow";
		row.setAttributeNode(att);

		var newParent = document.getElementById('nextRow');

		newParent.innerHTML = oldParent.innerHTML;

	}