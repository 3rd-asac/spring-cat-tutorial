function callCat(){
    var limit = document.getElementById('limit');
    window.location = "/Image/cat/" + limit.value;
}

function callFavouritesCat(){
    var sub_id = document.getElementById('sub_id')
    if(sub_id.value == ""){
        alert("저장 아이디를 입력해주세요.");
    }else {
        window.location = "/Favourites/" + sub_id.value;
    }
}

function collection_Cat(){
    var count = 0;  //  체크된 체크박스의 갯수를 카운트 하기위한 변수
    var sub_id = document.getElementById('sub_id');
    if(document.frm.image_id.length==undefined){   // 체크박스가 하나일때
        if( document.frm.image_id.checked == true)   // 그 체크박스만 체크되어 있는지 확인
            count++;
    }else{
        for( var i=0; i<document.frm.image_id.length; i++){
            if( document.frm.image_id[i].checked==true)
                count++;
        }
    }
    // 지금의 스크립트 명령은 체크박스가 하나도 체크되지 않았다면 원래로 되돌아 가기위한 코드들입니다
    if( count == 0 ){
        alert("추가할 항목을 선택해주세요");
    }else if(sub_id.value == ""){
        alert("저장 아이디를 입력해주세요.");
    }else{
        document.frm.method = 'POST';
        document.frm.action = "/collection/cat/" + sub_id.value;
        document.frm.submit();
    }
}

function DeleteFavouritesCat(){
    var count = 0;  //  체크된 체크박스의 갯수를 카운트 하기위한 변수
    if(document.frm.image_id.length==undefined){   // 체크박스가 하나일때
        if( document.frm.image_id.checked == true)   // 그 체크박스만 체크되어 있는지 확인
            count++;
    }else{
        for( var i=0; i<document.frm.image_id.length; i++){
            if( document.frm.image_id[i].checked==true)
                count++;
        }
    }
    // 지금의 스크립트 명령은 체크박스가 하나도 체크되지 않았다면 원래로 되돌아 가기위한 코드들입니다
    if( count == 0 ){
        alert("추가할 항목을 선택해주세요");
    }else{
        document.frm.method = 'POST';
        document.frm.action = "/Delete/cat";
        document.frm.submit();
    }
}