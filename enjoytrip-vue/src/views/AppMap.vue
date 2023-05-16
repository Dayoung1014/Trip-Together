<template>
  <div>
    <form class="d-flex my-3" onsubmit="return false;" role="search">
      <select
        id="search-area"
        class="form-select me-2"
        @change="showValue"
        ref="searcharea"
      >
        <option value="0" selected>지역</option>
        <option v-for="i in sido" :key="i.sidoCode" :value="i.sidoCode">
          {{ i.sidoName }}
        </option>
      </select>
      <select id="search-town" class="form-select me-2" ref="searchtown">
        <option v-for="i in gugun" :key="i.gugunCode" :value="i.gugunCode">
          {{ i.gugunName }}
        </option>
      </select>
      <select
        id="search-content-id"
        class="form-select me-2"
        ref="searchcontentid"
      >
        <option value="0" selected>관광지 유형</option>
        <option value="12">관광지</option>
        <option value="14">문화시설</option>
        <option value="15">축제공연행사</option>
        <option value="25">여행코스</option>
        <option value="28">레포츠</option>
        <option value="32">숙박</option>
        <option value="38">쇼핑</option>
        <option value="39">음식점</option>
      </select>

      <button
        id="btn-search"
        class="btn btn-outline-success"
        type="button"
        @click="search"
      >
        검색
      </button>
    </form>

    <div id="map" class="mt-3" style="width: 100%; height: 600px"></div>

    <b-modal id="modal-1" title="BootstrapVue">
      <p class="my-4">Hello from modal!</p>
    </b-modal>

    <div class="row" style="font-family: 'Black Han Sans', sans-serif">
      <!-- 관광지 카드 -->
      <b-card
        class="m-3"
        v-for="(card, i) in trips"
        :key="i"
        no-body
        style="max-width: 20rem"
        :img-src="card.firstImage"
        img-alt="Image"
        img-top
      >
        <template #header>
          <h4 class="mb-0">{{ card.title }}</h4>
        </template>

        <b-card-body>
          <b-card-text> 내용 </b-card-text>
        </b-card-body>

        <b-list-group flush>
          <b-list-group-item>{{ card.addr1 }}</b-list-group-item>
          <b-list-group-item>{{ card.tel }}</b-list-group-item>
          <b-list-group-item>Vestibulum at eros</b-list-group-item>
        </b-list-group>

        <b-card-body>
          <a href="#" class="card-link">Card link</a>
          <a href="#" class="card-link">Another link</a>
        </b-card-body>
      </b-card>
      <!-- 관광지 카드 끝 -->
    </div>
    <!-- 관광지 검색 end -->
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AppMap",
  components: {},

  data() {
    return {
      map: null,
      sido: [],
      gugun: [],
      trips: [],
      positions: [],
      markers: [],
    };
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      // 카카오 객체가 있고, 카카오 맵그릴 준비가 되어 있다면 맵 실행
      this.loadMap();
    } else {
      // 없다면 카카오 스크립트 추가 후 맵 실행
      this.loadScript();
    }
  },
  methods: {
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=f5f2b222929185c8e4e290245b9f818e&autoload=false"; // &autoload=false api를 로드한 후 맵을 그리는 함수가 실행되도록 구현
      script.onload = () => window.kakao.maps.load(this.loadMap); // 스크립트 로드가 끝나면 지도를 실행될 준비가 되어 있다면 지도가 실행되도록 구현

      document.head.appendChild(script); // html>head 안에 스크립트 소스를 추가
    },

    loadMap() {
      const container = document.getElementById("map"); // 지도를 담을 DOM 영역
      const options = {
        // 지도를 생성할 때 필요한 기본 옵션
        center: new window.kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3, // 지도의 레벨(확대, 축소 정도)
      };

      this.map = new window.kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
    },

    showValue(e) {
      let code = e.target.value;
      console.log(e.target.value);
      axios({
        method: "get",
        url: `http://localhost:8080/attraction/getGugun/${code}`,
        responseType: "json",
      })
        .then((data) => {
          this.gugun = data.data;
          console.log(this.gugun);
        })
        .catch((error) => console.log(error));
    },

    removeMarkers() {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];
    },

    search() {
      this.removeMarkers();
      let areaCode = this.$refs.searcharea.value;
      let townCode = this.$refs.searchtown.value;
      let contentTypeId = this.$refs.searchcontentid.value;
      console.log(areaCode, townCode, contentTypeId);
      axios
        .get(
          `http://localhost:8080/attraction/getTrip/${contentTypeId}/${areaCode}/${townCode}`
        )
        .then((response) => {
          this.trips = response.data;
          console.log(this.trips);
          //this.$refs.table.style = "display: ;";
          console.log(this.$refs.table, "dsf");
          this.positions = [];
          this.markers = [];
          this.trips.forEach((area) => {
            let markerInfo = {
              title: area.title,
              latlng: new window.kakao.maps.LatLng(
                area.latitude,
                area.longitude
              ), // 마커 위도, 경도
            };
            this.positions.push(markerInfo);
          });
          for (var i = 0; i < this.markers.length; i++) {
            this.markers[i].setMap(null);
          }
          var imageSrc =
            "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; // 관광지
          for (i = 0; i < this.positions.length; i++) {
            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new window.kakao.maps.Size(24, 35);

            // 마커 이미지를 생성합니다
            var markerImage = new window.kakao.maps.MarkerImage(
              imageSrc,
              imageSize
            );

            // 마커를 생성합니다
            var marker = new window.kakao.maps.Marker({
              map: this.map, // 마커를 표시할 지도
              position: this.positions[i].latlng, // 마커를 표시할 위치
              title: this.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
              image: markerImage, // 마커 이미지
              clickable: true, // 마커 클릭을 가능하게 함
            });

            this.markers[i] = marker;

            // 마커에 클릭이벤트를 등록합니다
            window.kakao.maps.event.addListener(marker, "click", function () {
              // 해당 관광지의 이름 출력
              alert(this.Gb);
            });
          }
          // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
          this.map.setCenter(this.positions[0].latlng);
        });
    },
  },

  created() {
    console.log("fdsfds");
    axios({
      method: "get",
      url: "http://localhost:8080/attraction/getSido/",
      responseType: "json",
    })
      .then((data) => {
        this.sido = data.data;
        console.log(this.sido);
      })
      .catch((error) => console.log(error));
  },
};
</script>

<style></style>
