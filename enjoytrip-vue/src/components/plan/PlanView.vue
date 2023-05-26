<template>
  <div class="regist">
    <div class="regist_form">
      <div class="view text-start" style="border: none; font-size: 40px">
        {{ article.name }}
      </div>
      <div class="view text-start" style="border: none; font-size: 20px">
        <b-icon icon="person-fill" font-scale="1"></b-icon> {{ article.userId }}
        <template v-for="member in member_list">
          , {{ member.userId }}
        </template>
        <b-button
          v-if="this.userId === article.userId"
          class="btn"
          @click="editPlanMember()"
          variant="outline-dark"
          style="width: 20%"
          >Edit Together!</b-button
        >
      </div>

      <div class="row">
        <div
          class="col-6 view d-flex justify-content-start"
          style="border: none; padding-left: 30px; font-size: 20px"
        >
          <b-icon icon="calendar" font-scale="1"></b-icon>
          {{ article.startDate }} ~
          <b-icon icon="calendar" font-scale="1"></b-icon> {{ article.endDate }}
        </div>

        <div style="padding-top: 15px" class="col-6 d-flex justify-content-end">
          <b-button
            v-if="this.userId === article.userId"
            class="btn"
            @click="$router.push('/plan/modify/' + article.id)"
            variant="outline-dark"
            style="width: 10%"
            >수정</b-button
          >
          <b-button
            v-if="this.userId === article.userId"
            class="btn"
            @click="$router.push('/plan/delete/' + article.id)"
            variant="outline-dark"
            style="width: 10%"
            >삭제</b-button
          >
          <b-button
            class="btn"
            @click="$router.push('/plan/list/')"
            variant="outline-dark"
            style="width: 10%"
            >목록</b-button
          >
          <b-button
            v-if="this.userId !== article.userId && !hasUserIdInMemberList"
            class="btn"
            @click="addPlanMember"
            variant="outline-dark"
            style="width: 20%"
            >Trip Together!</b-button
          >
          <b-button
            v-if="this.userId !== article.userId && hasUserIdInMemberList"
            @click="delMember()"
            variant="outline-dark"
            style="width: 30%"
            >여행에서 나가기</b-button
          >
        </div>
      </div>
    </div>

    <div
      class="map"
      style="padding: 10px; border: 1px solid #ccc; border-radius: 1.0rem 1.0rem 1.0rem 1.0rem;%;"
    >
      <div class="row">
        <div class="col-6">
          <div id="map"></div>
          <input
            id="pac-input"
            class="controls"
            type="text"
            placeholder="장소 검색"
          />
        </div>

        <div class="col-6">
          <div class="row">
            <b-button
              pill
              variant="outline-secondary"
              @click="calculateAndDisplayRoute()"
              style="width: 25%"
              >전체 경로 보기</b-button
            >
            <h6 style="width: 30%">
              <b-icon icon="geo-alt" font-scale="1"></b-icon>
              {{ totalDistanceKm }} km
            </h6>
            <h6 style="width: 20%">
              <b-icon icon="clock" font-scale="1"></b-icon>
              {{ totalDurationMin }} min
            </h6>
            <h6 style="width: 20%">
              <b-icon icon="credit-card-fill" font-scale="1"></b-icon>
              {{ totalPrice }} 원
            </h6>
          </div>

          <draggable
            id="list"
            style="overflow: scroll; width: 100%; height: 650px"
            v-model="places"
            @update="handleUpdate"
          >
            <div
              v-for="(place, index) in places"
              :key="index"
              class="list-item"
            >
              <!-- <h6 v-if="index != 0 && index != places.length" style="text-align: left;"><b-icon icon="arrow-down" font-scale="2"></b-icon></h6> -->

              <div
                style="padding-top: 10px"
                v-on:click="
                  calculateAndDisplayRoute2(
                    `${places[index - 1].address}`,
                    `${places[index].address}`
                  )
                "
              >
                <h6
                  v-if="index != 0 && index != places.length"
                  @click="
                    calculateAndDisplayRoute2(
                      `${places[index - 1].address}`,
                      `${places[index].address}`
                    )
                  "
                  style="text-align: left"
                >
                  <b-icon icon="geo-alt" font-scale="1"></b-icon>
                  {{ directions_1[index - 1] }}
                </h6>

                <h6
                  v-if="index != 0 && index != places.length"
                  @click="
                    calculateAndDisplayRoute2(
                      `${places[index - 1].address}`,
                      `${places[index].address}`
                    )
                  "
                  style="padding-bottom: 10px; text-align: left"
                >
                  <b-icon icon="clock" font-scale="1"></b-icon>
                  {{ directions_2[index - 1] }}
                </h6>
              </div>
              <!-- <h5
                v-html="directions[index - 1]"
                v-if="index != 0 && index != places.length"
                @click="
                  calculateAndDisplayRoute2(
                    `${places[index - 1].address}`,
                    `${places[index].address}`
                  )
                "
                style="padding-bottom: 10px; text-align: left;"
              > </h5> -->
              <b-card
                :img-src="place.photoUrl"
                img-alt="Card image"
                img-left
                img-height="120"
                img-width="120"
                @click="moveCenter(place.location)"
              >
                <div class="weater-group">
                  <div class="row">
                    <b-col cols="6">
                      <div v-if="currentWeather.length" class="weather-pos">
                        <img
                          :src="
                            getWeatherIcon(
                              currentWeather[index].weather[0].icon
                            )
                          "
                          alt="Weather Icon"
                          style="width: 40px; height: 40px; margin-left: 4%"
                        />
                        <p style="margin: 8px auto">
                          {{ currentWeather[index].main.temp }}°C
                        </p>
                      </div>
                      <h6 style="margin: 0px">
                        {{ place.name }}
                      </h6>
                      <b-card-text style="margin: 3px">
                        {{ place.address }}
                      </b-card-text>

                      <b-button
                        style="margin: 0px"
                        pill
                        variant="outline-danger"
                        @click="removePlace(index)"
                        id="btn-delete"
                        >삭제</b-button
                      >
                    </b-col>

                    <b-col
                      cols="6"
                      class="weater-group p d-flex align-items-center"
                    >
                      <div
                        style="
                          display: flex;
                          flex-direction: column;
                          width: 100%;
                        "
                      >
                        <input
                          v-model="place.price"
                          id="price-input"
                          type="text"
                          placeholder="price"
                          style="height: 30px; width: 100%; margin-bottom: 10px"
                          @blur="deleteandsave()"
                        />
                        <input
                          v-model="place.memo"
                          id="memo-input"
                          type="text"
                          placeholder="memo"
                          style="height: 30px; width: 100%; margin-bottom: 10px"
                          @blur="deleteandsave()"
                        />
                      </div>
                    </b-col>
                  </div>
                </div>
              </b-card>
            </div>
          </draggable>
        </div>
      </div>
    </div>

    <!-- 모달 창 -->
    <b-modal v-model="editMemberModal" centered>
      <template #modal-title>
        <!-- 모달 창 제목 -->
        [ {{ article.name }} ]
      </template>
      <template #default>
        <label class="checkbox-label">
          {{ article.userId }}
        </label>
        <div
          v-for="member in member_list"
          :key="member.userId"
          class="checkbox-label"
        >
          <input
            type="checkbox"
            :id="member.userId"
            :value="member.userId"
            v-model="delMembers"
            class="checkbox-input"
          />
          {{ member.userId }}
        </div>
      </template>
      <template #modal-footer>
        <!-- 모달 하단 버튼 -->
        <b-button
          variant="outline-dark"
          @click="confirmEditTripTogether"
          style="width: 20%"
        >
          일괄 삭제
        </b-button>
        <b-button
          variant="outline-dark"
          @click="closeEditModal"
          style="width: 20%"
        >
          취소
        </b-button>
      </template>
    </b-modal>

    <!-- 모달 창 -->
    <b-modal v-model="addMemberModal" centered>
      <template #modal-title>
        <!-- 모달 창 제목 -->
        [ {{ article.name }} ]
      </template>
      <template #default>
        <!-- 모달 내용 -->
        여행에 참여할까요?
      </template>
      <template #modal-footer>
        <!-- 모달 하단 버튼 -->
        <b-button
          variant="outline-dark"
          @click="confirmAddTripTogether"
          style="width: 20%"
          >Together!</b-button
        >
        <b-button
          variant="outline-dark"
          @click="closeAddModal"
          style="width: 20%"
          >취소</b-button
        >
      </template>
    </b-modal>
  </div>
</template>

<script>
import axios from "axios";
import draggable from "vuedraggable";
import { BIcon } from "bootstrap-vue";

export default {
  name: "PlanView",

  components: {
    draggable,
    BIcon,
  },

  data() {
    return {
      article: {},
      userId: {},
      addMemberModal: false,
      editMemberModal: false,
      member_list: [],
      delMembers: [],

      map: null,
      placesService: null,
      markers: [],
      places: [],

      start: "",
      waypoints: [],
      end: "",
      directions: [],
      directions_1: [],
      directions_2: [],
      totalDirections: "",
      directionsService: null,
      directionsRenderer: null,
      searchInput: "",
      geocoder: null,

      selected: "DRIVING",

      date: "5/26/2023",
      currentWeather: [],
      forecasts: [],
      filteredForecasts: [],
      latitude: 37.7749, // 예시를 위한 경도
      longitude: -122.4194, // 예시를 위한 위도
      apiKey: "2834387742b25d5393a21e88fee8246a",

      totalDistanceKm: 0,
      totalDurationMin: 0,
    };
  },

  async created() {
    // 현재 세션 스토리지에서 "vuex" 키의 값을 가져오기
    const vuexData = sessionStorage.getItem("vuex");
    const userData = JSON.parse(vuexData);
    console.log(userData);
    // userInfo의 id 값 가져오기
    this.userId = userData.memberStore.userInfo.id;
    console.log(this.userId);
    this.no = this.$route.params.no;

    axios({
      method: "get",
      url: `http://localhost:8080/plan/detail/${this.no}`,
      responseType: "json",
    })
      .then((response) => {
        console.log(response.data);
        this.article = response.data;
      })
      .catch((error) => console.log(error));

    axios({
      method: "get",
      url: `http://localhost:8080/planuser/plan/${this.no}`,
      responseType: "json",
    })
      .then((response) => {
        console.log(response.data.member);
        this.member_list = response.data.member;
      })
      .catch((error) => console.log(error));

    //map
    try {
      const response = await axios.get(
        `http://localhost:8080/planDetail/${this.no}`
      );
      this.places = response.data.data.map((item) => ({
        planId: item.planId,
        name: item.name,
        route: item.route,
        address: item.address,
        location: {
          lat: item.lat,
          lng: item.lng,
        },
        lat: item.lat,
        lng: item.lng,
        photoUrl: item.photoUrl,
        price: item.price,
        memo: item.memo,
        // 다른 컬럼명으로 저장할 속성들 추가
      }));

      this.places.sort((a, b) => a.route - b.route);

      if (this.places.length == 1) {
        this.start = this.places[0].address;
      } else if (this.places.length == 2) {
        this.start = this.places[0].address;
        this.end = this.places[1].address;
        this.calculateAndDisplayRoute();
      } else if (this.places.length >= 3) {
        this.start = this.places[0].address;
        for (let i = 1; i < this.places.length - 1; i++) {
          this.waypoints.push(this.places[i].address);
        }
        this.end = this.places[this.places.length - 1].address;
        this.calculateAndDisplayRoute();
      }
    } catch (error) {
      console.log(error);
    }
  },
  computed: {
    hasUserIdInMemberList() {
      return this.member_list.some((member) => member.userId === this.userId);
    },
    totalPrice() {
      return this.places.reduce(
        (sum, place) => sum + parseFloat(place.price || 0),
        0
      );
    },
  },

  mounted() {
    const googleMapsScript = document.createElement("script");
    googleMapsScript.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyAh8dfzpQhMLMSLpfz9Lo6WZc750-hs7f4&libraries=places&callback=initMap`;
    googleMapsScript.defer = true;
    window.initMap = this.initMap;
    document.head.appendChild(googleMapsScript);
  },
  watch: {
    selected(newValue) {
      this.calculateAndDisplayRoute(newValue);
    },
  },

  methods: {
    getIcon(name) {
      return ["geo-alt", "clock", "other-icon"].includes(name)
        ? name
        : "default-icon";
    },

    async getCurrentWeatherData() {
      this.currentWeather = [];
      // 현재 날씨 가져오기
      for (let i = 0; i < this.places.length; i++) {
        const lat = this.places[i].location.lat;
        const lng = this.places[i].location.lng;

        const currentWeatherUrl = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&units=metric&appid=${this.apiKey}`;
        await axios.get(currentWeatherUrl).then((response) => {
          this.currentWeather.push(response.data);
        });
      }
      console.log(this.currentWeather);
    },
    getWeatherData() {
      // 5일간 날씨 예보 가져오기
      const forecastUrl = `https://api.openweathermap.org/data/2.5/forecast?lat=${this.latitude}&lon=${this.longitude}&units=metric&appid=${this.apiKey}`;
      axios.get(forecastUrl).then((response) => {
        this.forecasts = response.data.list;

        // 목표 날짜에 해당하는 날씨 정보 필터링
        this.filteredForecasts = this.forecasts.filter((forecast) => {
          const forecastDate = new Date(
            forecast.dt_txt.split(" ")[0]
          ).toLocaleDateString("en-US");
          return forecastDate === this.date;
        });
      });
    },
    formatDate(date) {
      const options = {
        weekday: "long",
        year: "numeric",
        month: "long",
        day: "numeric",
      };
      return new Date(date).toLocaleDateString("en-US", options);
    },
    formatTime(timestamp) {
      const date = new Date(timestamp * 1000);
      return date.toLocaleTimeString("en-US", {
        hour: "numeric",
        minute: "numeric",
      });
    },
    getWeatherIcon(icon) {
      return `https://openweathermap.org/img/w/${icon}.png`;
    },

    async deleteandsave() {
      console.log(this.article.id);
      try {
        await axios.delete(
          `http://localhost:8080/planDetail/${this.article.id}`
        );
        await axios.post("http://localhost:8080/planDetail", this.places);
      } catch (error) {
        console.log(error);
      }
    },

    handleUpdate() {
      // 순서가 변경된 후의 places 배열을 출력합니다.
      console.log(this.places);
      this.start = this.places[0].address;
      this.end = this.places[this.places.length - 1].address;
      this.waypoints.length = 0;
      for (let i = 1; i < this.places.length - 1; i++) {
        this.waypoints.push(this.places[i].address);
      }
      for (let i = 0; i < this.places.length; i++) {
        this.places[i].route = i;
      }
      this.calculateAndDisplayRoute();
    },
    initMap() {
      this.map = new window.google.maps.Map(document.getElementById("map"), {
        center: { lat: 47.6062, lng: -122.332 },
        zoom: 13,
      });

      this.placesService = new window.google.maps.places.PlacesService(
        this.map
      );
      this.directionsService = new window.google.maps.DirectionsService();
      this.directionsRenderer = new window.google.maps.DirectionsRenderer();
      this.directionsRenderer.setMap(this.map);
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
                    const place = results[1];
                    console.log(place);

                    const lat = event.latLng.lat();
                    const lng = event.latLng.lng();

                    const location = {
                      lat: lat,
                      lng: lng,
                    };

                    this.addPlace(
                      place.name,
                      place.vicinity,
                      place.place_id,
                      location
                    );
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
                const place = results[1];

                const lat = place.geometry.location.lat();
                const lng = place.geometry.location.lng();

                const location = {
                  lat: lat,
                  lng: lng,
                };

                console.log(location);

                this.addPlace(
                  place.name,
                  place.vicinity,
                  place.place_id,
                  location
                );
              }
            }
          }
        );
      });
      if (this.places.length >= 2) {
        this.calculateAndDisplayRoute();
      } else {
        this.getCurrentWeatherData();
      }
    },
    addPlace(name, address, placeId, location) {
      const maxWidth = 400; // 가져올 사진의 최대 가로 크기를 지정합니다.

      // Place Details 요청을 보내고 Place ID로부터 사진 URL을 추출합니다.
      const request = {
        placeId: placeId,
        fields: ["photo"],
      };

      this.placesService.getDetails(request, (place, status) => {
        if (status === window.google.maps.places.PlacesServiceStatus.OK) {
          const route = this.places.length;
          const planId = this.article.id;
          const lat = location.lat;
          const lng = location.lng;
          if (place.photos && place.photos.length > 0) {
            const photo = place.photos[0];
            const photoUrl = photo.getUrl({ maxWidth: maxWidth });
            console.log(photoUrl);
            this.places.push({
              planId,
              name,
              address,
              photoUrl,
              location,
              lat,
              lng,
              route,
            });
          } else {
            const photoUrl =
              "https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg";
            this.places.push({
              planId,
              name,
              address,
              photoUrl,
              location,
              lat,
              lng,
              route,
            });
          }
          console.log(this.places);
        }
        if (this.places.length == 1) {
          this.start = address;
          this.getCurrentWeatherData();
          this.deleteandsave();
        } else if (this.places.length == 2) {
          this.end = address;
          this.calculateAndDisplayRoute();
        } else if (this.places.length >= 3) {
          this.waypoints[this.places.length - 3] = this.end;
          this.end = address;
          this.calculateAndDisplayRoute();
        }
        //this.calculateAndDisplayRoute();
      });
    },
    removePlace(index) {
      this.places.splice(index, 1);

      if (this.places.length == 0) {
        this.start = "";
        this.deleteandsave();
      } else if (this.places.length == 1) {
        this.start = this.places[0].address;
        this.end = this.start;
        this.calculateAndDisplayRoute();
      } else if (this.places.length >= 2) {
        this.start = this.places[0].address;
        this.end = this.places[this.places.length - 1].address;
        this.waypoints.length = 0;
        for (let i = 1; i < this.places.length - 1; i++) {
          this.waypoints.push(this.places[i].address);
        }
        this.calculateAndDisplayRoute();
      }
      //this.calculateAndDisplayRoute();
    },

    moveCenter(location) {
      console.log(location);
      this.map.setCenter(location);
      this.map.setZoom(15);
    },

    searchLocation() {
      if (this.searchInput === "") {
        return;
      }
      this.geocoder.geocode(
        { address: this.searchInput },
        (results, status) => {
          if (status === "OK") {
            console.log(results);
            const location = results[0].geometry.location;
            this.moveCenter(location);
            new window.google.maps.Marker({
              map: this.map,
              position: location,
            });
          } else {
            alert("장소를 찾을 수 없습니다.");
          }
        }
      );
    },

    calculateAndDisplayRoute() {
      for (let i = 0; i < this.places.length; i++) {
        this.places[i].route = i;
      }
      console.log(this.places);
      const waypts = [];
      for (let i = 0; i < this.waypoints.length; i++) {
        waypts.push({
          location: this.waypoints[i],
          stopover: true,
        });
      }

      this.directionsService
        .route({
          origin: this.start,
          destination: this.end,
          waypoints: waypts,
          optimizeWaypoints: false,
          travelMode: window.google.maps.TravelMode[this.selected],
          unitSystem: window.google.maps.UnitSystem.METRIC, // 거리 단위를 킬로미터로 설정
        })
        .then((response) => {
          this.directionsRenderer.setDirections(response);
          const route = response.routes[0];
          let totalDistance = 0;
          let totalDuration = 0;

          for (let i = 0; i < route.legs.length; i++) {
            //let directions = "";
            //const routeSegment = i + 1;
            const leg = route.legs[i];
            //directions += `<b>Route Segment: ${routeSegment}</b><br>`;
            //directions += `거리 : ${leg.distance.text}  `;
            //directions += `시간 : ${leg.duration.text}`;

            this.directions_1[i] = leg.distance.text;
            this.directions_2[i] = leg.duration.text;
            //console.log(i + directions);

            totalDistance += leg.distance.value;
            totalDuration += leg.duration.value;
          }
          console.log(this.places);
          // console.log(
          //   "::::::::::::" + this.directions[this.directions.length - 1]
          // );
          this.totalDistanceKm = (totalDistance / 1000).toFixed(2); // 총 거리를 킬로미터로 변환하여 소수점 둘째 자리까지 표시
          this.totalDurationMin = Math.round(totalDuration / 60); // 총 이동 시간을 분 단위로 변환
        })
        .catch((e) => {
          window.alert("Directions request failed due to " + e.status);
        });
      this.deleteandsave();
      this.getCurrentWeatherData();
    },

    calculateAndDisplayRoute2(s_address, e_address) {
      for (let i = 0; i < this.places.length; i++) {
        this.places[i].route = i;
      }
      console.log(s_address);
      console.log(e_address);
      this.directionsService
        .route({
          origin: s_address,
          destination: e_address,
          travelMode: window.google.maps.TravelMode[this.selected],
          unitSystem: window.google.maps.UnitSystem.METRIC, // 거리 단위를 킬로미터로 설정
        })
        .then((response) => {
          this.directionsRenderer.setDirections(response);
          const route = response.routes[0];

          const leg = route.legs[0]; // 경로의 첫 번째 leg 정보

          //const distance = leg.distance.text; // 출발지에서 도착지까지의 거리
          //const duration = leg.duration.text; // 출발지에서 도착지까지의 이동 시간

          const totalDistanceKm = leg.distance.value / 1000; // 총 거리를 킬로미터로 변환
          const totalDurationMin = Math.round(leg.duration.value / 60); // 총 이동 시간을 분 단위로 변환

          let directions = `전체 이동 거리 : ${totalDistanceKm.toFixed(
            2
          )} km<br>`;
          directions += `전체 소요 시간 : ${totalDurationMin} min`;

          //this.directions.push(directions);
          console.log(directions);
        })
        .catch((e) => {
          window.alert("Directions request failed due to " + e.status);
        });
      this.deleteandsave();
    },

    addPlanMember() {
      this.addMemberModal = true;
    },
    editPlanMember() {
      this.editMemberModal = true;
    },
    confirmEditTripTogether() {
      this.delMembers.forEach((delId) => {
        axios({
          method: "delete",
          url: `http://localhost:8080/planuser/${this.article.id}/${delId}`,
        })
          .then(() => {
            location.reload();
          })
          .catch((error) => {
            // Handle any errors that occur during deletion
            console.log(error);
          });
      });
    },
    confirmAddTripTogether() {
      console.log(this.article.id);
      console.log(this.userId);
      const inputData = {
        planId: this.article.id,
        userId: this.userId,
      };
      axios({
        method: "Post",
        url: "http://localhost:8080/planuser/",
        data: inputData,
        redirect: "follow",
      })
        .then((response) => {
          console.log(response);
          location.reload();
        })
        .catch((error) => console.log(error));

      this.closeAddModal();
    },
    closeAddModal() {
      this.addMemberModal = false;
    },
    closeEditModal() {
      this.editMemberModal = false;
    },
    delMember() {
      axios({
        method: "delete",
        url: `http://localhost:8080/planuser/${this.article.id}/${this.userId}`,
      })
        .then(() => {
          location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
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

#list {
  font-size: 15px;
}

#btn-delete {
  font-size: 5px;
  width: 50px;
}

.weater-group {
  position: relative;
  margin-top: -10px;
  /* margin-bottom: ; */
}

.weather-pos {
  display: flex;
  height: 30px;
  margin-top: -10px;
}

.modal-dialog-centered {
  display: flex;
  align-items: center;
  justify-content: center;
}

.checkbox-label {
  display: flex;
  align-items: center;
  font-size: 18px;
}

.checkbox-input {
  width: 24px;
  height: 24px;
  margin-right: 8px;
}
</style>
