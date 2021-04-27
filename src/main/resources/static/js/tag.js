var arrInput = new Array(0);
var arrInputValue = new Array(0);

function addInput() {
		arrInput.push(arrInput.length);
		arrInputValue.push("");
		display();
	}

	function display() {
		document.getElementById('tagdiv').innerHTML = "";
		for (intI = 0; intI < arrInput.length; intI++) {
			document.getElementById('tagdiv').innerHTML += createInput(
					arrInput[intI], arrInputValue[intI]);
		}
	}

	function saveValue(intId, strValue) {
		arrInputValue[intId] = strValue;
	}

	function createInput(id, value) {
		$("#tag-form").show();
		return "<input type='text' style='margin:5px' class='btn btn-outline-pink' placeholder='Tag를 입력해주세요.' name='tag' id='tag"
				+ id
				+ "' onChange='javascript:saveValue("
				+ id
				+ ",this.value)' value='"
				+ value
				+ "' OnKeyDown='CheckEnter(this.form,this)'>";

	}

	function deleteInput() {
		if (arrInput.length > 0) {
			arrInput.pop();
			arrInputValue.pop();
		}
		display();
	}