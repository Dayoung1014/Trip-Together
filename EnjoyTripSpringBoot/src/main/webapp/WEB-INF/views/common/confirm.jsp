<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%
String root = request.getContextPath();
%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<c:if test="${not empty userinfo}">
	<header class="p-3 text-white">
      <div class="container-fluid">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
          <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0 align-items-center">
            <!--<img src="/wyimg/grean-obj-3.png" alt="">-->
            <li>
              <a href="/" class="nav-link mx-5 p-3 ps-5 px-2 fs-1" style="color: SeaGreen">Enjoy Trip</a>
            </li>
            <li>
              <a href="${root}/attraction/search" class="nav-link ps-5 px-2 fs-5" style="color: ForestGreen">지역별여행지</a>
            </li>
            <li>
              <a href="<%= root %>/user?action=myroute&userid=${userinfo.id}" class="nav-link ps-3 px-2 fs-5" style="color: SkyBlue">나의여행계획</a>
            </li>
            <li>
              <a href="<%= root %>/article?action=list" class="nav-link ps-3 px-2 fs-5" style="color: Plum">여행 계획 둘러보기</a>
            </li>
            <li>
              <a href="${root}/hotplace/list" class="nav-link ps-3 px-2 fs-5" style="color: Crimson">핫플자랑하기</a>
            </li>

            <li>
              <a href="${root}/board/list?pgno=1&key=&word=" class="nav-link ps-3 px-2 fs-5" style="color: Plum">공지사항</a>
            </li>
          </ul>


          <!--로그인 후-->
         <div class="text-end mx-5" id="header_nav_confirm_off">
         <strong style="color: black;">${userinfo.name}</strong> <a style="color: black;">(${userinfo.id})님 안녕하세요.</a>
            <a
              class="btn fs-5"
              style="color: SeaGreen"
              id="btnlogout"
              href="${root}/user/logout"
            >로그아웃
            </a>
            <button
              type="button"
              class="btn fs-5"
              style="color: CornflowerBlue"
              id="btnmy"
              data-bs-toggle="modal"
              data-bs-target="#mypageModal"
            >내 정보
            </button>
         </div>
    </header>
        <!-- The Modal 내정보-->
    <div class="modal fade" tabindex="-1" role="dialog" id="mypageModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content rounded-4 shadow">
          <div class="modal-header p-5 pb-2 border-bottom-0">
            <!-- <h1 class="modal-title fs-5" >Modal title</h1> -->
            <h1 class="fw-bold mb-0 fs-4">내 정보</h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body p-5 pt-0">
            <form id="form-modify" method="POST" action="" >
              <div class="form-floating mb-3">
                <input type="text" class="form-control rounded-3" name="name" id="name1"  value="${userinfo.name}" />
                <label for="name1">name</label>
              </div>
              <div class="form-floating mb-3">
                <input readonly type="text" class="form-control rounded-3" name="id" id="id1" value="${userinfo.id}" />
                <label for="id1">id</label>
              </div>
              <div class="form-floating mb-3">
                <input type="password" class="form-control rounded-3" name="pwd" id="pwd1"  value="${userinfo.pwd}" />
                <label for="pwd1">password</label>
              </div>
              <div class="form-floating mb-3">
                <input type="password" class="form-control rounded-3" name="pwcheck" id="pwcheck1"  value="${userinfo.pwd}" />
                <label for="pwcheck1">password check</label>
              </div>
              <div class="form-floating mb-3">
                <input type="email" class="form-control rounded-3" name="email" id="email1"  value="${userinfo.email}" />
                <label for="email1">email</label>
              </div>
              <button type="button" id="btn-modify" class="btn btn-outline-primary mb-3">
                정보 수정
              </button>
            </form>
            <a class="w-100 mb-2 btn btn-lg rounded-3 btn-primary" type="submit" id="btn-delete" href="${root}/user/delete?userId=${userinfo.id}">
              회원탈퇴
              </a>
          </div>
        </div>
      </div>
    </div>
    
    <script>
    document.querySelector("#btn-modify").addEventListener("click", function () {
		if (!document.querySelector("#name1").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#id1").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#pwd1").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (document.querySelector("#pwd1").value != document.querySelector("#pwcheck1").value) {
          alert("비밀번호 확인!!");
          return;
        } else {
          let form = document.querySelector("#form-modify");
          form.setAttribute("action", "${root}/user/update");
          form.submit();
        }
	});
    </script>
</c:if>
<c:if test="${empty userinfo}">
 <header class="p-3 text-white">
      <div class="container-fluid">
        <div
          class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
        >
          <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0 align-items-center">
            <!--<img src="/wyimg/grean-obj-3.png" alt="">-->
            <li>
              <a href="/" class="nav-link mx-5 p-3 ps-5 px-2 fs-1" style="color: SeaGreen">Enjoy Trip</a>
            </li>
            <li>
              <a href="#" class="nav-link ps-5 px-2 fs-5" style="color: ForestGreen">지역별여행지</a>
            </li>
            <li>
              <a href="#" class="nav-link ps-3 px-2 fs-5" style="color: SkyBlue">나의여행계획</a>
            </li>
            <li>
              <a href="#" class="nav-link ps-3 px-2 fs-5" style="color: Plum">여행 계획 둘러보기</a>
            </li>
            <li>
              <a href="#" class="nav-link ps-3 px-2 fs-5" style="color: Crimson">핫플자랑하기</a>
            </li>

            <li>
              <a href="#" class="nav-link ps-3 px-2 fs-5" style="color: Plum">공지사항</a>
            </li>
          </ul>

          <!--로그인 전-->
          <div class="text-end mx-5" id="header_nav_confirm_off">
            <button
              type="button"
              class="btn fs-5"
              style="color: SeaGreen"
              id="btnlogin"
              data-bs-toggle="modal"
              data-bs-target="#loginModal"
            >
              로그인
            </button>
            <button
              type="button"
              class="btn fs-5"
              style="color: Plum"
              id="btnsignin"
              data-bs-toggle="modal"
              data-bs-target="#signinModal"
            >
              회원가입
            </button>
          </div>
        </div>
      </div>
    </header>

    <!-- The Modal 로그인-->
    <div class="modal fade" tabindex="-1" role="dialog" id="loginModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content rounded-4 shadow">
          <div class="modal-header p-5 pb-4 border-bottom-0">
            <!-- <h1 class="modal-title fs-5" >Modal title</h1> -->
            <h1 class="fw-bold mb-0 fs-2">로그인</h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body p-5 pt-0">
            <form id="form-login" method="POST" action="<%= root %>/user/login" >
	              <div class="form-floating mb-3">
	                <input type="text" class="form-control rounded-3" name="id" id="inputid" placeholder="Id" />
	                <label for="floatingInput">SSAFY</label>
	              </div>
	              <div class="form-floating mb-3">
	                <input type="password" class="form-control rounded-3" name="pwd" id="inputpwd" placeholder="Password"
	                />
	                <label for="floatingPassword">1234</label>
	              </div>
	              <input type="hidden" name="action" value="login" />
	              <input class="w-100 mb-2 btn btn-lg rounded-3 btn-primary" type="submit" id="btnlogin2" value = "로그인" onclick="javascript:login();" />
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- The Modal 회원가입-->
    <div class="modal fade" tabindex="-1" role="dialog" id="signinModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content rounded-4 shadow">
          <div class="modal-header p-5 pb-2 border-bottom-0">
            <!-- <h1 class="modal-title fs-5" >Modal title</h1> -->
            <h1 class="fw-bold mb-0 fs-4">회원가입</h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          
          <div class="modal-body p-5 pt-0">
          <form id="form-join" method="POST" action="">
            <div class="mb-3">
              <label for="username" class="form-label">이름 : </label>
              <input
                type="text"
                class="form-control"
                id="username"
                name="name"
                placeholder="이름..."
              />
            </div>
            <div class="mb-3">
              <label for="userid" class="form-label">아이디 : </label>
              <input
                type="text"
                class="form-control"
                id="userid"
                name="id"
                placeholder="아이디..."
              />
            </div>
            <div id="idcheck-result"></div>
            <div class="mb-3">
              <label for="userpwd" class="form-label">비밀번호 : </label>
              <input
                type="text"
                class="form-control"
                id="userpwd"
                name="pwd"
                placeholder="비밀번호..."
              />
            </div>
            <div class="mb-3">
              <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
              <input type="text" class="form-control" id="pwdcheck" placeholder="비밀번호확인..." />
            </div>
            <div class="mb-3">
              <label for="emailid" class="form-label">이메일 : </label>
              <div class="input-group">
                <input
                  type="text"
                  class="form-control"
                  id="emailid"
                  name="email"
                  placeholder="이메일아이디"
                />
              </div>
            </div>
            <div class="col-auto text-center">
              <button type="button" id="btn-join" class="btn btn-outline-primary mb-3">
                회원가입
              </button>
              <button type="button" class="btn btn-outline-success mb-3">초기화</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  
  
  <script>
  let isUseId = false;
  document.querySelector("#userid").addEventListener("keyup", function () {
		let userid = this.value;
		console.log(userid);
	 	let resultDiv = document.querySelector("#idcheck-result");
	 	if(userid.length < 6 || userid.length > 16) {
		 	resultDiv.setAttribute("class", "mb-3 text-dark");
		 	resultDiv.textContent = "아이디는 6자 이상 16자 이하 입니다.";
		 	isUseId = false;
	 	} else {
		 	fetch("${root}/user/" + userid)
	   		.then(response => response.text())
	   		.then(data => {
	   			console.log(data);
		 		if(data == 0) {
		   			resultDiv.setAttribute("class", "mb-3 text-primary");
	       			resultDiv.textContent = userid + "는 사용할 수 있습니다.";
	       			isUseId = true;
		 		} else {
		   			resultDiv.setAttribute("class", "mb-3 text-danger");
		       		resultDiv.textContent = userid + "는 사용할 수 없습니다.";
		     		isUseId = false;
		 		}
		   });
	 	}
  }); 
  
  document.querySelector("#btn-join").addEventListener("click", function () {
		if (!document.querySelector("#username").value) {
        alert("이름 입력!!");
        return;
      } else if (!document.querySelector("#userid").value) {
        alert("아이디 입력!!");
        return;
      } else if (!document.querySelector("#userpwd").value) {
        alert("비밀번호 입력!!");
        return;
      } else if (document.querySelector("#userpwd").value != document.querySelector("#pwdcheck").value) {
        alert("비밀번호 확인!!");
        return;
      } else {
        let form = document.querySelector("#form-join");
        form.setAttribute("action", "${root}/user/join");
        form.submit();
      }
	}); 
  </script>
</c:if>

<script>
    
   
    
	 
	</script>