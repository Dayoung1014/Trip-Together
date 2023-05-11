<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
	<%@ include file="/WEB-INF/views/common/confirm.jsp" %> 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <!-- HTML link 태그를 활용한 BootStrap 연동 -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap"
      rel="stylesheet"
    />
    
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9be9023e327b2659f382dfe6b17b0972&libraries=services,clusterer,drawing"
    ></script>
    <script src="https://kit.fontawesome.com/64df31442a.js" crossorigin="anonymous"></script>
    
    <style>
      /* 캐러셀(이미지슬라이드) 이미지 크기변경 */
      .carousel-inner {
        width: 100%;
        height: 400px;
        /* 이미지 높이 변경 */
      }

      .carousel-item {
        width: 100%;
        height: 100%;
      }

      .d-block {
        display: block;
        width: 100%;
        height: 100%;
      }

      body {
        font-family: "Gowun Dodum", sans-serif;
      }

      .wrap {
        position: absolute;
        left: 0;
        bottom: 40px;
        width: 288px;
        height: 132px;
        margin-left: -144px;
        text-align: left;
        overflow: hidden;
        font-size: 12px;
        font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
        line-height: 1.5;
      }
      .wrap * {
        padding: 0;
        margin: 0;
      }
      .wrap .info {
        width: 286px;
        height: 120px;
        border-radius: 5px;
        border-bottom: 2px solid #ccc;
        border-right: 1px solid #ccc;
        overflow: hidden;
        background: #fff;
      }
      .wrap .info:nth-child(1) {
        border: 0;
        box-shadow: 0px 1px 2px #888;
      }
      .info .title {
        padding: 5px 0 0 10px;
        height: 30px;
        background: #eee;
        border-bottom: 1px solid #ddd;
        font-size: 18px;
        font-weight: bold;
      }
      .info .close {
        position: absolute;
        top: 10px;
        right: 10px;
        color: #888;
        width: 17px;
        height: 17px;
        background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
      }
      .info .close:hover {
        cursor: pointer;
      }
      .info .body {
        position: relative;
        overflow: hidden;
      }
      .info .desc {
        position: relative;
        margin: 13px 0 0 90px;
        height: 75px;
      }
      .desc .ellipsis {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
      .desc .jibun {
        font-size: 11px;
        color: #888;
        margin-top: -2px;
      }
      .info .img {
        position: absolute;
        top: 6px;
        left: 5px;
        width: 73px;
        height: 71px;
        border: 1px solid #ddd;
        color: #888;
        overflow: hidden;
      }
      .info:after {
        content: "";
        position: absolute;
        margin-left: -12px;
        left: 50%;
        bottom: 0;
        width: 22px;
        height: 12px;
        background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
      }
      .info .link {
        color: #5085bb;
      }
    </style>
  </head>

  <body>
    <!-- BootStrap Navigation Bar Sample -->
    <%-- <%@ include file="/common/confirm.jsp" %> --%>

    <div class="container text-center">
      <!--원영 시작-->
      <!-- 관광지 검색 start -->
      <div id="search-top-label" class="fs-1">핫플 수정하기</div>
 
      <!-- 관광지 검색 end -->
      <div class="d-flex justify-content-center"> 
      <div class="col-md-6 p-3">
        <div id="map" style="width: 100%; height: 500px"></div>
       </div>
       	 
            <div class="col-md-6 p-3" style="text-align: left;">
              <article>
                <div class="container" role="main">
                  <form name="form-write" id="form-write" role="form" method="Post"> 
                  	<input type="text" class="form-control" name="userId" id="userId" value=${userinfo.id} hidden>
                    <div class="mb-3">
                      <label for="title"><strong>제목</strong></label>
                      <input type="text" class="form-control" name="name" id="name" placeholder="제목을 입력해 주세요" value="${hotplace.name}">
                    </div>

                    <div class="mb-3">
                      <label for="title"><strong>시도</strong></label>
                      <select id="search-area-sel" class="form-select" onchange="changeAreaSel()">
              			<option value="-1" selected>지역선택</option>
            		</select>
                    </div>
                     
                   <div class="mb-3">
                      <label for="title"><strong>구군</strong></label>
                      <select id="search-area-detail-sel" class="form-select" onchange="changeSigunguSel()" >
			              <option value="-1" selected>구군선택</option>
			            </select> 
                    </div>
                    
                    <div class="mb-3">
                      <label for="title"><strong>장소명</strong></label>
                      <input type="text" class="form-control" name="place_name" id="place_name" placeholder="하단에서 선택" readonly>
                   	  <input type="text" class="form-control" name="dataNum" id="dataNum" hidden>
                     </div>
                    
                    <div class="mb-3">
                      <label for="content"><strong>설명</strong></label>
                      <textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요" value="${hotplace.content}"></textarea>
                    </div>
                    
                    <div class="mb-3">
                      <label for="title"><strong>방문 시간</strong></label>
                        <input type="date" name="visitTime" id="visitTime" max="2077-06-20" min="2077-06-05" value="${hotplace.visitTime}">
					</div>

<!--                     <div class="mb-3">
                      <div class="form-group row">
                        <label for="inputFile" class="col-form-label"><strong>사진</strong></label>
                        <div class="col-sm-10">
                          <div class="custom-file" id="inputFile"> 
                          	<input type="file" class="form-control border" id="upfile" name="upfile" multiple="multiple">
                          </div>
                        </div>
                      </div>
                    </div> -->
                    
                  </form>

                  <div style="text-align: right;">
                    <button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
                  </div>
                </div>
              </article>
            </div>

      </div>
      <div
        id="card-panel"
        class="search-cards m-4 row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4 d-flex"
      >
        <!--카드 시작-->
        <!--카드 끝-->
      </div>

      <!--모달 시작-->
      <div class="modal fade" id="trip-info-modal" tabindex="-1">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Modal title</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <p>Modal body text goes here.</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
          </div>
        </div>
      </div>
      <!--모달 끝-->
    </div>

    <svg xmlns="http://www.w3.org/2000/svg" style="display: none">
      <symbol id="bootstrap" viewBox="0 0 118 94">
        <title>Bootstrap</title>
        <path
          fill-rule="evenodd"
          clip-rule="evenodd"
          d="M24.509 0c-6.733 0-11.715 5.893-11.492 12.284.214 6.14-.064 14.092-2.066 20.577C8.943 39.365 5.547 43.485 0 44.014v5.972c5.547.529 8.943 4.649 10.951 11.153 2.002 6.485 2.28 14.437 2.066 20.577C12.794 88.106 17.776 94 24.51 94H93.5c6.733 0 11.714-5.893 11.491-12.284-.214-6.14.064-14.092 2.066-20.577 2.009-6.504 5.396-10.624 10.943-11.153v-5.972c-5.547-.529-8.934-4.649-10.943-11.153-2.002-6.484-2.28-14.437-2.066-20.577C105.214 5.894 100.233 0 93.5 0H24.508zM80 57.863C80 66.663 73.436 72 62.543 72H44a2 2 0 01-2-2V24a2 2 0 012-2h18.437c9.083 0 15.044 4.92 15.044 12.474 0 5.302-4.01 10.049-9.119 10.88v.277C75.317 46.394 80 51.21 80 57.863zM60.521 28.34H49.948v14.934h8.905c6.884 0 10.68-2.772 10.68-7.727 0-4.643-3.264-7.207-9.012-7.207zM49.948 49.2v16.458H60.91c7.167 0 10.964-2.876 10.964-8.281 0-5.406-3.903-8.178-11.425-8.178H49.948z"
        ></path>
      </symbol>
      <symbol id="facebook" viewBox="0 0 16 16">
        <path
          d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z"
        />
      </symbol>
      <symbol id="instagram" viewBox="0 0 16 16">
        <path
          d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"
        />
      </symbol>
      <symbol id="twitter" viewBox="0 0 16 16">
        <path
          d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"
        />
      </symbol>
    </svg>


<script>  
document.querySelector("#btnSave").addEventListener("click", function () {
    if (!document.querySelector("#name").value) {
      alert("제목 입력!!");
      return;
    } else if (!document.querySelector("#content").value) {
      alert("내용 입력!!");
      return;
    } else if (!document.querySelector("#dataNum").value) {
        alert("장소 입력!!");
        return;
      } else if (!document.querySelector("#visitTime").value) {
        alert("방문 일자 입력!!");
        return;
      } else {
      let form = document.querySelector("#form-write");
      form.setAttribute("action", "${root}/hotplace/writeHotplace");
      form.submit();
    }
  });
</script>

<script>
  document.getElementById('visitTime').value = new Date().toISOString().substring(0, 10);
</script>

<script type="text/javascript">

let areaUrl = "/attraction/getSido";
var currAreaNum = -1;
var currSigunguNum = -1;
var types = [12, 14, 15, 25, 28, 32, 38, 39];
var originTypeNums = [12, 14, 15, 25, 28, 32, 38, 39];
var currResults = [];

window.onload = init();
function init() { 
    fetchAllAreas(areaUrl); 
}

function fetchAllAreas(areaUrl) {
    fetch(areaUrl)
  .then((response) => response.json())
  .then((data) => makeOption(data));
}

function makeOption(data) {
    let areas = data; //.response.body.items.item;
    let sel = document.getElementById("search-area-sel");
    areas.forEach(function (area) {
    let opt = document.createElement("option");
        opt.setAttribute("value", area.sidoCode);
        opt.appendChild(document.createTextNode(area.sidoName));
        sel.appendChild(opt);
    });
}

function changeAreaSel() {
    let detailSel = document.getElementById("search-area-detail-sel");
    let cnt = detailSel.options.length;
    for (i = 0; i < cnt; i++) {
        detailSel.options[0].remove();
    }

    var currSel = document.getElementById("search-area-sel")
    currAreaNum = currSel.options[currSel.selectedIndex].value;
    currSigunguNum = -1;
    //console.log(currAreaNum);
    
    let areaDetailUrl = "/attraction/getGugun/" + currAreaNum;
    fetch(areaDetailUrl)
        .then((response) => response.json())
        .then((data) => makeDetailOption(data));
} 

function makeDetailOption(data) {
    let areas = data; //.response.body.items.item;
    let sel = document.getElementById("search-area-detail-sel");
	//console.log(areas);
    areas.forEach(function (area) {
    let opt = document.createElement("option");
        opt.setAttribute("value", area.gugunCode);
        opt.appendChild(document.createTextNode(area.gugunName));
        sel.appendChild(opt);
    });
}

function changeSigunguSel() {
    var currSel = document.getElementById("search-area-detail-sel")
    currSigunguNum = currSel.options[currSel.selectedIndex].value;
    getTrips();
}

function getTrips() {
    currResults = [];
    for (i = 0; i < types.length; i++) {
        let tripsUrl = "/attraction/getTrip/" + types[i] + "/" + currAreaNum + "/" + currSigunguNum;
       	console.log(tripsUrl);
        fetch(tripsUrl)
            .then((response) => response.json())
            .then((data) => getResults(data));
    }
}

function getResults(result) {
    if (!result) return;
    var cardPanel = document.getElementById("card-panel");

    let originCnt = cardPanel.children.length;
    for (i = 0; i < originCnt; i++){
        cardPanel.children[0].remove();
    }
    
    var tmp = result; //.response.body.items.item;
    for (i = 0; i < tmp.length; i++) {
        excute(tmp[i]);
    }
}

function excute(currResult) {
    addCard(currResult);
    addMarker(currResult);
}




function addCard(result) {
    var cardPanel = document.getElementById("card-panel");

        var titleEl = document.createElement("h5");
        titleEl.setAttribute("class", "card-title");  
        titleEl.appendChild(document.createTextNode(result.title));

        var pEl = document.createElement("p");
        pEl.setAttribute("class", "card-text");
        pEl.appendChild(document.createTextNode(result.addr1));

        var buttonEl = document.createElement("button");
        buttonEl.setAttribute("type", "button");
        buttonEl.setAttribute("class", "btn btn-primary"); 
        buttonEl.setAttribute("id", "btn-add"); 
        buttonEl.setAttribute("name", "btn-add");
        buttonEl.setAttribute("value", result.contentId);
        console.log(result);
        buttonEl.setAttribute("onclick", "addFunc(this)");
        buttonEl.appendChild(document.createTextNode("추가하기"));

        var cardBodyEl = document.createElement("div");
        cardBodyEl.setAttribute("class", "card-body");
        cardBodyEl.appendChild(titleEl);
        cardBodyEl.appendChild(pEl);
        cardBodyEl.appendChild(buttonEl);

        var imgEl = document.createElement("img");
        imgEl.setAttribute("src", result.firstImage);
        imgEl.setAttribute("class", "card-img-top");

        var cardEl = document.createElement("div");
        cardEl.setAttribute("class", "card m-2");
        cardEl.setAttribute("style", "width: 18rem");
        cardEl.appendChild(imgEl);
        cardEl.appendChild(cardBodyEl);

        var colEl = document.createElement("div");
        colEl.setAttribute("class", "col");
    colEl.appendChild(cardEl);
    
    cardPanel.appendChild(colEl);
}

function addFunc(result)  {
		let res;
		let areaDetailUrl = "/attraction/getAttraction/" + result.value;
	    fetch(areaDetailUrl)
	        .then((response) => response.json())
	        .then((data) => setAttraction(data));  
} 

function setAttraction(data) {
    let area = data; //.response.body.items.item;
    console.log(area);
    document.getElementById("place_name").value = area.title;
	document.getElementById("dataNum").value = area.contentId;
}


function addMarker(result) {
    //console.log(result);
    var place = {
        title: result.title,
        addr1: result.addr1,
        firstimage: result.firstImage,
        y: result.latitude,
        x: result.longitude,
    }
    //console.log(place.firstimage);
    displayMarker(place);
}

var content =
    `<div class="wrap">
        <div class="info">
            <div class="title"> 
                ${place.title}
            </div>
            <div class="body">
                <div class="img">
                    <img src=${place.firstimage} width="73" height="70">
               </div>
                <div class="desc">
                    <div class="ellipsis">${place.addr1}</div>
                </div>
            </div>
        </div>
    </div>`;
          

	var mapContainer = document.getElementById("map"), // 지도의 중심좌표 
      mapOption = {
        // {36.35536, 127.298294 }
        center: new kakao.maps.LatLng(36.35536, 127.298294), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다


    var overlay = new daum.maps.CustomOverlay({
    	map : map,
      	position: mapOption.latlng,
      	content: content,
    });
    
    function overLay(marker, tmp) {
      overlay = new kakao.maps.CustomOverlay({
        content: tmp,
        map: map,
        position: marker.getPosition(),
      });
    }

    // 지도에 마커를 표시합니다
    function displayMarker(place) { 
    	
      // 마커를 생성하고 지도에 표시합니다
      var marker = new kakao.maps.Marker({
          map: map,
          position: new kakao.maps.LatLng(place.y, place.x),
       });


        overlay = new kakao.maps.CustomOverlay({
	        map : map,
	        //content: content,
	        position: marker.getPosition(),
        });
        
      kakao.maps.event.addListener(marker, "click", function () {
        overlay.setMap(null);
        overLay(marker, content);
      });
      map.setCenter(new kakao.maps.LatLng(place.y, place.x));
      
    } 
    
/*     document.querySelector("#btn-add").addEventListener("click", function () {
    	console.log("click");
    }); */
    


	</script>
   </body>
</html>