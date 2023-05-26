<template>
  <div class="regist row">
    <div class="col-6">
      <div id="map" class="map-container"></div>
      <input
        id="pac-input"
        class="controls"
        type="text"
        placeholder="장소 검색"
      />
    </div>
    <div class="col-6">
      <div class="regist_form">
        <label for="subject"><strong>제목</strong></label>
        <input
          style="width: 100%"
          type="text"
          id="name"
          ref="name"
          v-model="article.name"
        /><br />

        <label for="img"><strong>사진</strong></label
        ><br />

        <div v-for="(article, index) in article_img" :key="article.id">
          <img :src="img_to_src(article)" alt="img" />
          <b-button
            class="close-button"
            @click="imageDelete(article, index)"
            variant="outline-dark"
            style="width: 10%"
            >삭제</b-button
          >
        </div>

        <label for="img"><strong>사진 추가</strong></label
        ><br />
        <input type="file" id="img" multiple="multiple" ref="img" />

        <label for="title"><strong>장소명</strong></label>
        <input
          readonly
          style="width: 100%"
          type="text"
          id="dataNum"
          ref="dataNum"
          v-model="article.dataNum"
        /><br />

        <label for="content"><strong>설명</strong></label>
        <br />
        <textarea
          id="content"
          ref="content"
          cols="35"
          rows="5"
          v-model="article.content"
        ></textarea
        ><br />

        <label for="title"><strong>방문 시간</strong></label>
        <input
          type="date"
          ref="visitTime"
          name="visitTime"
          id="visitTime"
          max="2023-12-31"
          min="2000-01-01"
          value="2023-05-12"
          v-model="article.visitTime"
        />

        <div
          class="col-6 offset-6 d-flex justify-content-end"
          style="padding-top: 15px"
        >
          <b-button
            @click="checkValue"
            variant="outline-dark"
            style="width: 20%"
            >수정</b-button
          >
          <b-button
            @click="$router.push('/hotplace/list')"
            variant="outline-dark"
            style="width: 20%"
            >목록</b-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "HotplaceModify",
  data() {
    return {
      article: {},
      article_img: {},
      delete_img_id: [],

      map: null,
      placesService: null,
      markers: [],
      searchInput: "",
      geocoder: null,

      latitude: "",
      longitude: "",
    };
  },
  async created() {
    this.no = this.$route.params.no;

    axios({
      method: "get",
      url: `http://localhost:8080/hotplace/${this.no}`,
      responseType: "json",
    })
      .then((response) => {
        this.article = response.data.resdata;
        this.article_img = response.data.resImg;
        console.log(this.article);

        // 현재 세션 스토리지에서 "vuex" 키의 값을 가져오기
        const vuexData = sessionStorage.getItem("vuex");
        const userData = JSON.parse(vuexData);
        // userInfo의 id 값 가져오기
        this.userId = userData.memberStore.userInfo.id;
        if (this.userId !== this.article.userId) {
          alert("접근 권한이 없습니다.");
          this.$router.push("/hotplace/list");
          return;
        }

        // for (let i = 0; i < this.article_img.length; i++) {
        //   this.img_src[i] = "http://localhost:8080/upload"+this.article_img[i].path.split('upload')[1] +"/"+this.article_img[i].name ;
        //   console.log(this.img_src[i])
        // }
        //this.getLatLng();
      })
      .catch((error) => console.log(error));
  },
  mounted() {
    window.initMap = this.initMap;
    const googleMapsScript = document.createElement("script");
    googleMapsScript.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyAh8dfzpQhMLMSLpfz9Lo6WZc750-hs7f4&libraries=places&callback=initMap`;
    googleMapsScript.defer = true;
    googleMapsScript.onload = this.getLatLng; // initMap 함수 호출
    document.head.appendChild(googleMapsScript);
  },
  methods: {
    getLatLng() {
      console.log(this.article);
      const geocoder = new window.google.maps.Geocoder();
      geocoder.geocode({ address: this.article.dataNum }, (results, status) => {
        if (
          status === window.google.maps.GeocoderStatus.OK &&
          results.length > 0
        ) {
          this.latitude = results[0].geometry.location.lat();
          this.longitude = results[0].geometry.location.lng();

          this.initMap();
        } else {
          console.error("Failed to geocode address");
          console.log(this.article.dataNum);
        }
      });
    },
    initMap() {
      console.log(this.latitude);
      this.map = new window.google.maps.Map(document.getElementById("map"), {
        center: { lat: this.latitude, lng: this.longitude },
        zoom: 13,
      });

      this.placesService = new window.google.maps.places.PlacesService(
        this.map
      );
      this.geocoder = new window.google.maps.Geocoder();

      const input = document.getElementById("pac-input");
      const searchBox = new window.google.maps.places.SearchBox(input);
      this.map.controls[window.google.maps.ControlPosition.TOP_LEFT].push(
        input
      );

      this.map.addListener("bounds_changed", () => {
        searchBox.setBounds(this.map.getBounds());
      });

      searchBox.addListener("places_changed", () => {
        const places = searchBox.getPlaces();
        if (places.length === 0) {
          return;
        }

        // Remove previous markers
        this.markers.forEach((marker) => {
          marker.setMap(null);
        });
        this.markers = [];

        const bounds = new window.google.maps.LatLngBounds();
        places.forEach((place) => {
          if (!place.geometry || !place.geometry.location) {
            console.log("Returned place contains no geometry");
            return;
          }

          const icon = {
            url: place.icon,
            size: new window.google.maps.Size(71, 71),
            origin: new window.google.maps.Point(0, 0),
            anchor: new window.google.maps.Point(17, 34),
            scaledSize: new window.google.maps.Size(25, 25),
          };

          const marker = new window.google.maps.Marker({
            map: this.map,
            icon,
            title: place.name,
            position: place.geometry.location,
          });

          marker.addListener("click", (event) => {
            // 마커를 클릭했을 때 수행할 동작 작성
            this.placesService.nearbySearch(
              { location: event.latLng, radius: 100 },
              (results, status) => {
                if (
                  status === window.google.maps.places.PlacesServiceStatus.OK
                ) {
                  if (results.length > 0) {
                    this.article.dataNum = results[1].vicinity;
                  }
                }
              }
            );
            console.log("마커 클릭됨");
          });
          this.markers.push(marker);

          if (place.geometry.viewport) {
            bounds.union(place.geometry.viewport);
          } else {
            bounds.extend(place.geometry.location);
          }
        });

        this.map.fitBounds(bounds);
      });

      this.map.addListener("click", (event) => {
        this.placesService.nearbySearch(
          { location: event.latLng, radius: 100 },
          (results, status) => {
            if (status === window.google.maps.places.PlacesServiceStatus.OK) {
              if (results.length > 0) {
                this.article.dataNum = results[1].vicinity;
                console.log(results);
              }
            }
          }
        );
      });
    },
    img_to_src(input) {
      return (
        "http://localhost:8080/upload" +
        input.path.split("upload")[1] +
        "/" +
        input.name
      );
    },
    imageDelete(article, index) {
      console.log(article);
      // 버튼 클릭 이벤트 처리 로직
      this.delete_img_id.push(article.fid);
      this.article_img.splice(index, 1);
      console.log(this.delete_img_id);
    },

    // 입력값 체크하기
    checkValue() {
      console.log(this.article);
      // 사용자 입력값 체크하기
      let err = true;
      let msg = "";

      !this.article.name &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.name.focus());
      // err &&
      //   this.$refs.img.files.length == 0 &&
      //   ((msg ="파일 입력해주세요"), (err = false), this.$refs.img.focus());
      err &&
        !this.article.dataNum &&
        ((msg = "장소명 입력해주세요"),
        (err = false),
        this.$refs.dataNum.focus());
      err &&
        !this.article.content &&
        ((msg = "설명 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      err &&
        !this.article.visitTime &&
        ((msg = "방문시간 입력해주세요"),
        (err = false),
        this.$refs.visitTime.focus());

      if (!err) alert(msg);
      else this.submitForm();
    },

    submitForm() {
      let formData = new FormData();
      formData.append("id", this.article.id);
      formData.append("userId", this.article.userId);
      formData.append("name", this.$refs.name.value);
      formData.append("dataNum", this.$refs.dataNum.value);
      formData.append("content", this.$refs.content.value);
      formData.append("visitTime", this.$refs.visitTime.value);
      let imgFiles = this.$refs.img.files;

      for (let i = 0; i < imgFiles.length; i++) {
        formData.append("files", imgFiles[i]);
      }

      formData.append("delete_img_id", this.delete_img_id);

      console.log(this.article);

      axios({
        method: "put",
        url: "http://localhost:8080/hotplace/",
        data: formData, //this.article,
        headers: { "Content-Type": "multipart/form-data" },
        redirect: "follow",
      });

      setTimeout(() => {
        this.$router.push("/hotplace");
      }, 100);
    },
  },
};
</script>

<style scope>
.regist_form input,
.regist_form textarea {
  margin-bottom: 10px;
}

.regist_form img {
  margin-bottom: 10px;
}

.regist_form label:nth-child(3) {
  margin-top: 20px;
}
#map {
  height: 700px;
  width: 100%;
}

.controls {
  margin-top: 10px;
  border: 1px solid transparent;
  border-radius: 2px 0 0 2px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  height: 32px;
  outline: none;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

#pac-input {
  background-color: #fff;
  padding: 0 11px 0 13px;
  width: 400px;
  font-family: Roboto;
  font-size: 15px;
  font-weight: 300;
  text-overflow: ellipsis;
}
</style>
