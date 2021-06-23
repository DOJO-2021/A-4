/**
 * 
 */
//var cnt =0;
function valueChange(event){
	//画像を配列に格納する
 	var pics_src = new Array("images/0.png","images/1.png");



	//画像番号用のグローバル変数
	var cnt = document.getElementById("count");

	//画像番号を進める
	  if (cnt == 1) {
		  cnt = 0;

	  }else if (cnt == 0) {
		  cnt = 1;
	  }
	  //画像を切り替える
	  document.getElementById("gazo").src=pics_src[cnt];
	  let note_id = document.getElementById("note_id").value;
	  //alert(note_id);
	$.ajax({
		type:'post',
		url: '/ShareNote/Favorites_button',
		data: {
				note_id : note_id,
				cnt : cnt

			  }
	});
	//alert("owata");

}

let saveCheckbox = document.getElementById('saveCheckbox');
saveCheckbox.addEventListener('change', valueChange);
let msg = document.getElementById('msg');
