<template>
  <div class="row">
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
      <div class="regist">
        <!-- <h1 class="underline">Hotplace 작성</h1> -->
        <div class="regist_form">
          <label for="name"><strong>제목</strong></label>
          <input
            style="width: 100%"
            type="text"
            id="name"
            ref="name"
            v-model="data.name"
          /><br />

          <label for="img"><strong>사진</strong></label>
          <input type="file" id="img" multiple="multiple" ref="img" />

          <label for="title"><strong>장소</strong></label>
          <input
            readonly
            style="width: 100%"
            type="text"
            id="data_num"
            ref="data_num"
            v-model="data.data_num"
          /><br />

          <label for="content"><strong>설명</strong></label>
          <br />
          <textarea
            id="content"
            ref="content"
            cols="35"
            rows="5"
            v-model="data.content"
          ></textarea
          ><br />

          <label for="title"><strong>방문 시간</strong></label>
          <input
            type="date"
            name="visitTime"
            ref="visitTime"
            id="visitTime"
            max="2023-12-31"
            min="2000-01-01"
            value="2023-05-12"
            v-model="data.visitTime"
          />

          <div
            style="padding-top: 15px"
            class="col-6 offset-6 d-flex justify-content-end"
          >
            <b-button
              @click="checkValue()"
              class="btn"
              variant="outline-dark"
              style="width: 20%"
              >등록</b-button
            >
            <b-button
              @click="$router.push('/hotplace/list')"
              class="btn"
              variant="outline-dark"
              style="width: 20%"
              >목록</b-button
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "HotplaceWrite",
  data() {
    return {
      map: null,
      placesService: null,
      markers: [],

      searchInput: "",
      geocoder: null,

      data: {
        name: "",
        data_num: "",
        visitTime: "",
        content: "",
        userId: "",
      },
    };
  },
  created() {
    // 현재 세션 스토리지에서 "vuex" 키의 값을 가져오기
    const vuexData = sessionStorage.getItem("vuex");
    const userData = JSON.parse(vuexData);
    // userInfo의 id 값 가져오기
    this.data.userId = userData.memberStore.userInfo.id;
  },
  mounted() {
    const googleMapsScript = document.createElement("script");
    googleMapsScript.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyAh8dfzpQhMLMSLpfz9Lo6WZc750-hs7f4&libraries=places&callback=initMap`;
    googleMapsScript.defer = true;
    window.initMap = this.initMap;
    document.head.appendChild(googleMapsScript);
  },
  methods: {
    initMap() {
      this.map = new window.google.maps.Map(document.getElementById("map"), {
        center: { lat: 47.6062, lng: -122.332 },
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
                    this.data.data_num = results[1].vicinity;
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
                this.data.data_num = results[1].vicinity;
                console.log(results);
              }
            }
          }
        );
      });
    },
    // 입력값 체크하기
    checkValue() {
      // 사용자 입력값 체크하기
      let err = true;
      let msg = "";
      !this.data.userId &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.data.name &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        this.$refs.img.files.length == 0 &&
        ((msg = "파일 입력해주세요"), (err = false), this.$refs.img.focus());
      err &&
        !this.data.data_num &&
        ((msg = "장소명 입력해주세요"),
        (err = false),
        this.$refs.data_num.focus());
      err &&
        !this.data.content &&
        ((msg = "설명 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      err &&
        !this.data.visitTime &&
        ((msg = "방문시간 입력해주세요"),
        (err = false),
        this.$refs.visitTime.focus());

      if (!err) alert(msg);
      else this.submitForm();
    },

    submitForm() {
      let formData = new FormData();
      formData.append("userId", this.data.userId);
      formData.append("name", this.$refs.name.value);
      formData.append("data_num", this.$refs.data_num.value);
      formData.append("content", this.$refs.content.value);
      formData.append("visitTime", this.$refs.visitTime.value);
      let imgFiles = this.$refs.img.files;
      for (let i = 0; i < imgFiles.length; i++) {
        formData.append("files", imgFiles[i]);
        console.log(imgFiles[i]);
      }

      axios({
        method: "post",
        url: "http://localhost:8080/hotplace/",
        data: formData,
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
.map-container {
  margin-top: 50px !important; /* 원하는 마진 값으로 조정 */
}
.regist_form input,
.regist_form textarea {
  margin-bottom: 10px !important;
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
