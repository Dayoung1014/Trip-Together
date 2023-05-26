<template>
  <div class="outer">
    <!-- <home-carousel></home-carousel> -->

    <div class="videoDiv">
      <video
        class="main-top-promotion-video"
        autoplay="autoplay"
        loop="loop"
        muted="muted"
        style="width: 100%; height: 100%"
      >
        <source
          style="width: 100%; height: 100%"
          src="/airplane.mov"
          type="video/mp4"
        />
      </video>
    </div>
  </div>
</template>

<script>
//import HomeCarousel from "@/components/home/HomeCarousel.vue";
export default {
  components: {
    //HomeCarousel,
  },
  data() {
    return {};
  },
  methods: {},
};
</script>
<style scoped>
.outer {
  width: 100%;
  height: 120%;
  /* margin: 0px !important;
  padding: 0px !important; */
  /* display: flex; 추가 */
  justify-content: center; /* 추가 */
  align-items: center; /* 추가 */
}

.videoDiv {
  margin: 100px;
}
.main-top-promotion-video {
  margin: 0 auto;
}
</style>
