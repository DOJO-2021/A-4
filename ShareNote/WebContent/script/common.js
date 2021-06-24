'use strict';



//新規登録画面

function onRegist(){

		var nickname = document.inform.nickname.value.trim();
		var password = document.inform.password.value.trim();
		var passconf = document.inform.password_check.value.trim();
		var answer = document.inform.answer.value.trim();
		//エラーメッセージを表示する場所の情報を取得
		var target = document.getElementById("errMsg");

		if(nickname=="" || password=="" || passconf=="" || answer==""){
			target.innerHTML = "入力されていない項目があります";
			return false;
		}else{

			if(window.confirm("この内容で登録してよろしいですか？")){

				//OKのときの処理
				return true;

			}else{
				//キャンセルの時の処理
				return false;
			}
		}


};


//編集削除

function onDelete(){


			if(window.confirm("本当に削除しますか？")){

				//OKのときの処理
				return true;

			}else{
				//キャンセルの時の処理
				return false;
			}


};


